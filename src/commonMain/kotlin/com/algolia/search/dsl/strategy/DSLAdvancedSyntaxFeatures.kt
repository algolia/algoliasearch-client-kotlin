package com.algolia.search.dsl.strategy

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.settings.AdvancedSyntaxFeatures


@DSLParameters
public class DSLAdvancedSyntaxFeatures(
    private val advancedSyntaxFeatures: MutableList<AdvancedSyntaxFeatures> = mutableListOf()
) {

    public val ExactPhrase = AdvancedSyntaxFeatures.ExactPhrase
    public val ExcludeWords = AdvancedSyntaxFeatures.ExcludeWords

    public operator fun AdvancedSyntaxFeatures.unaryPlus() {
        advancedSyntaxFeatures += this
    }

    public companion object : DSL<DSLAdvancedSyntaxFeatures, List<AdvancedSyntaxFeatures>> {

        override operator fun invoke(block: DSLAdvancedSyntaxFeatures.() -> Unit): List<AdvancedSyntaxFeatures> {
            return DSLAdvancedSyntaxFeatures().apply(block).advancedSyntaxFeatures
        }
    }
}