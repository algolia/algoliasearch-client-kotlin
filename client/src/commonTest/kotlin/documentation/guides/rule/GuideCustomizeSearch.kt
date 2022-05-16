package documentation.guides.rule

import com.algolia.search.client.Index
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.search.Query
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore

@Ignore
class GuideCustomizeSearch {

    private val movies: Index = TODO()

    /** save contextual **/
    fun snippet1() {
        runTest {
            val rule = Rule(
                objectID = ObjectID("a-rule-id"),
                conditions = listOf(
                    Condition(
                        context = "mobile"
                    )
                ),
                consequence = Consequence(
                    query = Query(filters = "release_date >= 1577836800")
                ),
            )

            movies.saveRule(rule)
        }
    }
}
