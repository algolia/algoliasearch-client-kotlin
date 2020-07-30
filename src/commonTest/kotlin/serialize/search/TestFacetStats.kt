package serialize.search

import com.algolia.search.model.search.FacetStats
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestFacetStats : TestSerializer<FacetStats>(FacetStats.serializer()) {

    override val items = listOf(
        facetStats to jsonObject
    )

    companion object {

        val facetStats = FacetStats(0f, 1f, 2f, 3f)
        val jsonObject = buildJsonObject {
            put("min", 0.0)
            put("max", 1.0)
            put("avg", 2f)
            put("sum", 3f)
        }
    }
}
