plugins {
    id("idea")
    id("com.github.johnrengelman.shadow") version ("8.+") apply false
}

group = "team.idealstate.minecraft"

subprojects {
    val buildScript = project.file("build.gradle.kts")
    if (!buildScript.exists() || buildScript.isDirectory) {
        return@subprojects
    }

    apply {
        plugin("idea")
        plugin("java")
        plugin("java-library")
        plugin("com.github.johnrengelman.shadow")

        from(rootProject.file("/gradle/languages.gradle"))
        from(rootProject.file("/gradle/repositories.gradle"))
        from(rootProject.file("/gradle/dependencies.gradle"))
        from(rootProject.file("/gradle/resources.gradle"))
        from(rootProject.file("/gradle/packages.gradle"))
    }
}