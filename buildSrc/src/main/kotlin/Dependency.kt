interface Dependency {

    val group: String
    val artifact: String
    val version: String

    operator fun invoke(module: String): String {
        return "$group:$artifact-$module:$version"
    }
}