package model.synonym

import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestSynonymType {

    @Test
    fun raw() {
        SynonymType.OneWay.raw shouldEqual Key.OneWaySynonym
        SynonymType.MultiWay.raw shouldEqual Key.Synonym
        SynonymType.AlternativeCorrections(SynonymType.Typo.One).raw shouldEqual Key.AlternativeCorrection1
        SynonymType.AlternativeCorrections(SynonymType.Typo.Two).raw shouldEqual Key.AlternativeCorrection2
        SynonymType.Placeholder.raw shouldEqual Key.Placeholder
        SynonymType.Other(unknown).raw shouldEqual unknown
    }
}
