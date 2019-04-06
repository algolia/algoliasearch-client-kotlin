package com.algolia.search.dsl.rule

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.rule.AutomaticFacetFilters


@DSLParameters
public class DSLAutomaticFacetFilters(
    private val automaticFacetFilters: MutableList<AutomaticFacetFilters> = mutableListOf()
) {

    public operator fun String.unaryPlus() {
        +Attribute(this)
    }

    public operator fun Attribute.unaryPlus() {
        +AutomaticFacetFilters(this)
    }

    public operator fun AutomaticFacetFilters.unaryPlus() {
        automaticFacetFilters += this
    }

    public operator fun String.invoke(score: Int? = null, disjunctive: Boolean? = null): AutomaticFacetFilters {
        return Attribute(this).invoke(score, disjunctive)
    }

    public operator fun Attribute.invoke(score: Int? = null, disjunctive: Boolean? = null): AutomaticFacetFilters {
        return AutomaticFacetFilters(this, score, disjunctive)
    }

    public companion object : DSL<DSLAutomaticFacetFilters, List<AutomaticFacetFilters>> {

        override operator fun invoke(block: DSLAutomaticFacetFilters.() -> Unit): List<AutomaticFacetFilters> {
            return DSLAutomaticFacetFilters().apply(block).automaticFacetFilters
        }
    }
}