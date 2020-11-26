package suite

import clientAnswers
import com.algolia.search.ExperimentalAlgoliaClientAPI
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.AnswersQuery
import com.algolia.search.model.search.Language
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import runBlocking
import shouldEqual
import kotlin.test.Test

internal class TestSuiteAnswers {

    private val indexName = IndexName("ted")
    private val answers = clientAnswers.initIndex(indexName)

    @OptIn(ExperimentalAlgoliaClientAPI::class)
    @Test
    fun withResult() {
        runBlocking {
            val query = AnswersQuery(
                query = "sir ken robinson",
                queryLanguages = listOf(Language.English)
            )

            val response = answers.findAnswers(query)

            response.nbHits shouldEqual 10
        }
    }

    @OptIn(ExperimentalAlgoliaClientAPI::class)
    @Test
    fun withoutResult() {
        runBlocking {
            val query = AnswersQuery(
                query = "what",
                queryLanguages = listOf(Language.English)
            )

            val response = answers.findAnswers(query)

            response.nbHits shouldEqual 0
        }
    }

    @OptIn(ExperimentalAlgoliaClientAPI::class)
    @Test
    fun withHighlight() {
        runBlocking {
            val query = AnswersQuery(
                query = "sarah jones",
                queryLanguages = listOf(Language.English),
                nbHits = 2,
            ).apply {
                highlightPreTag = "_pre_"
                highlightPostTag = "_post_"
            }

            val response = answers.findAnswers(query)

            response.nbHits shouldEqual 2
            response.hits[0]
                .highlightResult["main_speaker"]
                ?.jsonObject?.get("value")
                ?.jsonPrimitive?.content shouldEqual "_pre_Sarah_post_ _pre_Jones_post_"

        }
    }
}
