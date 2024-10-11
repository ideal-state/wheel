group = "${rootProject.group}.protocol"

dependencies {
    compileOnly("org.apache.logging.log4j:log4j-api:2.22.1")

    shadow("com.fasterxml.jackson.core:jackson-databind:2.16.0")

    compileOnly("org.projectlombok:lombok:1.18.26")
    annotationProcessor("org.projectlombok:lombok:1.18.26")
}
