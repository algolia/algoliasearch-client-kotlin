package com.algolia.search.dsl.filtering


public interface DSLTag {

    fun tag(tag: String): FilterTag {
        return FilterTag(tag)
    }
}