package dsl

import com.algolia.search.dsl.DSLObjectIDs
import com.algolia.search.model.ObjectID
import kotlin.test.Test
import objectIDA
import shouldEqual

internal class TestDSLObjectIDs {

    @Test
    fun default() {
        val dsl = DSLObjectIDs {
            +objectIDA
            +"objectIDB"
        }

        dsl shouldEqual listOf(
            objectIDA,
            ObjectID("objectIDB")
        )
    }
}
