package com.algolia.search.saas.query


sealed class Group(open val name: String) {

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

data class GroupAnd(override val name: String) : Group(name)

data class GroupOr(override val name: String) : Group(name)