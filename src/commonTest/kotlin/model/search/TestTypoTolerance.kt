package model.search

import com.algolia.search.model.search.TypoTolerance.*
import com.algolia.search.serialize.KeyMin
import com.algolia.search.serialize.KeyStrict
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestTypoTolerance {

    @Test
    fun raw() {
        True shouldEqual "true"
        False shouldEqual "true"
        Strict.raw shouldEqual KeyStrict
        Min.raw shouldEqual KeyMin
        Other(unknown).raw shouldEqual unknown
    }
}