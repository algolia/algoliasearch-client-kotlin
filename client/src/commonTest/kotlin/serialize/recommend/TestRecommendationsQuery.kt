package serialize.recommend

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.recommend.FrequentlyBoughtTogetherQuery
import com.algolia.search.model.recommend.RecommendationModel
import com.algolia.search.model.recommend.RecommendationsQuery
import com.algolia.search.model.recommend.RelatedProductsQuery
import com.algolia.search.model.search.RecommendSearchOptions
import com.algolia.search.serialize.internal.KeyAttributesToRetrieve
import com.algolia.search.serialize.internal.KeyIndexName
import com.algolia.search.serialize.internal.KeyMaxRecommendations
import com.algolia.search.serialize.internal.KeyModel
import com.algolia.search.serialize.internal.KeyObjectID
import com.algolia.search.serialize.internal.KeyQueryParameters
import com.algolia.search.serialize.internal.KeyThreshold
import com.algolia.search.serialize.internal.JsonNoDefaults
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
import shouldEqual
import kotlin.test.Test

internal class TestRecommendationsQuery {

    @Test
    fun testRecommendationsQuery() {
        val query = RecommendationsQuery(
            indexName = IndexName("products"),
            model = RecommendationModel.BoughtTogether,
            objectID = ObjectID("B018APC4LE"),
            threshold = 0,
            maxRecommendations = 10,
            queryParameters = RecommendSearchOptions(
                attributesToRetrieve = listOf(Attribute("*")),
            ).apply { // all options bellow should be ignored
                hitsPerPage = 1
                page = 1
                offset = 1
                length = 1
            }
        )

        val json = JsonNoDefaults.encodeToJsonElement(query)
        json shouldEqual buildJsonObject {
            put(KeyIndexName, JsonPrimitive("products"))
            put(KeyModel, JsonPrimitive("bought-together"))
            put(KeyObjectID, JsonPrimitive("B018APC4LE"))
            put(KeyThreshold, JsonPrimitive(0))
            put(KeyMaxRecommendations, JsonPrimitive(10))
            put(
                KeyQueryParameters,
                buildJsonObject {
                    put(KeyAttributesToRetrieve, buildJsonArray { add(JsonPrimitive("*")) })
                }
            )
        }
    }

    @Test
    fun testRecommendationsQueryDefaultThreshold() {
        val query = RecommendationsQuery(
            indexName = IndexName("products"),
            model = RecommendationModel.BoughtTogether,
            objectID = ObjectID("B018APC4LE"),
            maxRecommendations = 10,
            queryParameters = RecommendSearchOptions(
                attributesToRetrieve = listOf(Attribute("*")),
            ).apply { // all options bellow should be ignored
                hitsPerPage = 1
                page = 1
                offset = 1
                length = 1
            }
        )

        val json = JsonNoDefaults.encodeToJsonElement(query)
        json shouldEqual buildJsonObject {
            put(KeyIndexName, JsonPrimitive("products"))
            put(KeyModel, JsonPrimitive("bought-together"))
            put(KeyObjectID, JsonPrimitive("B018APC4LE"))
            put(KeyThreshold, JsonPrimitive(0))
            put(KeyMaxRecommendations, JsonPrimitive(10))
            put(
                KeyQueryParameters,
                buildJsonObject {
                    put(KeyAttributesToRetrieve, buildJsonArray { add(JsonPrimitive("*")) })
                }
            )
        }
    }

    @Test
    fun testRelatedProducts() {
        val query = RelatedProductsQuery(
            indexName = IndexName("products"),
            objectID = ObjectID("B018APC4LE"),
            threshold = 10,
            maxRecommendations = 10,
            queryParameters = RecommendSearchOptions(
                attributesToRetrieve = listOf(Attribute("*"))
            )
        )

        val json = JsonNoDefaults.encodeToJsonElement(query)
        json shouldEqual buildJsonObject {
            put(KeyIndexName, JsonPrimitive("products"))
            put(KeyModel, JsonPrimitive("related-products"))
            put(KeyObjectID, JsonPrimitive("B018APC4LE"))
            put(KeyThreshold, JsonPrimitive(10))
            put(KeyMaxRecommendations, JsonPrimitive(10))
            put(
                KeyQueryParameters,
                buildJsonObject {
                    put(KeyAttributesToRetrieve, buildJsonArray { add(JsonPrimitive("*")) })
                }
            )
        }
    }

    @Test
    fun testFrequencyBoughtTogether() {
        val query = FrequentlyBoughtTogetherQuery(
            indexName = IndexName("products"),
            objectID = ObjectID("B018APC4LE"),
            threshold = 10,
            maxRecommendations = 10,
            queryParameters = RecommendSearchOptions(
                attributesToRetrieve = listOf(Attribute("*"))
            )
        )

        val json = JsonNoDefaults.encodeToJsonElement(query)
        json shouldEqual buildJsonObject {
            put(KeyIndexName, JsonPrimitive("products"))
            put(KeyModel, JsonPrimitive("bought-together"))
            put(KeyObjectID, JsonPrimitive("B018APC4LE"))
            put(KeyThreshold, JsonPrimitive(10))
            put(KeyMaxRecommendations, JsonPrimitive(10))
            put(
                KeyQueryParameters,
                buildJsonObject {
                    put(KeyAttributesToRetrieve, buildJsonArray { add(JsonPrimitive("*")) })
                }
            )
        }
    }
}
