group = "team.idealstate.minecraft.spigot"
version = "1.0.0"

repositories {
    maven {
        name = "spigotmc-public"
        url = uri("https://hub.spigotmc.org/nexus/content/groups/public/")
    }
    maven {
        name = "spigotmc-snapshots"
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {
    compileOnly("org.apache.logging.log4j:log4j-api:2.22.1")
    compileOnly("org.spigotmc:spigot-api:1.12-R0.1-SNAPSHOT")

    shadow(project(":modules:wheel-protocol"))
}

tasks.processResources {
    includeEmptyDirs = false
    val props = mapOf(
        "name" to rootProject.name,
        "version" to version,
    )
    filesMatching(listOf("plugin.yml")) {
        expand(props)
    }
}
