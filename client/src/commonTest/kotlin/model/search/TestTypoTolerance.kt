package model.search

import com.algolia.search.model.search.TypoTolerance.False
import com.algolia.search.model.search.TypoTolerance.Min
import com.algolia.search.model.search.TypoTolerance.Other
import com.algolia.search.model.search.TypoTolerance.Strict
import com.algolia.search.model.search.TypoTolerance.True
import com.algolia.search.serialize.internal.KeyMin
import com.algolia.search.serialize.internal.KeyStrict
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestTypoTolerance {

    @Test
    fun raw() {
        True.raw shouldEqual "true"
        False.raw shouldEqual "false"
        Strict.raw shouldEqual KeyStrict
        Min.raw shouldEqual KeyMin
        Other(unknown).raw shouldEqual unknown
    }
}
