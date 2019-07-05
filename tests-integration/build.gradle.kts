import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

plugins {
    id("kotlin2js")
}

group = "org.jetbrains.kotlin.spec"
version = "0.1"

repositories {
    mavenCentral()
    maven {
        setUrl("https://kotlin.bintray.com/js-externals")
    }
}

dependencies {
    implementation(kotlin("stdlib-js"))
    compile("kotlin.js.externals:kotlin-js-jquery:2.0.0-0")
}

java.sourceSets {
    "main" {
        java.srcDir("src/main")
    }
}

tasks {
    "compileKotlin2Js"(Kotlin2JsCompile::class)  {
        kotlinOptions {
            outputFile = "js/${project.name}.js"
        }
    }
}