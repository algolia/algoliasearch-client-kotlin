package documentation.guides.results.promoting

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.rule.rules
import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideConsequences {

    @Test
    fun snippet1() {
        runBlocking {
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
        runBlocking {
            val director = Attribute("director")
            val rules = rules {
                rule(
                    "director_rule",
                    Condition(Contains, Facet(director)),
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
