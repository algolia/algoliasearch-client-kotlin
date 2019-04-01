package com.algolia.search.dsl.filtering


public interface DSLTag {

    fun tag(tag: String): Filter.Tag {
        return Filter.Tag(tag)
    }
}