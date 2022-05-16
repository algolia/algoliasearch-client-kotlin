package model.search

import com.algolia.search.model.search.AlternativesAsExact.IgnorePlurals
import com.algolia.search.model.search.AlternativesAsExact.MultiWordsSynonym
import com.algolia.search.model.search.AlternativesAsExact.Other
import com.algolia.search.model.search.AlternativesAsExact.SingleWordSynonym
import com.algolia.search.serialize.internal.KeyIgnorePlurals
import com.algolia.search.serialize.internal.KeyMultiWordsSynonym
import com.algolia.search.serialize.internal.KeySingleWordSynonym
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
