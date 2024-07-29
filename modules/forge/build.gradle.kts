plugins {
    id("com.gtnewhorizons.retrofuturagradle") version "1.3.25"
}

group = "team.idealstate.minecraft.forge"
version = "1.0.0"

minecraft {
    mcVersion.set("1.12.2")

    // Enable assertions in the mod's package when running the client or server
    extraRunJvmArguments.add("-ea:${project.group}")

    // Exclude some Maven dependency groups from being automatically included in the reobfuscated runs
    groupsToExcludeFromAutoReobfMapping.addAll(
            "com.diffplug",
            "com.diffplug.durian",
            "net.industrial-craft",
            "team.idealstate.minecraft.protocol",
    )
}

repositories {
    // RetroFuturaGradle
    maven {
        name = "GTNH Maven"
        url = uri("https://nexus.gtnewhorizons.com/repository/public/")
    }
}

dependencies {
//    shadow(project(":modules:wheel-protocol"))
    implementation(project(":modules:wheel-protocol"))
}

tasks.processResources {
    includeEmptyDirs = false
    val props = mapOf(
            "mod_id" to rootProject.name,
            "mod_name" to rootProject.name,
            "mod_version" to version,
            "mod_authors" to "ketikai".replace(" ", "").replace(",", "\",\""),
            "minecraft_version" to "1.12.2",
    )
    filesMatching(listOf("assets/**/*.lang", "**/mcmod.info", "**/pack.mcmeta")) {
        expand(props)
    }
    val assetsDir = "assets/${rootProject.name}"
    eachFile {
        if (path.startsWith("assets/")) {
            print("$path >> ")
            path = assetsDir + path.substring(6)
            println(path)
        }
    }
}

tasks.shadowJar {
    manifest.attributes.putAll(tasks.jar.get().manifest.attributes)
    configurations = listOf(project.configurations.runtimeClasspath.get())
    dependencies {
        include {
            it.moduleGroup.startsWith("team.idealstate.minecraft.protocol")
        }
    }
    finalizedBy(tasks.reobfJar)
}

tasks.reobfJar {
    dependsOn(tasks.shadowJar)
    inputJar.set(tasks.shadowJar.get().archiveFile)
}
