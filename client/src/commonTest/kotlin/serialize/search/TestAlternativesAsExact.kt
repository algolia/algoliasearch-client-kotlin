package serialize.search

import com.algolia.search.model.search.AlternativesAsExact
import com.algolia.search.model.search.AlternativesAsExact.IgnorePlurals
import com.algolia.search.model.search.AlternativesAsExact.MultiWordsSynonym
import com.algolia.search.model.search.AlternativesAsExact.Other
import com.algolia.search.model.search.AlternativesAsExact.SingleWordSynonym
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestAlternativesAsExact : TestSerializer<AlternativesAsExact>(AlternativesAsExact) {

    override val items = listOf(
        IgnorePlurals to JsonPrimitive(IgnorePlurals.raw),
        SingleWordSynonym to JsonPrimitive(SingleWordSynonym.raw),
        MultiWordsSynonym to JsonPrimitive(MultiWordsSynonym.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
