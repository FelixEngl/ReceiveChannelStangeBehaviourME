plugins {
    kotlin("multiplatform") version "1.5.20"
}

group = "org.mozilla.universalchardet"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    val kotlin_coroutines_version: String by project
    val ktor_version: String by project
    val junit_jupiter_params_version: String by project
    val junit_jupiter_engine_version: String by project

    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
        withJava()
    }
    js(LEGACY) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }

    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-io:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlin_coroutines_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-server-netty:$ktor_version")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.junit.jupiter:junit-jupiter-params:$junit_jupiter_params_version")
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:$junit_jupiter_engine_version")
            }
        }
        val jsMain by getting
        val jsTest by getting
    }
}
