package com.algolia.search.dsl

import com.algolia.search.model.synonym.SynonymType

/**
 * DSL for building a [List] of [SynonymType].
 */
@Suppress("PropertyName")
@DSLParameters
public class DSLSynonymType(
    private val synonymTypes: MutableList<SynonymType> = mutableListOf()
) {

    public val OneWay: SynonymType.OneWay = SynonymType.OneWay
    public val MultiWay: SynonymType.MultiWay = SynonymType.MultiWay
    public val Placeholder: SynonymType.Placeholder = SynonymType.Placeholder
    public val AlternativeCorrectionsOneTypo: SynonymType.Typo = SynonymType.Typo.One
    public val AlternativeCorrectionsTwoTypos: SynonymType.Typo = SynonymType.Typo.Two

    /**
     * Add [this] to [synonymTypes].
     */
    public operator fun SynonymType.unaryPlus() {
        synonymTypes += this
    }

    /**
     * Create and add a [SynonymType.AlternativeCorrections] using [this].
     */
    public operator fun SynonymType.Typo.unaryPlus() {
        +SynonymType.AlternativeCorrections(this)
    }

    public companion object : DSL<DSLSynonymType, List<SynonymType>> {

        override operator fun invoke(block: DSLSynonymType.() -> Unit): List<SynonymType> {
            return DSLSynonymType().apply(block).synonymTypes
        }
    }
}
