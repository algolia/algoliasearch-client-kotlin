package com.algolia.search.dsl.filtering

import com.algolia.search.model.filter.Filter


public interface DSLTag {

    fun tag(tag: String): Filter.Tag {
        return Filter.Tag(tag)
    }
}