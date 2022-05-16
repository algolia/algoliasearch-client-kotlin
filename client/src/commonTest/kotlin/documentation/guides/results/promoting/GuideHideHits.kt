package documentation.guides.results.promoting

import com.algolia.search.dsl.filters
import com.algolia.search.dsl.objectIDs
import com.algolia.search.dsl.query
import com.algolia.search.dsl.rule.rules
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import com.algolia.search.model.rule.Pattern
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideHideHits {

    @Test
    fun snippet1() {
        runTest {
            val rules = rules {
                rule(
                    objectID = "hide-12345",
                    conditions = conditions {
                        +Condition(Contains, Literal("harry potter"))
                    },
                    consequence = Consequence(hide = objectIDs { +"HP-12345" })
                )
            }

            index.saveRules(rules)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val rules = rules {
                rule(
                    objectID = "hide-shirts",
                    conditions = conditions {
                        +Condition(
                            pattern = Pattern.Literal("banana"),
                            anchoring = Anchoring.Is
                        )
                    },
                    consequence = Consequence(
                        query = query {
                            filters {
                                and { facet("clothing-type", "t-shirts", isNegated = true) }
                            }
                        }
                    )
                )
            }

            index.saveRules(rules)
        }
    }
}
