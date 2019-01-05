package data

import com.algolia.search.saas.data.MatchLevel.*
import com.algolia.search.saas.serialize.KeyFull
import com.algolia.search.saas.serialize.KeyNone
import com.algolia.search.saas.serialize.KeyPartial
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestMatchLevel {

    @Test
    fun raw() {
        None.raw shouldEqual KeyNone
        Partial.raw shouldEqual KeyPartial
        Full.raw shouldEqual KeyFull
        Other(unknown).raw shouldEqual unknown
    }
}