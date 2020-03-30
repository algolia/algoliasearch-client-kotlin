package model.synonym

import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.KeyAlternativeCorrection1
import com.algolia.search.serialize.KeyAlternativeCorrection2
import com.algolia.search.serialize.KeyOneWaySynonym
import com.algolia.search.serialize.KeyPlaceholder
import com.algolia.search.serialize.KeySynonym
import kotlin.test.Test
import shouldEqual
import unknown

internal class TestSynonymType {

    @Test
    fun raw() {
        SynonymType.OneWay.raw shouldEqual KeyOneWaySynonym
        SynonymType.MultiWay.raw shouldEqual KeySynonym
        SynonymType.AlternativeCorrections(SynonymType.Typo.One).raw shouldEqual KeyAlternativeCorrection1
        SynonymType.AlternativeCorrections(SynonymType.Typo.Two).raw shouldEqual KeyAlternativeCorrection2
        SynonymType.Placeholder.raw shouldEqual KeyPlaceholder
        SynonymType.Other(unknown).raw shouldEqual unknown
    }
}
