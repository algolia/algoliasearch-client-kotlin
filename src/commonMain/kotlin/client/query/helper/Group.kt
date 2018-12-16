package client.query.helper


sealed class Group(open val name: String) {

    internal enum class Type {
        And,
        OrFacet,
        OrNumeric,
        OrTag
    }

    internal data class Key(val name: String, val type: Type)
}

data class GroupAnd(override val name: String) : Group(name)

data class GroupOr(override val name: String) : Group(name)