package com.algolia.search.dsl.strategy

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.AlternativesAsExact

/**
 * DSL for building a [List] of [AlternativesAsExact].
 */
@Suppress("PropertyName")
@DSLParameters
public class DSLAlternativesAsExact(
    private val alternativesAsExacts: MutableList<AlternativesAsExact> = mutableListOf()
) {

    public val IgnorePlurals: AlternativesAsExact.IgnorePlurals = AlternativesAsExact.IgnorePlurals
    public val SingleWordSynonym: AlternativesAsExact.SingleWordSynonym = AlternativesAsExact.SingleWordSynonym
    public val MultiWordsSynonym: AlternativesAsExact.MultiWordsSynonym = AlternativesAsExact.MultiWordsSynonym

    /**
     * Add [this] to [alternativesAsExacts].
     */
    public operator fun AlternativesAsExact.unaryPlus() {
        alternativesAsExacts += this
    }

    public companion object : DSL<DSLAlternativesAsExact, List<AlternativesAsExact>> {

        override operator fun invoke(block: DSLAlternativesAsExact.() -> Unit): List<AlternativesAsExact> {
            return DSLAlternativesAsExact().apply(block).alternativesAsExacts
        }
    }
}
