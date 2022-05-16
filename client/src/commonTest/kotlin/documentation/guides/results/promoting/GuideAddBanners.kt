package documentation.guides.results.promoting

import com.algolia.search.dsl.rule.rules
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideAddBanners {

    @Test
    fun snippet1() {
        runTest {
            val rules = rules {
                rule(
                    "a-rule-id",
                    conditions {
                        +Condition(Contains, Literal("harry potter"))
                    },
                    Consequence(
                        userData = buildJsonObject {
                            put("promo_content", "20% OFF on all Harry Potter books!")
                        }
                    ),
                    enabled = false
                )
            }

            index.saveRules(rules)
        }
    }
}
