package model.search

import boolean
import com.algolia.search.model.search.TypoTolerance.*
import com.algolia.search.serialize.KeyMin
import com.algolia.search.serialize.KeyStrict
import kotlin.test.Test
import shouldEqual
import unknown


internal class TestTypoTolerance {

    @Test
    fun raw() {
        Boolean(boolean).raw shouldEqual "$boolean"
        Strict.raw shouldEqual KeyStrict
        Min.raw shouldEqual KeyMin
        Other(unknown).raw shouldEqual unknown
    }
}