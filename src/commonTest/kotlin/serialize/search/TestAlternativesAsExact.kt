package serialize.search

import com.algolia.search.model.search.AlternativesAsExact
import com.algolia.search.model.search.AlternativesAsExact.*
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestAlternativesAsExact : TestSerializer<AlternativesAsExact>(AlternativesAsExact) {

    override val items = listOf(
        IgnorePlurals to JsonLiteral(IgnorePlurals.raw),
        SingleWordSynonym to JsonLiteral(SingleWordSynonym.raw),
        MultiWordsSynonym to JsonLiteral(MultiWordsSynonym.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}