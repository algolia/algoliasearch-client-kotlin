package documentation.guides.results.promoting

import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.*
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideHideHits {

    @Test
    fun snippet() {
        runBlocking {
            val rule = Rule(
                objectID = ObjectID("hide-12345"),
                condition = Condition(Anchoring.Contains, Pattern.Literal("harry potter")),
                consequence = Consequence(
                    hide = listOf(ObjectID("HP-12345"))
                )
            )

            index.saveRule(rule)
        }
    }
}