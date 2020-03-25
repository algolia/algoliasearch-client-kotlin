package dsl.rule

import com.algolia.search.dsl.rule.DSLTimeRanges
import com.algolia.search.model.rule.TimeRange
import shouldEqual
import kotlin.test.Test

internal class TestDSLTimeRanges {

    @Test
    fun default() {
        val dsl = DSLTimeRanges {
            +(0L..100L)
            +TimeRange(100, 200)
        }

        dsl shouldEqual listOf(
            TimeRange(0, 100),
            TimeRange(100, 200)
        )
    }
}
