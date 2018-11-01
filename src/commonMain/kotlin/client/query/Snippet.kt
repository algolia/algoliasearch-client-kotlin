package client.query


sealed class Snippet {

    data class Attribute(val attribute: Attribute, val numberOfWords: Int = 10) : Snippet()

    data class All(val numberOfWords: Int) : Snippet() {

        val raw = "*:$numberOfWords"
    }
}