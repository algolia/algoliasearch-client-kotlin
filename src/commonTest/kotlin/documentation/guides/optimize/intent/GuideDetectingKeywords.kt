package documentation.guides.optimize.intent

import com.algolia.search.dsl.query
import com.algolia.search.dsl.restrictSearchableAttributes
import com.algolia.search.dsl.rule.rules
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideDetectingKeywords {

    @Test
    fun snippet1() {
        runBlocking {
            val rules = rules {
                rule(
                    "rule",
                    Condition(StartsWith, Literal("article")),
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
