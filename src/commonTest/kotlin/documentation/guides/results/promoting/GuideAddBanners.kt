package documentation.guides.results.promoting

import com.algolia.search.dsl.rule.rules
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import documentation.index
import kotlinx.serialization.json.json
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideAddBanners {

    @Test
    fun snippet1() {
        runBlocking {
            val rules = rules {
                +rule(
                    "a-rule-id",
                    Condition(Contains, Literal("harry potter")),
                    Consequence(userData = json { "promo_content" to "20% OFF on all Harry Potter books!" }),
                    enabled = false
                )
            }

            index.saveRules(rules)
        }
    }
}