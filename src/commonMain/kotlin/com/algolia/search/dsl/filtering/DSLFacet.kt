package com.algolia.search.dsl.filtering

import com.algolia.search.model.Attribute


public interface DSLFacet {

    fun facet(name: String, value: Number, score: Int? = null): FilterFacet {
        return facet(Attribute(name), value, score)
    }

    fun facet(attribute: Attribute, value: Number, score: Int? = null): FilterFacet {
        return FilterFacet(attribute, value, score)
    }

    fun facet(name: String, value: String, score: Int? = null): FilterFacet {
        return facet(Attribute(name), value, score)
    }

    fun facet(attribute: Attribute, value: String, score: Int? = null): FilterFacet {
        return FilterFacet(attribute, value, score)
    }

    fun facet(name: String, value: Boolean, score: Int? = null): FilterFacet {
        return facet(Attribute(name), value, score)
    }

    fun facet(attribute: Attribute, value: Boolean, score: Int? = null): FilterFacet {
        return FilterFacet(attribute, value, score)
    }
}