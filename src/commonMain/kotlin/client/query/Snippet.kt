package client.query


sealed class Snippet {

    abstract val raw: String

    data class Attribute(val attribute: String, val numberOfWords: Int? = null) : Snippet() {

        override val raw = if (numberOfWords != null) "$attribute:$numberOfWords" else attribute
    }

    data class All(val numberOfWords: Int) : Snippet() {

        override val raw = "*:$numberOfWords"
    }
}