package data

import com.algolia.search.saas.model.SynonymType
import com.algolia.search.saas.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
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