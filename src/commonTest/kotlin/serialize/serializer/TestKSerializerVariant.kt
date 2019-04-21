package serialize.serializer

import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import indexA
import kotlinx.serialization.json.json
import serialize.TestSerializer


internal class TestKSerializerVariant : TestSerializer<Variant>(KSerializerVariant) {

    private val query = Query(query = "stuff", sumOrFiltersScores = true)

    override val items = listOf(
        Variant(
            indexName = indexA,
            customSearchParameters = query,
            trafficPercentage = 10
        ) to json {
            KeyIndexName to indexA.raw
            KeyPercentage to 10
            KeyCustomSearchParameters to JsonNoDefaults.toJson(Query.serializer(), query)
        }
    )
}