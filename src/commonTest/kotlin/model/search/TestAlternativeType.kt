package model.search

import com.algolia.search.model.search.AlternativeType
import com.algolia.search.serialize.*
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestAlternativeType {

    @Test
    fun raw() {
        AlternativeType.Original.raw shouldEqual KeyOriginal
        AlternativeType.Excluded.raw shouldEqual KeyExcluded
        AlternativeType.Optional.raw shouldEqual KeyOptional
        AlternativeType.StopWord.raw shouldEqual KeyStopWord
        AlternativeType.Typo.raw shouldEqual KeyTypo
        AlternativeType.Split.raw shouldEqual KeySplit
        AlternativeType.Concat.raw shouldEqual KeyConcat
        AlternativeType.Synonym.raw shouldEqual KeySynonym
        AlternativeType.AlternativeCorrection.raw shouldEqual KeyAltcorrection
        AlternativeType.Plural.raw shouldEqual KeyPlural
        AlternativeType.Compound.raw shouldEqual KeyCompound
        AlternativeType.Other(unknown).raw shouldEqual unknown
    }
}