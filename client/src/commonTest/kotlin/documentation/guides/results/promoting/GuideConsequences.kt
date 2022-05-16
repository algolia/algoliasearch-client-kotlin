package documentation.guides.results.promoting

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.rule.rules
import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideConsequences {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                attributesForFaceting {
                    +"director"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val director = Attribute("director")
            val rules = rules {
                rule(
                    "director_rule",
                    conditions {
                        +Condition(Contains, Facet(director))
                    },
                    Consequence(
                        automaticFacetFilters { +director },
                        edits = edits { +"director" }
                    )
                )
            }

            index.saveRules(rules)
        }
    }
}
