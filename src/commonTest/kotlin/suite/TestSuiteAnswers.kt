package suite

import clientAnswers
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.answers.AnswersQuery
import com.algolia.search.model.search.Language
import runBlocking
import shouldEqual
import kotlin.test.Test

internal class TestSuiteAnswers {

    private val indexName = IndexName("ted")
    private val answers = clientAnswers.initIndex(indexName)

    @Test
    fun test() {
        runBlocking {
            val query = AnswersQuery(
                query = "when do babies start learning?",
                queryLanguages = listOf(Language.English),
                attributesForPrediction = listOf(Attribute("description"), Attribute("title"), Attribute("transcript")),
                nbHits = 20
            )

            val response = answers.findAnswers(query)
            val answer = response.hits[0].answer

            response.nbHits shouldEqual 20
            answer.score shouldEqual 212.122
        }
    }
}
