// AUTO-GENERATED FILE. DO NOT MODIFY.
//
// This class was automatically generated by Apollo GraphQL plugin from the GraphQL queries it found.
// It should not be modified by hand.
//
package com.example.reserved_words

import com.apollographql.apollo.api.InputFieldMarshaller
import com.apollographql.apollo.api.Mutation
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.OperationName
import com.apollographql.apollo.api.ResponseField
import com.apollographql.apollo.api.ResponseFieldMapper
import com.apollographql.apollo.api.ResponseFieldMarshaller
import com.apollographql.apollo.api.ResponseReader
import com.example.reserved_words.type.TestInputType
import kotlin.Any
import kotlin.Array
import kotlin.String
import kotlin.Suppress
import kotlin.collections.Map
import kotlin.jvm.Transient

@Suppress("NAME_SHADOWING", "LocalVariableName", "RemoveExplicitTypeArguments",
        "NestedLambdaShadowedImplicitParameter")
data class TestMutation(val input: TestInputType) : Mutation<TestMutation.Data, TestMutation.Data,
        Operation.Variables> {
    @Transient
    private val variables: com.apollographql.apollo.api.Operation.Variables = object :
            com.apollographql.apollo.api.Operation.Variables() {
        override fun valueMap(): Map<String, Any?> = mutableMapOf<String, Any?>().apply {
            this["input"] = input
        }

        override fun marshaller(): InputFieldMarshaller = InputFieldMarshaller { writer ->
            writer.writeObject("input", input.marshaller())
        }
    }

    override fun operationId(): String = OPERATION_ID
    override fun queryDocument(): String = QUERY_DOCUMENT
    override fun wrapData(data: Data?): Data? = data
    override fun variables(): com.apollographql.apollo.api.Operation.Variables = variables
    override fun name(): OperationName = OPERATION_NAME
    override fun responseFieldMapper(): ResponseFieldMapper<Data> = ResponseFieldMapper {
        Data(it)
    }

    data class Operation(val __typename: String, val name: String) {
        fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], __typename)
            it.writeString(RESPONSE_FIELDS[1], name)
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("__typename", "__typename", null, false, null),
                    ResponseField.forString("name", "name", null, false, null)
                    )

            operator fun invoke(reader: ResponseReader): Operation {
                val __typename = reader.readString(RESPONSE_FIELDS[0])
                val name = reader.readString(RESPONSE_FIELDS[1])
                return Operation(
                    __typename = __typename,
                    name = name
                )
            }
        }
    }

    data class Data(
        val abstract_: String?,
        val assert_: String?,
        val boolean_: String?,
        val break_: String?,
        val byte_: String?,
        val case_: String?,
        val catch_: String?,
        val char_: String?,
        val class_: String?,
        val const_: String?,
        val continue_: String?,
        val default_: String?,
        val do_: String?,
        val double_: String?,
        val else_: String?,
        val enum_: String?,
        val extends_: String?,
        val final_: String?,
        val finally_: String?,
        val float_: String?,
        val for_: String?,
        val goto_: String?,
        val if_: String?,
        val implements_: String?,
        val import_: String?,
        val instanceof_: String?,
        val int_: String?,
        val interface_: String?,
        val long_: String?,
        val native_: String?,
        val new_: String?,
        val package_: String?,
        val private_: String?,
        val protected_: String?,
        val public_: String?,
        val return_: String?,
        val short_: String?,
        val static_: String?,
        val strictfp_: String?,
        val super_: String?,
        val switch_: String?,
        val synchronized_: String?,
        val this_: String?,
        val throw_: String?,
        val throws_: String?,
        val transient_: String?,
        val try_: String?,
        val void_: String?,
        val volatile_: String?,
        val while_: String?,
        val operation: Operation?
    ) : com.apollographql.apollo.api.Operation.Data {
        override fun marshaller(): ResponseFieldMarshaller = ResponseFieldMarshaller {
            it.writeString(RESPONSE_FIELDS[0], abstract_)
            it.writeString(RESPONSE_FIELDS[1], assert_)
            it.writeString(RESPONSE_FIELDS[2], boolean_)
            it.writeString(RESPONSE_FIELDS[3], break_)
            it.writeString(RESPONSE_FIELDS[4], byte_)
            it.writeString(RESPONSE_FIELDS[5], case_)
            it.writeString(RESPONSE_FIELDS[6], catch_)
            it.writeString(RESPONSE_FIELDS[7], char_)
            it.writeString(RESPONSE_FIELDS[8], class_)
            it.writeString(RESPONSE_FIELDS[9], const_)
            it.writeString(RESPONSE_FIELDS[10], continue_)
            it.writeString(RESPONSE_FIELDS[11], default_)
            it.writeString(RESPONSE_FIELDS[12], do_)
            it.writeString(RESPONSE_FIELDS[13], double_)
            it.writeString(RESPONSE_FIELDS[14], else_)
            it.writeString(RESPONSE_FIELDS[15], enum_)
            it.writeString(RESPONSE_FIELDS[16], extends_)
            it.writeString(RESPONSE_FIELDS[17], final_)
            it.writeString(RESPONSE_FIELDS[18], finally_)
            it.writeString(RESPONSE_FIELDS[19], float_)
            it.writeString(RESPONSE_FIELDS[20], for_)
            it.writeString(RESPONSE_FIELDS[21], goto_)
            it.writeString(RESPONSE_FIELDS[22], if_)
            it.writeString(RESPONSE_FIELDS[23], implements_)
            it.writeString(RESPONSE_FIELDS[24], import_)
            it.writeString(RESPONSE_FIELDS[25], instanceof_)
            it.writeString(RESPONSE_FIELDS[26], int_)
            it.writeString(RESPONSE_FIELDS[27], interface_)
            it.writeString(RESPONSE_FIELDS[28], long_)
            it.writeString(RESPONSE_FIELDS[29], native_)
            it.writeString(RESPONSE_FIELDS[30], new_)
            it.writeString(RESPONSE_FIELDS[31], package_)
            it.writeString(RESPONSE_FIELDS[32], private_)
            it.writeString(RESPONSE_FIELDS[33], protected_)
            it.writeString(RESPONSE_FIELDS[34], public_)
            it.writeString(RESPONSE_FIELDS[35], return_)
            it.writeString(RESPONSE_FIELDS[36], short_)
            it.writeString(RESPONSE_FIELDS[37], static_)
            it.writeString(RESPONSE_FIELDS[38], strictfp_)
            it.writeString(RESPONSE_FIELDS[39], super_)
            it.writeString(RESPONSE_FIELDS[40], switch_)
            it.writeString(RESPONSE_FIELDS[41], synchronized_)
            it.writeString(RESPONSE_FIELDS[42], this_)
            it.writeString(RESPONSE_FIELDS[43], throw_)
            it.writeString(RESPONSE_FIELDS[44], throws_)
            it.writeString(RESPONSE_FIELDS[45], transient_)
            it.writeString(RESPONSE_FIELDS[46], try_)
            it.writeString(RESPONSE_FIELDS[47], void_)
            it.writeString(RESPONSE_FIELDS[48], volatile_)
            it.writeString(RESPONSE_FIELDS[49], while_)
            it.writeObject(RESPONSE_FIELDS[50], operation?.marshaller())
        }

        companion object {
            private val RESPONSE_FIELDS: Array<ResponseField> = arrayOf(
                    ResponseField.forString("abstract", "abstract", null, true, null),
                    ResponseField.forString("assert", "assert", null, true, null),
                    ResponseField.forString("boolean", "boolean", null, true, null),
                    ResponseField.forString("break", "break", null, true, null),
                    ResponseField.forString("byte", "byte", null, true, null),
                    ResponseField.forString("case", "case", null, true, null),
                    ResponseField.forString("catch", "catch", null, true, null),
                    ResponseField.forString("char", "char", null, true, null),
                    ResponseField.forString("class", "class", null, true, null),
                    ResponseField.forString("const", "const", null, true, null),
                    ResponseField.forString("continue", "continue", null, true, null),
                    ResponseField.forString("default", "default", null, true, null),
                    ResponseField.forString("do", "do", null, true, null),
                    ResponseField.forString("double", "double", null, true, null),
                    ResponseField.forString("else", "else", null, true, null),
                    ResponseField.forString("enum", "enum", null, true, null),
                    ResponseField.forString("extends", "extends", null, true, null),
                    ResponseField.forString("final", "final", null, true, null),
                    ResponseField.forString("finally", "finally", null, true, null),
                    ResponseField.forString("float", "float", null, true, null),
                    ResponseField.forString("for", "for", null, true, null),
                    ResponseField.forString("goto", "goto", null, true, null),
                    ResponseField.forString("if", "if", null, true, null),
                    ResponseField.forString("implements", "implements", null, true, null),
                    ResponseField.forString("import", "import", null, true, null),
                    ResponseField.forString("instanceof", "instanceof", null, true, null),
                    ResponseField.forString("int", "int", null, true, null),
                    ResponseField.forString("interface", "interface", null, true, null),
                    ResponseField.forString("long", "long", null, true, null),
                    ResponseField.forString("native", "native", null, true, null),
                    ResponseField.forString("new", "new", null, true, null),
                    ResponseField.forString("package", "package", null, true, null),
                    ResponseField.forString("private", "private", null, true, null),
                    ResponseField.forString("protected", "protected", null, true, null),
                    ResponseField.forString("public", "public", null, true, null),
                    ResponseField.forString("return", "return", null, true, null),
                    ResponseField.forString("short", "short", null, true, null),
                    ResponseField.forString("static", "static", null, true, null),
                    ResponseField.forString("strictfp", "strictfp", null, true, null),
                    ResponseField.forString("super", "super", null, true, null),
                    ResponseField.forString("switch", "switch", null, true, null),
                    ResponseField.forString("synchronized", "synchronized", null, true, null),
                    ResponseField.forString("this", "this", null, true, null),
                    ResponseField.forString("throw", "throw", null, true, null),
                    ResponseField.forString("throws", "throws", null, true, null),
                    ResponseField.forString("transient", "transient", null, true, null),
                    ResponseField.forString("try", "try", null, true, null),
                    ResponseField.forString("void", "void", null, true, null),
                    ResponseField.forString("volatile", "volatile", null, true, null),
                    ResponseField.forString("while", "while", null, true, null),
                    ResponseField.forObject("operation", "operation", null, true, null)
                    )

            operator fun invoke(reader: ResponseReader): Data {
                val abstract_ = reader.readString(RESPONSE_FIELDS[0])
                val assert_ = reader.readString(RESPONSE_FIELDS[1])
                val boolean_ = reader.readString(RESPONSE_FIELDS[2])
                val break_ = reader.readString(RESPONSE_FIELDS[3])
                val byte_ = reader.readString(RESPONSE_FIELDS[4])
                val case_ = reader.readString(RESPONSE_FIELDS[5])
                val catch_ = reader.readString(RESPONSE_FIELDS[6])
                val char_ = reader.readString(RESPONSE_FIELDS[7])
                val class_ = reader.readString(RESPONSE_FIELDS[8])
                val const_ = reader.readString(RESPONSE_FIELDS[9])
                val continue_ = reader.readString(RESPONSE_FIELDS[10])
                val default_ = reader.readString(RESPONSE_FIELDS[11])
                val do_ = reader.readString(RESPONSE_FIELDS[12])
                val double_ = reader.readString(RESPONSE_FIELDS[13])
                val else_ = reader.readString(RESPONSE_FIELDS[14])
                val enum_ = reader.readString(RESPONSE_FIELDS[15])
                val extends_ = reader.readString(RESPONSE_FIELDS[16])
                val final_ = reader.readString(RESPONSE_FIELDS[17])
                val finally_ = reader.readString(RESPONSE_FIELDS[18])
                val float_ = reader.readString(RESPONSE_FIELDS[19])
                val for_ = reader.readString(RESPONSE_FIELDS[20])
                val goto_ = reader.readString(RESPONSE_FIELDS[21])
                val if_ = reader.readString(RESPONSE_FIELDS[22])
                val implements_ = reader.readString(RESPONSE_FIELDS[23])
                val import_ = reader.readString(RESPONSE_FIELDS[24])
                val instanceof_ = reader.readString(RESPONSE_FIELDS[25])
                val int_ = reader.readString(RESPONSE_FIELDS[26])
                val interface_ = reader.readString(RESPONSE_FIELDS[27])
                val long_ = reader.readString(RESPONSE_FIELDS[28])
                val native_ = reader.readString(RESPONSE_FIELDS[29])
                val new_ = reader.readString(RESPONSE_FIELDS[30])
                val package_ = reader.readString(RESPONSE_FIELDS[31])
                val private_ = reader.readString(RESPONSE_FIELDS[32])
                val protected_ = reader.readString(RESPONSE_FIELDS[33])
                val public_ = reader.readString(RESPONSE_FIELDS[34])
                val return_ = reader.readString(RESPONSE_FIELDS[35])
                val short_ = reader.readString(RESPONSE_FIELDS[36])
                val static_ = reader.readString(RESPONSE_FIELDS[37])
                val strictfp_ = reader.readString(RESPONSE_FIELDS[38])
                val super_ = reader.readString(RESPONSE_FIELDS[39])
                val switch_ = reader.readString(RESPONSE_FIELDS[40])
                val synchronized_ = reader.readString(RESPONSE_FIELDS[41])
                val this_ = reader.readString(RESPONSE_FIELDS[42])
                val throw_ = reader.readString(RESPONSE_FIELDS[43])
                val throws_ = reader.readString(RESPONSE_FIELDS[44])
                val transient_ = reader.readString(RESPONSE_FIELDS[45])
                val try_ = reader.readString(RESPONSE_FIELDS[46])
                val void_ = reader.readString(RESPONSE_FIELDS[47])
                val volatile_ = reader.readString(RESPONSE_FIELDS[48])
                val while_ = reader.readString(RESPONSE_FIELDS[49])
                val operation = reader.readObject<Operation>(RESPONSE_FIELDS[50]) { reader ->
                    Operation(reader)
                }

                return Data(
                    abstract_ = abstract_,
                    assert_ = assert_,
                    boolean_ = boolean_,
                    break_ = break_,
                    byte_ = byte_,
                    case_ = case_,
                    catch_ = catch_,
                    char_ = char_,
                    class_ = class_,
                    const_ = const_,
                    continue_ = continue_,
                    default_ = default_,
                    do_ = do_,
                    double_ = double_,
                    else_ = else_,
                    enum_ = enum_,
                    extends_ = extends_,
                    final_ = final_,
                    finally_ = finally_,
                    float_ = float_,
                    for_ = for_,
                    goto_ = goto_,
                    if_ = if_,
                    implements_ = implements_,
                    import_ = import_,
                    instanceof_ = instanceof_,
                    int_ = int_,
                    interface_ = interface_,
                    long_ = long_,
                    native_ = native_,
                    new_ = new_,
                    package_ = package_,
                    private_ = private_,
                    protected_ = protected_,
                    public_ = public_,
                    return_ = return_,
                    short_ = short_,
                    static_ = static_,
                    strictfp_ = strictfp_,
                    super_ = super_,
                    switch_ = switch_,
                    synchronized_ = synchronized_,
                    this_ = this_,
                    throw_ = throw_,
                    throws_ = throws_,
                    transient_ = transient_,
                    try_ = try_,
                    void_ = void_,
                    volatile_ = volatile_,
                    while_ = while_,
                    operation = operation
                )
            }
        }
    }

    companion object {
        const val OPERATION_ID: String =
                "413697ef5917f00cd5c2ff0389d350a39a0e707d4629c7721539204c6ce7efa4"

        val QUERY_DOCUMENT: String = "query TestMutation {}"

        val OPERATION_NAME: OperationName = OperationName { "TestMutation" }
    }
}
