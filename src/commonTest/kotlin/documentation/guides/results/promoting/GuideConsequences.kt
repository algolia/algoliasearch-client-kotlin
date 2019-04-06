package documentation.guides.results.promoting

import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.settings
import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.*
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
            val rule = Rule(
                objectID = ObjectID("director-rule"),
                condition = Condition(Anchoring.Contains, Pattern.Facet(director)),
                consequence = Consequence(
                    automaticFacetFilters = listOf(AutomaticFacetFilters(director)),
                    edits = listOf(Edit("director"))
                )
            )

            index.saveRule(rule)
        }
    }
}