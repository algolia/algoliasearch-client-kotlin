package client


inline class Index(
    val string: String
) {

    fun encode(): StringUTF8 {
        return StringUTF8.encode(string)
    }
}