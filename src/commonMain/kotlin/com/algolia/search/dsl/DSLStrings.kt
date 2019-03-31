package com.algolia.search.dsl


@DSLParameters
public class DSLStrings {

    private val strings: MutableList<String> = mutableListOf()

    public operator fun String.unaryPlus() {
        strings += this
    }

    public fun build(): List<String> {
        return strings.toList()
    }
}