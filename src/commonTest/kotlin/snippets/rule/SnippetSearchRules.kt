package snippets.rule

import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.RuleQuery
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


class SnippetSearchRules : TestSnippets() {

//    suspend fun Index.searchRules(
//        query: __RuleQuery__ = RuleQuery(),
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseRules
//
//    @Serializable
//    data class RuleQuery(
//        val #{query}: __String?__ = null,
//        val #{anchoring}: __Anchoring?__ = null,
//        val #{context}: __String?__ = null,
//        val #{page}: __Int?__ = null,
//        val #{hitsPerPage}: __Int?__ = null,
//        val #{enabled}: __Boolean?__ = null
//    )

    @Test
    fun searchRules() {
        runBlocking {
            val rule = RuleQuery(
                anchoring = Anchoring.Is,
                page = 1,
                hitsPerPage = 10,
                context = "something"
            )

            index.searchRules(rule)
        }
    }
}