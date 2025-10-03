dependencies {
    implementation(project(":api"))
    compileOnly(libs.jetanno)
}

tasks.register<Copy>("exportResources") {
    from("src/main/resources")
    into("${buildDir}/exportedResources")
}
