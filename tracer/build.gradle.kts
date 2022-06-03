import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc
import org.gradle.kotlin.dsl.application
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.getting
import org.gradle.kotlin.dsl.implementation
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.repositories
import org.gradle.kotlin.dsl.sourceSets
import org.gradle.kotlin.dsl.test
import org.gradle.kotlin.dsl.testImplementation
import org.gradle.kotlin.dsl.version
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {

    kotlin("jvm") version "1.6.21"
    id("com.google.protobuf") version "0.8.18"
    application
}

group = "io.github.hsedjame.grpc.tracing"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")

    implementation("io.grpc:grpc-protobuf:1.46.0")
    implementation("io.grpc:grpc-stub:1.46.0")
    implementation("io.grpc:grpc-kotlin-stub:1.2.1")
    implementation("io.grpc:grpc-netty:1.46.0")

    implementation("com.google.protobuf:protobuf-java-util:3.20.1")
    implementation("com.google.protobuf:protobuf-kotlin:3.20.1")


    testImplementation(kotlin("test"))

    testImplementation("io.grpc:grpc-testing:1.46.0")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>().all {
    kotlinOptions{
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
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
application {
    mainClass.set("MainKt")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    manifest {
        attributes["Main-Class"] = "MainKt"

    }

    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}
