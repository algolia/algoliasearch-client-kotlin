package serialize.serializer

import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KSerializerVariant
import com.algolia.search.serialize.KeyCustomSearchParameters
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyPercentage
import indexA
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
