plugins {
    id("idea")
    id("com.gradleup.shadow") version "8.3.3" apply false
    kotlin("jvm") version "1.9.22" apply false
}

group = "team.idealstate.minecraft"
version = "2.0.0"

subprojects {
    val buildScript = project.file("build.gradle.kts")
    if (!buildScript.exists() || buildScript.isDirectory) {
        return@subprojects
    }

    version = rootProject.version

    apply {
        plugin("idea")
        plugin("java")
        plugin("java-library")
        plugin("com.gradleup.shadow")
        plugin("org.jetbrains.kotlin.jvm")

        from(rootProject.file("/gradle/languages.gradle"))
        from(rootProject.file("/gradle/repositories.gradle"))
        from(rootProject.file("/gradle/dependencies.gradle"))
        from(rootProject.file("/gradle/resources.gradle"))
        from(rootProject.file("/gradle/packages.gradle"))
    }
}