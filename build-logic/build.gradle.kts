plugins {
  kotlin("jvm")
}

apply(from = "../gradle/dependencies.gradle")

repositories {
  gradlePluginPortal()
  google()
  mavenCentral()
}

group = "com.apollographql.apollo"

dependencies {
  implementation(gradleApi())
  implementation(groovy.util.Eval.x(project, "x.dep.okHttp.okHttp4"))
  implementation(groovy.util.Eval.x(project, "x.dep.moshi.moshi"))

  // We add all the plugins to the classpath here so that they are loaded with proper conflict resolution
  // See https://github.com/gradle/gradle/issues/4741
  implementation(groovy.util.Eval.x(project, "x.dep.android.plugin"))
  implementation(groovy.util.Eval.x(project, "x.dep.gradleJapiCmpPlugin"))
  implementation(groovy.util.Eval.x(project, "x.dep.gradleMetalavaPlugin"))
  implementation(groovy.util.Eval.x(project, "x.dep.kotlin.plugin"))
  implementation(groovy.util.Eval.x(project, "x.dep.sqldelight.plugin"))
  implementation(groovy.util.Eval.x(project, "x.dep.gradlePublishPlugin"))
  implementation(groovy.util.Eval.x(project, "x.dep.benManesVersions"))
  // this plugin is added to the classpath but never applied, it is only used for the closeAndRelease code
  implementation(groovy.util.Eval.x(project, "x.dep.vanniktechPlugin"))

  implementation(groovy.util.Eval.x(project, "x.dep.kotlin.atomicGradle"))
}
