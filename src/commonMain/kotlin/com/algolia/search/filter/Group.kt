package com.algolia.search.filter


public sealed class Group {

    abstract val name: String

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

public data class GroupAnd(override val name: String) : Group()

public data class GroupOr(override val name: String) : Group()