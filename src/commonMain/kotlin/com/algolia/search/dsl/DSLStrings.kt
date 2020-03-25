package com.algolia.search.dsl

/**
 * DSL for building a [List] of [String].
 */
@DSLParameters
class DSLStrings(
    private val strings: MutableList<String> = mutableListOf()
) {

    /**
     * Add [this] to [strings].
     */
    operator fun String.unaryPlus() {
        strings += this
    }

    companion object : DSL<DSLStrings, List<String>> {

        override operator fun invoke(block: DSLStrings.() -> Unit): List<String> {
            return DSLStrings().apply(block).strings
        }
    }
}
