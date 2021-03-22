package com.algolia.search.dsl.rule

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.rule.AttributedFacets
import com.algolia.search.model.rule.FacetMerchandising
import com.algolia.search.model.search.Facet

@DSLParameters
public class DSLFacetMerchandising(
    private val facetOrder: MutableList<AttributedFacets> = mutableListOf()
) {

    public fun attributedFacets(attribute: Attribute, facets: List<Facet>) {
        +AttributedFacets(attribute = attribute, facets = facets)
    }

    public operator fun AttributedFacets.unaryPlus() {
        facetOrder += this
    }

    public companion object : DSL<DSLFacetMerchandising, FacetMerchandising> {

        override operator fun invoke(block: DSLFacetMerchandising.() -> Unit): FacetMerchandising {
            return FacetMerchandising(DSLFacetMerchandising().apply(block).facetOrder)
        }
    }
}
