package serialize.recommend

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.recommend.FrequentlyBoughtTogetherQuery
import com.algolia.search.model.recommend.RecommendationModel
import com.algolia.search.model.recommend.RecommendationsQuery
import com.algolia.search.model.recommend.RelatedProductsQuery
import com.algolia.search.model.search.RecommendSearchOptions
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
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
            put(Key.IndexName, JsonPrimitive("products"))
            put(Key.Model, JsonPrimitive("bought-together"))
            put(Key.ObjectID, JsonPrimitive("B018APC4LE"))
            put(Key.Threshold, JsonPrimitive(0))
            put(Key.MaxRecommendations, JsonPrimitive(10))
            put(
                Key.QueryParameters,
                buildJsonObject {
                    put(Key.AttributesToRetrieve, buildJsonArray { add(JsonPrimitive("*")) })
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
            put(Key.IndexName, JsonPrimitive("products"))
            put(Key.Model, JsonPrimitive("bought-together"))
            put(Key.ObjectID, JsonPrimitive("B018APC4LE"))
            put(Key.Threshold, JsonPrimitive(0))
            put(Key.MaxRecommendations, JsonPrimitive(10))
            put(
                Key.QueryParameters,
                buildJsonObject {
                    put(Key.AttributesToRetrieve, buildJsonArray { add(JsonPrimitive("*")) })
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
            put(Key.IndexName, JsonPrimitive("products"))
            put(Key.Model, JsonPrimitive("related-products"))
            put(Key.ObjectID, JsonPrimitive("B018APC4LE"))
            put(Key.Threshold, JsonPrimitive(10))
            put(Key.MaxRecommendations, JsonPrimitive(10))
            put(
                Key.QueryParameters,
                buildJsonObject {
                    put(Key.AttributesToRetrieve, buildJsonArray { add(JsonPrimitive("*")) })
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
            put(Key.IndexName, JsonPrimitive("products"))
            put(Key.Model, JsonPrimitive("bought-together"))
            put(Key.ObjectID, JsonPrimitive("B018APC4LE"))
            put(Key.Threshold, JsonPrimitive(10))
            put(Key.MaxRecommendations, JsonPrimitive(10))
            put(
                Key.QueryParameters,
                buildJsonObject {
                    put(Key.AttributesToRetrieve, buildJsonArray { add(JsonPrimitive("*")) })
                }
            )
        }
    }
}
