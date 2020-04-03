package documentation.parameters.rule

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import com.algolia.search.model.rule.Pattern
import com.algolia.search.model.rule.Promotion
import com.algolia.search.model.rule.Rule
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Suppress("UNUSED_VARIABLE")
@Ignore
class DocFilterPromotes {

//    filterPromotes: Boolean = true|false

    @Test
    fun snippet1() {
        runBlocking {
            val rule = Rule(
                objectID = ObjectID("rule_with_filterPromotes"),
                condition = Condition(Anchoring.Is, Pattern.Facet(Attribute("brand"))),
                consequence = Consequence(
                    filterPromotes = true,
                    promote = listOf(
                        Promotion(ObjectID("promoted_items"), position = 0)
                    )
                )
            )
        }
    }
}
