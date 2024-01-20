plugins {
    id("java")
    id("maven-publish")
    kotlin("jvm") version "1.9.21" apply false
    kotlin("plugin.spring") version "1.9.0" apply false
    kotlin("kapt") version "1.9.0" apply false
}
subprojects {
    group = "net.dongri"
    version = "1.0"
    val envs = mutableMapOf<String, String>()
    tasks.register("loadEnv") {
        doFirst {
            val envFile = file(".env")
            if (envFile.exists()) {
                envFile.readLines().forEach {
                    val (key, value) = it.split("=")
                    envs[key] = value
                }
            } else {
                println("Warning: .env file not found, skipping environment variable loading.")
            }
        }
    }
    repositories {
        mavenCentral()
        maven("https://maven.pkg.github.com/dongheemin/packages")
    }
}