package com.algolia.search.dsl.strategy

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.settings.AdvancedSyntaxFeatures

/**
 * DSL for building a [List] of [AdvancedSyntaxFeatures].
 */
@Suppress("PropertyName")
@DSLParameters
class DSLAdvancedSyntaxFeatures(
    private val advancedSyntaxFeatures: MutableList<AdvancedSyntaxFeatures> = mutableListOf()
) {

    val ExactPhrase = AdvancedSyntaxFeatures.ExactPhrase
    val ExcludeWords = AdvancedSyntaxFeatures.ExcludeWords

    /**
     * Add [this] to [advancedSyntaxFeatures].
     */
    operator fun AdvancedSyntaxFeatures.unaryPlus() {
        advancedSyntaxFeatures += this
    }

    companion object : DSL<DSLAdvancedSyntaxFeatures, List<AdvancedSyntaxFeatures>> {

        override operator fun invoke(block: DSLAdvancedSyntaxFeatures.() -> Unit): List<AdvancedSyntaxFeatures> {
            return DSLAdvancedSyntaxFeatures().apply(block).advancedSyntaxFeatures
        }
    }
}
