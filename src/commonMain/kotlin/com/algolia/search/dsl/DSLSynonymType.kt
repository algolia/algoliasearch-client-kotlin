package com.algolia.search.dsl

import com.algolia.search.model.synonym.SynonymType

/**
 * DSL for building a [List] of [SynonymType].
 */
@Suppress("PropertyName")
@DSLParameters
class DSLSynonymType(
    private val synonymTypes: MutableList<SynonymType> = mutableListOf()
) {

    val OneWay = SynonymType.OneWay
    val MultiWay = SynonymType.MultiWay
    val Placeholder = SynonymType.Placeholder
    val AlternativeCorrectionsOneTypo = SynonymType.Typo.One
    val AlternativeCorrectionsTwoTypos = SynonymType.Typo.Two

    /**
     * Add [this] to [synonymTypes].
     */
    operator fun SynonymType.unaryPlus() {
        synonymTypes += this
    }

    /**
     * Create and add a [SynonymType.AlternativeCorrections] using [this].
     */
    operator fun SynonymType.Typo.unaryPlus() {
        +SynonymType.AlternativeCorrections(this)
    }

    companion object : DSL<DSLSynonymType, List<SynonymType>> {

        override operator fun invoke(block: DSLSynonymType.() -> Unit): List<SynonymType> {
            return DSLSynonymType().apply(block).synonymTypes
        }
    }
}
