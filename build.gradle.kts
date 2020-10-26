tasks {
    val stage by registering {
        dependsOn(getTasksByName("build", true))
    }
}
