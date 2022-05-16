package documentation.guides.rule

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Alternatives
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.AutomaticFacetFilters
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import com.algolia.search.model.rule.Edit
import com.algolia.search.model.rule.Pattern
import com.algolia.search.model.rule.Rule
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore

@Ignore
class GuideTaggedQuery {

    /** API save rule tagged **/
    fun snippet1() {
        runTest {
            val rule = Rule(
                objectID = ObjectID("tagged-brand-rule"),
                conditions = listOf(
                    Condition(
                        anchoring = Anchoring.Contains,
                        pattern = Pattern.Literal("brand\\: {facet:brand.name}"),
                        alternative = Alternatives.False
                    )
                ),
                consequence = Consequence(
                    automaticFacetFilters = listOf(AutomaticFacetFilters(attribute = Attribute("brand.name"))),
                    edits = listOf(Edit(delete = "brand\\:"), Edit(delete = "{facet:brand.name}"))
                )
            )

            index.saveRule(rule)
        }
    }
}
