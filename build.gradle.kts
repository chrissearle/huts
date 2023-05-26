plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinter)
    jacoco
}


group = "org.chrissearle.huts"
version = "0.0.1"
application {
    mainClass.set("org.chrissearle.huts.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(19))
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.call.id)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.host)
    implementation(libs.ktor.server.metrics.micrometer)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.server.sessions)

    implementation(libs.ktor.jackson)

    implementation(libs.kotlinx.coroutines.core)

    implementation(libs.arrow.core)

    implementation(libs.kotliquery)
    implementation(libs.flyway)
    implementation(libs.postgres)
    implementation(libs.hikaricp)
    implementation(libs.jackson.datatype.jsr310)

    implementation(libs.logback.classic)
    implementation(libs.kotlin.logging)
    implementation(libs.micrometer.registry.prometheus)

    implementation(libs.passay)
    implementation(libs.password4j)

    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.ktor.client.mock)
    testImplementation(libs.kotest.runner.junit5)
    testImplementation(libs.kotest.assertions.core)
    testImplementation(libs.kotest.framework.datatest)
    testImplementation(libs.kotest.extensions.testcontainers)
    testImplementation(libs.testcontainers.core)
    testImplementation(libs.testcontainers.postgresql)
    testImplementation(libs.mockk.jvm)
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xcontext-receivers")
            jvmTarget = "19"
            apiVersion = "1.9"
            languageVersion = "1.9"
        }
    }
}

tasks.check {
    dependsOn("installKotlinterPrePushHook")
}

tasks.shadowJar {
    dependsOn(":vue:npm_run_build")

    from("vue/dist") {
        into("static")
    }

    archiveFileName.set("huts.jar")
}

tasks.jar {
    enabled = false
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
    dependsOn(tasks.test)
}
