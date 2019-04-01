package com.algolia.search.dsl

import com.algolia.search.model.synonym.SynonymType


@Suppress("PropertyName")
@DSLParameters
public class DSLSynonymType(
    private val synonymTypes: MutableList<SynonymType> = mutableListOf()
) {

    public val OneWay = SynonymType.OneWay
    public val MultiWay = SynonymType.MultiWay
    public val Placeholder = SynonymType.Placeholder
    public val TypoOne = SynonymType.Typo.One
    public val TypoTwo = SynonymType.Typo.Two

    public fun alternativeCorrections(typo: SynonymType.Typo): SynonymType.AlternativeCorrections {
        return SynonymType.AlternativeCorrections(typo)
    }

    operator fun SynonymType.unaryPlus() {
        synonymTypes += this
    }

    public fun build(): List<SynonymType> {
        return synonymTypes.toList()
    }
}