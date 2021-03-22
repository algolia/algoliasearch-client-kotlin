package com.algolia.search.dsl.advanced

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.ExplainModule

/**
 * DSL for building a [List] of [ExplainModule].
 */
@Suppress("PropertyName")
@DSLParameters
public class DSLExplainModules(
    private val explainModules: MutableList<ExplainModule> = mutableListOf()
) {

    public val MatchAlternatives: ExplainModule.MatchAlternatives = ExplainModule.MatchAlternatives

    /**
     * Add [this] to [explainModules].
     */
    public operator fun ExplainModule.unaryPlus() {
        explainModules += this
    }

    public companion object : DSL<DSLExplainModules, List<ExplainModule>> {

        override operator fun invoke(block: DSLExplainModules.() -> Unit): List<ExplainModule> {
            return DSLExplainModules().apply(block).explainModules
        }
    }
}
