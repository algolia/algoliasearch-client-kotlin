package model.search

import com.algolia.search.model.search.AroundPrecision
import shouldEqual
import kotlin.test.Test

internal class TestAroundPrecision {

    @Test
    fun dx() {
        AroundPrecision.Int(0)
        AroundPrecision.Ranges(0 until 10).list shouldEqual listOf(IntRange(0, 9))
    }
}
