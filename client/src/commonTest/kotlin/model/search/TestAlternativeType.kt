package model.search

import com.algolia.search.model.search.AlternativeType
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestAlternativeType {

    @Test
    fun raw() {
        AlternativeType.Original.raw shouldEqual Key.Original
        AlternativeType.Excluded.raw shouldEqual Key.Excluded
        AlternativeType.Optional.raw shouldEqual Key.Optional
        AlternativeType.StopWord.raw shouldEqual Key.StopWord
        AlternativeType.Typo.raw shouldEqual Key.Typo
        AlternativeType.Split.raw shouldEqual Key.Split
        AlternativeType.Concat.raw shouldEqual Key.Concat
        AlternativeType.Synonym.raw shouldEqual Key.Synonym
        AlternativeType.AlternativeCorrection.raw shouldEqual Key.Altcorrection
        AlternativeType.Plural.raw shouldEqual Key.Plural
        AlternativeType.Compound.raw shouldEqual Key.Compound
        AlternativeType.Other(unknown).raw shouldEqual unknown
    }
}
