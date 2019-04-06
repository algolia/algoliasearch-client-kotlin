package com.algolia.search.dsl.filtering

import com.algolia.search.model.Attribute
import com.algolia.search.model.filter.Filter


public interface DSLFacet {

    fun facet(name: String, value: Number, score: Int? = null): Filter.Facet {
        return facet(Attribute(name), value, score)
    }

    fun facet(attribute: Attribute, value: Number, score: Int? = null): Filter.Facet {
        return Filter.Facet(attribute, value, score)
    }

    fun facet(name: String, value: String, score: Int? = null): Filter.Facet {
        return facet(Attribute(name), value, score)
    }

    fun facet(attribute: Attribute, value: String, score: Int? = null): Filter.Facet {
        return Filter.Facet(attribute, value, score)
    }

    fun facet(name: String, value: Boolean, score: Int? = null): Filter.Facet {
        return facet(Attribute(name), value, score)
    }

    fun facet(attribute: Attribute, value: Boolean, score: Int? = null): Filter.Facet {
        return Filter.Facet(attribute, value, score)
    }
}