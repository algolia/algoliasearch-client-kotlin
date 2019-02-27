package com.algolia.search.query


public sealed class Group(open val name: String) {

    internal enum class Type {
        And,
        OrFacet,
        OrNumeric,
        OrTag
    }

    internal data class Key(val name: String, val type: Type) {

        override fun toString(): String {
            return "Group($name, type=$type)"
        }
    }
}

public data class GroupAnd(override val name: String) : Group(name)

public data class GroupOr(override val name: String) : Group(name)