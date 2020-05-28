package dsl.rule

import com.algolia.search.dsl.rule.DSLPromotions
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.Promotion
import objectIDA
import objectIDB
import shouldEqual
import kotlin.test.Test

internal class TestDSLPromotions {

    @Test
    fun promotions() {
        val dsl = DSLPromotions {
            +objectIDA(10)
            +"objectIDB"(10)
            +listOf(objectIDA, objectIDB)(10)
        }

        dsl shouldEqual listOf(
            Promotion(objectIDA, 10),
            Promotion(ObjectID("objectIDB"), 10),
            Promotion(listOf(objectIDA, objectIDB), 10)
        )
    }
}
