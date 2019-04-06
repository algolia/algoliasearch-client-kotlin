package documentation.guides.results.promoting

import com.algolia.search.dsl.objectIDs
import com.algolia.search.dsl.rule.rules
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideHideHits {

    @Test
    fun snippet() {
        runBlocking {
            val rules = rules {
                +rule(
                    "hide-12345",
                    Condition(Contains, Literal("harry potter")),
                    Consequence(hide = objectIDs { +"HP-12345" })
                )
            }

            index.saveRules(rules)
        }
    }
}