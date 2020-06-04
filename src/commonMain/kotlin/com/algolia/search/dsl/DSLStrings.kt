package com.algolia.search.dsl

/**
 * DSL for building a [List] of [String].
 */
@DSLParameters
public class DSLStrings(
    private val strings: MutableList<String> = mutableListOf()
) {

    /**
     * Add [this] to [strings].
     */
    public operator fun String.unaryPlus() {
        strings += this
    }

    public companion object : DSL<DSLStrings, List<String>> {

        override operator fun invoke(block: DSLStrings.() -> Unit): List<String> {
            return DSLStrings().apply(block).strings
        }
    }
}
