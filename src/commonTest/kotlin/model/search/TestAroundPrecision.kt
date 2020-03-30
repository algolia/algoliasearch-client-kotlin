package model.search

import com.algolia.search.model.search.AroundPrecision
import kotlin.test.Test
import shouldEqual

internal class TestAroundPrecision {

    @Test
    fun dx() {
        AroundPrecision.Int(0)
        AroundPrecision.Ranges(0 until 10).list shouldEqual listOf(IntRange(0, 9))
    }
}
