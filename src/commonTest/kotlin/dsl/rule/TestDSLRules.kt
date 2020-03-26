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
import com.algolia.search.model.rule.Pattern
import com.algolia.search.model.rule.Promotion
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.rule.TimeRange
import kotlin.test.Test
import kotlinx.serialization.json.json
import objectIDA
import objectIDB
import shouldEqual

internal class TestDSLRules {

    @Test
    fun default() {
        val dsl = DSLRules {
            rule(
                objectID = objectIDA,
                condition = condition(Contains, Literal("value")),
                consequence = consequence(
                    automaticFacetFilters = automaticFacetFilters { +attributeA },
                    automaticOptionalFacetFilters = automaticFacetFilters { +attributeB },
                    edits = edits { +"value" },
                    filterPromotes = true,
                    promote = promotions { +objectIDA(10) },
                    query = query { },
                    userData = json { },
                    hide = objectIDs { +objectIDB }
                ),
                enabled = true,
                description = "hello",
                validity = timeRanges { +(0L..10L) }
            )
        }

        dsl shouldEqual listOf(
            Rule(
                objectID = objectIDA,
                condition = Condition(Anchoring.Contains, Pattern.Literal("value")),
                consequence = Consequence(
                    automaticFacetFilters = listOf(AutomaticFacetFilters(attributeA)),
                    automaticOptionalFacetFilters = listOf(AutomaticFacetFilters(attributeB)),
                    edits = listOf(Edit("value")),
                    filterPromotes = true,
                    promote = listOf(Promotion(objectIDA, 10)),
                    query = query { },
                    userData = json { },
                    hide = listOf(objectIDB)
                ),
                enabled = true,
                description = "hello",
                validity = listOf(TimeRange(0, 10))
            )
        )
    }

    @Test
    fun anchoring() {
        DSLRules {
            Is shouldEqual Anchoring.Is
            StartsWith shouldEqual Anchoring.StartsWith
            EndsWith shouldEqual Anchoring.EndsWith
            Contains shouldEqual Anchoring.Contains
        }
    }

    @Test
    fun pattern() {
        DSLRules {
            Facet("hello") shouldEqual Pattern.Facet(Attribute("hello"))
            Literal("hello") shouldEqual Pattern.Literal("hello")
        }
    }
}
