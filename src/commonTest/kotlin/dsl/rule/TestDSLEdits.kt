package dsl.rule

import com.algolia.search.dsl.rule.DSLEdits
import com.algolia.search.model.rule.Edit
import shouldEqual
import kotlin.test.Test

internal class TestDSLEdits {

    @Test
    fun default() {
        val dsl = DSLEdits {
            +"insert"
            +"insert"("delete")
        }

        dsl shouldEqual listOf(
            Edit("insert"),
            Edit("insert", "delete")
        )
    }
}
