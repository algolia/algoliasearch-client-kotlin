package com.algolia.search.dsl.strategy

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.AlternativesAsExact

/**
 * DSL for building a [List] of [AlternativesAsExact].
 */
@Suppress("PropertyName")
@DSLParameters
class DSLAlternativesAsExact(
    private val alternativesAsExacts: MutableList<AlternativesAsExact> = mutableListOf()
) {

    val IgnorePlurals = AlternativesAsExact.IgnorePlurals
    val SingleWordSynonym = AlternativesAsExact.SingleWordSynonym
    val MultiWordsSynonym = AlternativesAsExact.MultiWordsSynonym

    /**
     * Add [this] to [alternativesAsExacts].
     */
    operator fun AlternativesAsExact.unaryPlus() {
        alternativesAsExacts += this
    }

    companion object : DSL<DSLAlternativesAsExact, List<AlternativesAsExact>> {

        override operator fun invoke(block: DSLAlternativesAsExact.() -> Unit): List<AlternativesAsExact> {
            return DSLAlternativesAsExact().apply(block).alternativesAsExacts
        }
    }
}
