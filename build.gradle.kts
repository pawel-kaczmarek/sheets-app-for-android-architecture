tasks {
    val stage by registering {
        dependsOn(getTasksByName("build", true))

        doLast {
            copy {
                from("$rootDir/application/build/libs")
                into("$buildDir/libs")
            }
        }
    }
}
