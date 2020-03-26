package documentation.guides.results.promoting

import com.algolia.search.dsl.rule.rules
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class GuidePromoteHits {

    @Test
    fun snippet1() {
        runBlocking {
            val rules = rules {
                rule(
                    "Promote Harry Potter Box Set",
                    Condition(Contains, Literal("Harry Potter")),
                    Consequence(promote = promotions { +ObjectID("HP-12345")(0) })
                )
            }

            index.saveRules(rules)
        }
    }
}
