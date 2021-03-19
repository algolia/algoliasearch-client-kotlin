package dsl.rule

import attributeA
import attributeB
import com.algolia.search.dsl.objectIDs
import com.algolia.search.dsl.query
import com.algolia.search.dsl.rule.DSLRules
import com.algolia.search.model.Attribute
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.AutomaticFacetFilters
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import com.algolia.search.model.rule.Edit
import com.algolia.search.model.rule.FacetMerchandising
import com.algolia.search.model.rule.Pattern
import com.algolia.search.model.rule.Promotion
import com.algolia.search.model.rule.Redirect
import com.algolia.search.model.rule.RenderingContent
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.TimeRange
import kotlin.test.Test
import kotlinx.serialization.json.buildJsonObject
import objectIDA
import objectIDB
import shouldEqual

internal class TestDSLRules {

    @Test
    fun default() {
        val dsl = DSLRules {
            rule(
                objectID = objectIDA,
                conditions = conditions {
                    +condition(Contains, Literal("value"))
                },
                consequence = consequence(
                    automaticFacetFilters = automaticFacetFilters { +attributeA },
                    automaticOptionalFacetFilters = automaticFacetFilters { +attributeB },
                    edits = edits { +"value" },
                    filterPromotes = true,
                    promote = promotions { +objectIDA(10) },
                    query = query { },
                    userData = buildJsonObject { },
                    hide = objectIDs { +objectIDB },
                    renderingContent = renderingContent(
                        redirect = redirect("http://algolia.com/kotlin"),
                        facetMerchandising = facetMerchandising(listOf(Attribute("brand"))),
                        userData = listOf(buildJsonObject { })
                    )
                ),
                enabled = true,
                description = "hello",
                validity = timeRanges { +(0L..10L) }
            )
        }

        dsl shouldEqual listOf(
            Rule(
                objectID = objectIDA,
                conditions = listOf(Condition(Anchoring.Contains, Pattern.Literal("value"))),
                consequence = Consequence(
                    automaticFacetFilters = listOf(AutomaticFacetFilters(attributeA)),
                    automaticOptionalFacetFilters = listOf(AutomaticFacetFilters(attributeB)),
                    edits = listOf(Edit("value")),
                    filterPromotes = true,
                    promote = listOf(Promotion(objectIDA, 10)),
                    query = query { },
                    userData = buildJsonObject { },
                    hide = listOf(objectIDB),
                    renderingContent = RenderingContent(
                        redirect = Redirect("http://algolia.com/kotlin"),
                        facetMerchandising = FacetMerchandising(listOf(Attribute("brand"))),
                        userData = listOf(buildJsonObject { })
                    )
                ),
                enabled = true,
                description = "hello",
                validity = listOf(TimeRange(0, 10))
            )
        )
    }

    @Test
    fun pattern() {
        DSLRules {
            Facet("hello") shouldEqual Pattern.Facet(Attribute("hello"))
        }
    }
}
