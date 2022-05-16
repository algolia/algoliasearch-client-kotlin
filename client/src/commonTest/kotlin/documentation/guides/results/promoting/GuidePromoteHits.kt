package documentation.guides.results.promoting

import com.algolia.search.dsl.rule.rules
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuidePromoteHits {

    @Test
    fun snippet1() {
        runTest {
            val rules = rules {
                rule(
                    "Promote Harry Potter Box Set",
                    conditions {
                        +Condition(Contains, Literal("Harry Potter"))
                    },
                    Consequence(promote = promotions { +ObjectID("HP-12345")(0) })
                )
            }

            index.saveRules(rules)
        }
    }
}
