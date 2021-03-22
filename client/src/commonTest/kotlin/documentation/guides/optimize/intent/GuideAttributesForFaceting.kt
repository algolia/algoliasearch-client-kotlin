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
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideAttributesForFaceting {

    @Test
    fun snippet1() {
        runBlocking {
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
        runBlocking {
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
