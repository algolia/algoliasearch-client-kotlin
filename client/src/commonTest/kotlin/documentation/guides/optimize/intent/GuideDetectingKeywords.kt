package documentation.guides.optimize.intent

import com.algolia.search.dsl.query
import com.algolia.search.dsl.restrictSearchableAttributes
import com.algolia.search.dsl.rule.rules
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideDetectingKeywords {

    @Test
    fun snippet1() {
        runTest {
            val rules = rules {
                rule(
                    "rule",
                    conditions {
                        +Condition(StartsWith, Literal("article"))
                    },
                    Consequence(
                        query = query {
                            restrictSearchableAttributes {
                                +"title"
                                +"book_id"
                            }
                        },
                        edits = edits { +"article" }
                    )
                )
            }

            index.saveRules(rules)
        }
    }
}
