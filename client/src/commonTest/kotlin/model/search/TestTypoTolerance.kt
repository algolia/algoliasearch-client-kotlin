package model.search

import com.algolia.search.model.search.TypoTolerance.False
import com.algolia.search.model.search.TypoTolerance.Min
import com.algolia.search.model.search.TypoTolerance.Other
import com.algolia.search.model.search.TypoTolerance.Strict
import com.algolia.search.model.search.TypoTolerance.True
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestTypoTolerance {

    @Test
    fun raw() {
        True.raw shouldEqual "true"
        False.raw shouldEqual "false"
        Strict.raw shouldEqual Key.Strict
        Min.raw shouldEqual Key.Min
        Other(unknown).raw shouldEqual unknown
    }
}
