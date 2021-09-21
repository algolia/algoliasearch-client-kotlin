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
import com.algolia.search.model.rule.FacetOrdering
import com.algolia.search.model.rule.FacetValuesOrder
import com.algolia.search.model.rule.FacetsOrder
import com.algolia.search.model.rule.Pattern
import com.algolia.search.model.rule.Promotion
import com.algolia.search.model.rule.RenderingContent
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.SortRule
import com.algolia.search.model.rule.TimeRange
import kotlinx.serialization.json.buildJsonObject
import objectIDA
import objectIDB
import shouldEqual
import kotlin.test.Test

internal class TestDSLRules {

    @Test
    fun default() {
        val facetOrdering = FacetOrdering(
            facets = FacetsOrder(
                order = listOf("size", "brand", "country", "color")
            ),
            values = mapOf(
                Attribute("brand") to FacetValuesOrder(
                    order = listOf("Uniqlo"),
                    sortRemainingBy = SortRule.Alpha
                ),
                Attribute("color") to FacetValuesOrder(
                    order = listOf("red", "green", "blue"),
                    sortRemainingBy = SortRule.Count
                ),
                Attribute("country") to FacetValuesOrder(
                    order = listOf("France", "Germany", "Finland"),
                    sortRemainingBy = SortRule.Hidden
                ),
                Attribute("size") to FacetValuesOrder(
                    order = emptyList(),
                    sortRemainingBy = SortRule.Count
                )
            )
        )

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
                    renderingContent = renderingContent(facetOrdering),
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
                    renderingContent = RenderingContent(facetOrdering),
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
