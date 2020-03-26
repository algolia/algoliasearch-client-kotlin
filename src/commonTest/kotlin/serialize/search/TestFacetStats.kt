package serialize.search

import com.algolia.search.model.search.FacetStats
import kotlinx.serialization.json.json
import serialize.TestSerializer


internal class TestFacetStats : TestSerializer<FacetStats>(FacetStats.serializer()) {

    override val items = listOf(
        facetStats to json
    )

    companion object {

        val facetStats = FacetStats(0f, 1f, 2f, 3f)
        val json = json {
            "min" to 0.0
            "max" to 1.0
            "avg" to 2f
            "sum" to 3f
        }
    }
}