package com.algolia.search.dsl.advanced

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.ExplainModule

/**
 * DSL for building a [List] of [ExplainModule].
 */
@Suppress("PropertyName")
@DSLParameters
class DSLExplainModules(
    private val explainModules: MutableList<ExplainModule> = mutableListOf()
) {

    val MatchAlternatives = ExplainModule.MatchAlternatives

    /**
     * Add [this] to [explainModules].
     */
    operator fun ExplainModule.unaryPlus() {
        explainModules += this
    }

    companion object : DSL<DSLExplainModules, List<ExplainModule>> {

        override operator fun invoke(block: DSLExplainModules.() -> Unit): List<ExplainModule> {
            return DSLExplainModules().apply(block).explainModules
        }
    }
}
