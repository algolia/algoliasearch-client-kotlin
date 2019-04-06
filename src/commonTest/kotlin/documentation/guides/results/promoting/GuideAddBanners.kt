package documentation.guides.results.promoting

import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.*
import documentation.index
import kotlinx.serialization.json.json
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class GuideAddBanners {

    @Test
    fun snippet1() {
        runBlocking {
            val rule = Rule(
                objectID = ObjectID("a-rule-id"),
                condition = Condition(anchoring = Anchoring.Contains, pattern = Pattern.Literal("harry potter")),
                consequence = Consequence(userData = json { "promo_content" to "20% OFF on all Harry Potter books!" }),
                enabled = false
            )

            index.saveRule(rule)
        }
    }
}