package com.apollographql.apollo.internal.interceptor

import com.apollographql.apollo.api.internal.Optional
import com.apollographql.apollo.cache.CacheHeaders
import com.apollographql.apollo.integration.interceptor.UploadFileExampleMutation
import com.apollographql.apollo.integration.interceptor.type.NestedObject
import com.apollographql.apollo.internal.ApolloLogger
import com.apollographql.apollo.response.ScalarTypeAdapters
import com.google.common.base.Predicate
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.fail
import okhttp3.*
import okio.Buffer
import org.junit.Test
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException

fun createFile(fileName: String, content: String): File {
    val tempDir = System.getProperty("java.io.tmpdir")
    val filePath = tempDir + "/" + fileName
    val f = File(filePath)
    val bw = BufferedWriter(FileWriter(f))
    bw.write(content)
    bw.close()
    return f
}

class ApolloServerUploadInterceptorTest {
    private val serverUrl = HttpUrl.parse("http://google.com")
    private val file0 = createFile("test0.txt", "content_testZero")
    private val file1 = createFile("test1.png", "content_testOne")
    private val file2 = createFile("test2.jpg", "content_test2")
    private val nestedObject0 = NestedObject.builder()
            .file(file0)
            .fileList(listOf(file1, file2))
            .build()
    private val nestedObject1 = NestedObject.builder()
            .file(file1)
            .fileList(listOf(file0, file2))
            .build()
    private val nestedObject2 = NestedObject.builder()
            .file(file2)
            .fileList(listOf(file0, file1))
            .recursiveNested(listOf(nestedObject0, nestedObject1))
            .build()
    private val query = UploadFileExampleMutation.builder()
            .nested(nestedObject2)
            .topFile(file2)
            .topFileList(listOf(file1, file0))
            .build()

    @Test
    fun testUploadWithGraphQL() {
        val requestAssertPredicate = Predicate<Request> { request ->
            assertThat(request!!.header(ApolloServerInterceptor.HEADER_CONTENT_TYPE)).isEqualTo("multipart/form-data; boundary=--graphql-multipart-upload-boundary-85763456--")
            assertRequestBody(request, """
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="operations"
Content-Type: application/json; charset=utf-8
Content-Length: 419

{"query":"mutation uploadFileExample(${'$'}topFile: Upload, ${'$'}topFileList: [Upload], ${'$'}nested: NestedObject) {  uploadFileExample(topFile: ${'$'}topFile, topFileList: ${'$'}topFileList, nested: ${'$'}nested)}","operationName":"uploadFileExample","variables":{"topFile":null,"topFileList":[null,null],"nested":{"recursiveNested":[{"file":null,"fileList":[null,null]},{"file":null,"fileList":[null,null]}],"file":null,"fileList":[null,null]}}}
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="map"
Content-Type: application/json; charset=utf-8
Content-Length: 507

{"0":["variables.topFile"],"11":["variables.nested.fileList.1"],"1":["variables.topFileList.0"],"2":["variables.topFileList.1"],"3":["variables.nested.recursiveNested.0.file"],"4":["variables.nested.recursiveNested.0.fileList.0"],"5":["variables.nested.recursiveNested.0.fileList.1"],"6":["variables.nested.recursiveNested.1.file"],"7":["variables.nested.recursiveNested.1.fileList.0"],"8":["variables.nested.recursiveNested.1.fileList.1"],"9":["variables.nested.file"],"10":["variables.nested.fileList.0"]}
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="0"; filename="test2.jpg"
Content-Type: image/jpeg
Content-Length: 13

content_test2
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="1"; filename="test1.png"
Content-Type: image/png
Content-Length: 15

content_testOne
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="2"; filename="test0.txt"
Content-Type: text/plain
Content-Length: 16

content_testZero
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="3"; filename="test0.txt"
Content-Type: text/plain
Content-Length: 16

content_testZero
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="4"; filename="test1.png"
Content-Type: image/png
Content-Length: 15

content_testOne
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="5"; filename="test2.jpg"
Content-Type: image/jpeg
Content-Length: 13

content_test2
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="6"; filename="test1.png"
Content-Type: image/png
Content-Length: 15

content_testOne
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="7"; filename="test0.txt"
Content-Type: text/plain
Content-Length: 16

content_testZero
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="8"; filename="test2.jpg"
Content-Type: image/jpeg
Content-Length: 13

content_test2
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="9"; filename="test2.jpg"
Content-Type: image/jpeg
Content-Length: 13

content_test2
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="10"; filename="test0.txt"
Content-Type: text/plain
Content-Length: 16

content_testZero
----graphql-multipart-upload-boundary-85763456--
Content-Disposition: form-data; name="11"; filename="test1.png"
Content-Type: image/png
Content-Length: 15

content_testOne
----graphql-multipart-upload-boundary-85763456----
    """.trimIndent())
            true
        }

        val interceptor = ApolloServerInterceptor(serverUrl!!,
                AssertHttpCallFactory(requestAssertPredicate), null, false,
                ScalarTypeAdapters(emptyMap()),
                ApolloLogger(Optional.absent()))

        interceptor.httpCall(query, CacheHeaders.NONE, true)
    }

    private fun assertRequestBody(request: Request?, expectedRequestBody: String) {
//            assertThat(request!!.body()!!.contentType()).isEqualTo("multipart/form-data; boundary=--graphql-multipart-upload-boundary-85763456--");
        val bodyBuffer = Buffer()
        request!!.body()!!.writeTo(bodyBuffer)
        assertThat(bodyBuffer.readUtf8().trimIndent()).isEqualTo(expectedRequestBody)
    }

    private class AssertHttpCallFactory internal constructor(internal val predicate: Predicate<Request>) : Call.Factory {

        override fun newCall(request: Request): Call {
            if (!predicate.apply(request)) {
                fail("Assertion failed")
            }
            return NoOpCall()
        }
    }

    private class NoOpCall : Call {
        override fun request(): Request? {
            return null
        }

        @Throws(IOException::class)
        override fun execute(): Response? {
            return null
        }

        override fun enqueue(responseCallback: Callback) {}

        override fun cancel() {}

        override fun isExecuted(): Boolean {
            return false
        }

        override fun isCanceled(): Boolean {
            return false
        }

        override fun clone(): Call {
            return this
        }
    }
}
