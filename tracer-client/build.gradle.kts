import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.generateProtoTasks
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.21"
    id("com.google.protobuf") version "0.8.18"
    id("maven-publish")
}

group = "io.github.hsedjame"
version = "0.0.1"
application {
    mainClass.set("io.github.hsedjame.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap") }
}

dependencies {
    implementation("io.grpc:grpc-protobuf:1.46.0")
    implementation("io.grpc:grpc-stub:1.46.0")
    implementation("io.grpc:grpc-kotlin-stub:1.2.1")
    implementation("io.grpc:grpc-netty:1.46.0")

    implementation("com.google.protobuf:protobuf-java-util:3.20.1")
    implementation("com.google.protobuf:protobuf-kotlin:3.20.1")

    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.6")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:1.6.1-native-mt")

    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")


    implementation("io.ktor:ktor-serialization-gson-jvm:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")

    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions{
        jvmTarget = "17"
        freeCompilerArgs = listOf("-opt-in=kotlin.RequiresOptIn")
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.20.1"
    }
    plugins {
        id ("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.46.0"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }

            it.builtins {
                id("kotlin")
            }
        }
    }
}

sourceSets {
    val main by getting {}

    main.java.srcDir("build/generated/source/proto/main/java")
    main.java.srcDir("build/generated/source/proto/main/grpc")
    main.java.srcDir("build/generated/source/proto/main/kotlin")
    main.java.srcDir("build/generated/source/proto/main/grpckt")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "io.github.hsedjame"
            artifactId = "tracer-client"
            version = "0.0.1"

            from(components["java"])
        }
    }
}