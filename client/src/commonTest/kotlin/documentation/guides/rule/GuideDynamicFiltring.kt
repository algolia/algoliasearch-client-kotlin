package documentation.guides.rule

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.AutomaticFacetFilters
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import com.algolia.search.model.rule.Edit
import com.algolia.search.model.rule.Pattern
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.search.Query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore

@Ignore
class GuideDynamicFiltring {

    /** API filter rule example **/
    fun snippet1() {
        runTest {
            val rule = Rule(
                objectID = ObjectID("red-color"),
                conditions = listOf(
                    Condition(anchoring = Anchoring.Contains, pattern = Pattern.Literal("red"))
                ),
                consequence = Consequence(
                    query = Query(filters = "color:red"),
                    edits = listOf(Edit(delete = "read"))
                )
            )

            index.saveRule(rule)
        }
    }

    /** API facet rule example **/
    fun snippet2() {
        runTest {
            val rule = Rule(
                objectID = ObjectID("color-facets"),
                conditions = listOf(
                    Condition(
                        anchoring = Anchoring.Contains,
                        pattern = Pattern.Literal("{facet:color}")
                    )
                ),
                consequence = Consequence(
                    automaticFacetFilters = listOf(
                        AutomaticFacetFilters(attribute = Attribute("color"))
                    )
                )
            )

            index.saveRule(rule)
        }
    }

    /** API filter by type **/
    fun snippet3() {
        runTest {
            val rule = Rule(
                objectID = ObjectID("t-shirt"),
                conditions = listOf(
                    Condition(
                        anchoring = Anchoring.Contains,
                        pattern = Pattern.Literal("t-shirt")
                    )
                ),
                consequence = Consequence(
                    edits = listOf(Edit(delete = "t-shirt")),
                    query = Query(filters = "clothing_type:shirt")
                )
            )

            index.saveRule(rule)
        }
    }

    /** API numerical filtering **/
    fun snippet4() {
        runTest {
            val toasterRule = Rule(
                objectID = ObjectID("toaster"),
                conditions = listOf(
                    Condition(anchoring = Anchoring.Contains, pattern = Pattern.Literal("toaster"))
                ),
                consequence = Consequence(
                    query = Query(filters = "product_type:toaster"),
                    edits = listOf(Edit(delete = "toaster"))
                )
            )

            val cheapRule = Rule(
                objectID = ObjectID("cheap"),
                conditions = listOf(
                    Condition(anchoring = Anchoring.Contains, pattern = Pattern.Literal("cheap"))
                ),
                consequence = Consequence(
                    query = Query(filters = "price < 10"),
                    edits = listOf(Edit(delete = "cheap"))
                )
            )

            index.saveRules(listOf(toasterRule, cheapRule))
        }
    }
}
