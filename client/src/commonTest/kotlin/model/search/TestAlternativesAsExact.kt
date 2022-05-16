package model.search

import com.algolia.search.model.search.AlternativesAsExact.IgnorePlurals
import com.algolia.search.model.search.AlternativesAsExact.MultiWordsSynonym
import com.algolia.search.model.search.AlternativesAsExact.Other
import com.algolia.search.model.search.AlternativesAsExact.SingleWordSynonym
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestAlternativesAsExact {

    @Test
    fun raw() {
        IgnorePlurals.raw shouldEqual Key.IgnorePlurals
        SingleWordSynonym.raw shouldEqual Key.SingleWordSynonym
        MultiWordsSynonym.raw shouldEqual Key.MultiWordsSynonym
        Other(unknown).raw shouldEqual unknown
    }
}
