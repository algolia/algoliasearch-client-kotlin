package com.algolia.search.dsl.strategy

import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.AlternativesAsExact


@DSLParameters
public class DSLAlternativesAsExact(
    private val alternativesAsExacts: MutableList<AlternativesAsExact> = mutableListOf()
) {

    public val IgnorePlurals = AlternativesAsExact.IgnorePlurals
    public val SingleWordSynonym = AlternativesAsExact.SingleWordSynonym
    public val MultiWordsSynonym = AlternativesAsExact.MultiWordsSynonym

    public operator fun AlternativesAsExact.unaryPlus() {
        alternativesAsExacts += this
    }

    public fun build(): List<AlternativesAsExact> {
        return alternativesAsExacts.toList()
    }
}