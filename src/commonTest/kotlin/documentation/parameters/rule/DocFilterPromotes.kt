package documentation.parameters.rule

import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.*
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