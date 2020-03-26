package model.search

import com.algolia.search.model.search.AlternativeType
import com.algolia.search.serialize.KeyAltcorrection
import com.algolia.search.serialize.KeyCompound
import com.algolia.search.serialize.KeyConcat
import com.algolia.search.serialize.KeyExcluded
import com.algolia.search.serialize.KeyOptional
import com.algolia.search.serialize.KeyOriginal
import com.algolia.search.serialize.KeyPlural
import com.algolia.search.serialize.KeySplit
import com.algolia.search.serialize.KeyStopWord
import com.algolia.search.serialize.KeySynonym
import com.algolia.search.serialize.KeyTypo
import kotlin.test.Test
import shouldEqual
import unknown

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
