package suite

import clientAnswers
import com.algolia.search.ExperimentalAlgoliaClientAPI
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.AnswersQuery
import com.algolia.search.model.search.Language
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFalse

internal class TestSuiteAnswers {

    private val indexName = IndexName("ted")
    private val answers = clientAnswers.initIndex(indexName)

    @OptIn(ExperimentalAlgoliaClientAPI::class)
    @Test
    fun testFindAnswers() {
        runTest {
            val query = AnswersQuery(
                query = "when do babies start learning?",
                queryLanguages = listOf(Language.English),
                nbHits = 20,
                attributesForPrediction = listOf(Attribute("description"), Attribute("title"), Attribute("transcript"))
            )

            val response = answers.findAnswers(query)

            assertFalse(response.hits.map { it.answer }.isEmpty())
        }
    }
}
