package client


data class Index(
    val name: String
) {

    fun encode(): StringUTF8 {
        return StringUTF8.encode(name)
    }
}