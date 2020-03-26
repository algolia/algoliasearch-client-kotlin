package model.synonym

import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.*
import shouldEqual
import unknown
import kotlin.test.Test


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