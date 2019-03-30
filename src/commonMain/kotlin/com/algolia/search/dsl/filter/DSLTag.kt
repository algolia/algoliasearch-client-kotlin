package com.algolia.search.dsl.filter


public interface DSLTag {

    fun tag(tag: String): FilterTag {
        return FilterTag(tag)
    }
}