package documentation.guides.optimize.intent

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.rule.rules
import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import com.algolia.search.model.search.Query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideAttributesForFaceting {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                customRanking {
                    +Desc("nb_airline_liaisons")
                }
                attributesForFaceting {
                    +"city"
                    +"country"
                }
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val country = Attribute("country")
            val city = Attribute("city")
            val rules = rules {
                rule(
                    "country",
                    conditions {
                        +Condition(Contains, Facet(country))
                    },
                    Consequence(query = Query(aroundLatLngViaIP = false))
                )
                rule(
                    "city",
                    conditions {
                        +Condition(Contains, Facet(city))
                    },
                    Consequence(query = Query(aroundLatLngViaIP = false))
                )
            }

            index.saveRules(rules)
        }
    }
}
