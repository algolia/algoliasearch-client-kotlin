package model.search

import com.algolia.search.model.search.AlternativesAsExact.*
import com.algolia.search.serialize.KeyIgnorePlurals
import com.algolia.search.serialize.KeyMultiWordsSynonym
import com.algolia.search.serialize.KeySingleWordSynonym
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestAlternativesAsExact {

    @Test
    fun raw() {
        IgnorePlurals.raw shouldEqual KeyIgnorePlurals
        SingleWordSynonym.raw shouldEqual KeySingleWordSynonym
        MultiWordsSynonym.raw shouldEqual KeyMultiWordsSynonym
        Other(unknown).raw shouldEqual unknown
    }
}