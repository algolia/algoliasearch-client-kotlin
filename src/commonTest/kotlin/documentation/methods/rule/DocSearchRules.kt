package documentation.methods.rule

import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.RuleQuery
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocSearchRules : TestDocumentation() {

//    suspend fun Index.searchRules(
//        query: __RuleQuery__ = RuleQuery(),
//        requestOptions: __RequestOptions?__ = null
//    ): ResponseRules
//
//    @Serializable
//    data class RuleQuery(
//        var #{query}: __String?__ = null,
//        var #{anchoring}: __Anchoring?__ = null,
//        var #{context}: __String?__ = null,
//        var #{page}: __Int?__ = null,
//        var #{hitsPerPage}: __Int?__ = null,
//        var #{enabled}: __Boolean?__ = null
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