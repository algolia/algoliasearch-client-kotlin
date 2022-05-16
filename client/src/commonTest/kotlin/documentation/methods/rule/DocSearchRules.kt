package documentation.methods.rule

import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.RuleQuery
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocSearchRules {

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
    fun snippet1() {
        runTest {
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
