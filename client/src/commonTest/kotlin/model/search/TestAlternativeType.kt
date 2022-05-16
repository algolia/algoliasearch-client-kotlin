package model.search

import com.algolia.search.model.search.AlternativeType
import com.algolia.search.serialize.internal.KeyAltcorrection
import com.algolia.search.serialize.internal.KeyCompound
import com.algolia.search.serialize.internal.KeyConcat
import com.algolia.search.serialize.internal.KeyExcluded
import com.algolia.search.serialize.internal.KeyOptional
import com.algolia.search.serialize.internal.KeyOriginal
import com.algolia.search.serialize.internal.KeyPlural
import com.algolia.search.serialize.internal.KeySplit
import com.algolia.search.serialize.internal.KeyStopWord
import com.algolia.search.serialize.internal.KeySynonym
import com.algolia.search.serialize.internal.KeyTypo
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
