package serialize.answers

import com.algolia.search.ExperimentalAlgoliaClientAPI
import com.algolia.search.model.Attribute
import com.algolia.search.model.search.AnswersQuery
import com.algolia.search.model.search.Language
import com.algolia.search.serialize.internal.JsonNoDefaults
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

@OptIn(ExperimentalAlgoliaClientAPI::class)
internal class TestAnswers : TestSerializer<AnswersQuery>(AnswersQuery.serializer(), JsonNoDefaults) {

    override val items: List<Pair<AnswersQuery, JsonElement>> = listOf(

        AnswersQuery(
            query = "query",
            queryLanguages = listOf(Language.English),
            attributesForPrediction = listOf(Attribute("a1"), Attribute("a2")),
            nbHits = 10,
            threshold = 20f
        ) to buildJsonObject {
            put(Key.Query, "query")
            put(Key.QueryLanguages, buildJsonArray { add(Language.English.raw) })
            put(
                Key.AttributesForPrediction,
                buildJsonArray {
                    add("a1")
                    add("a2")
                }
            )
            put(Key.NbHits, 10)
            put(Key.Threshold, 20f)
        },

        AnswersQuery(
            query = "query",
            queryLanguages = listOf(Language.English),
        ).apply {
            filters = "brand:sony"
        } to buildJsonObject {
            put(Key.Query, "query")
            put(Key.QueryLanguages, buildJsonArray { add(Language.English.raw) })
            put(
                Key.Params,
                buildJsonObject {
                    put(Key.Filters, "brand:sony")
                }
            )
        }
    )
}
