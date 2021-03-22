package serialize.serializer

import com.algolia.search.model.analytics.Variant
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.KSerializerVariant
import com.algolia.search.serialize.KeyCustomSearchParameters
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyPercentage
import com.algolia.search.serialize.internal.JsonNoDefaults
import indexA
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestKSerializerVariant : TestSerializer<Variant>(KSerializerVariant) {

    private val query = Query(query = "stuff", sumOrFiltersScores = true)

    override val items = listOf(
        Variant(
            indexName = indexA,
            customSearchParameters = query,
            trafficPercentage = 10
        ) to buildJsonObject {
            put(KeyIndexName, indexA.raw)
            put(KeyPercentage, 10)
            put(KeyCustomSearchParameters, JsonNoDefaults.encodeToJsonElement(Query.serializer(), query))
        }
    )
}
