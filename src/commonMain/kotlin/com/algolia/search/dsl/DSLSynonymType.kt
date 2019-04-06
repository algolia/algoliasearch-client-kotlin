package com.algolia.search.dsl

import com.algolia.search.model.synonym.SynonymType


@Suppress("PropertyName")
@DSLParameters
public class DSLSynonymType(
    private val synonymTypes: MutableList<SynonymType> = mutableListOf()
) {

    public enum class AlternativeCorrections(val typo: SynonymType.Typo) {
        One(SynonymType.Typo.One),
        Two(SynonymType.Typo.Two)
    }

    public val OneWay = SynonymType.OneWay
    public val MultiWay = SynonymType.MultiWay
    public val Placeholder = SynonymType.Placeholder
    public val AlternativeCorrectionsOneTypo = AlternativeCorrections.One
    public val AlternativeCorrectionsTwoTypos = AlternativeCorrections.Two

    public operator fun SynonymType.unaryPlus() {
        synonymTypes += this
    }

    public operator fun AlternativeCorrections.unaryPlus() {
        +SynonymType.AlternativeCorrections(typo)
    }

    public companion object : DSL<DSLSynonymType, List<SynonymType>> {

        override operator fun invoke(block: DSLSynonymType.() -> Unit): List<SynonymType> {
            return DSLSynonymType().apply(block).synonymTypes
        }
    }
}