package documentation.guides.results.promoting

import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.*
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuidePromoteHits {

    @Test
    fun snippet() {
        runBlocking {
            val rule = Rule(
                objectID = ObjectID("Promote Harry Potter Box Set"),
                condition = Condition(Anchoring.Contains, Pattern.Literal("Harry Potter")),
                consequence = Consequence(
                    promote = listOf(
                        Promotion(objectID = ObjectID("HP-12345"), position = 0)
                    )
                )
            )

            index.saveRule(rule)
        }
    }
}