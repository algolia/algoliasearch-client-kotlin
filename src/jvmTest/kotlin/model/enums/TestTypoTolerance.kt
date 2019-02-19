package model.enums

import boolean
import com.algolia.search.model.enums.TypoTolerance.*
import com.algolia.search.model.enums.TypoTolerance.Boolean
import com.algolia.search.serialize.KeyMin
import com.algolia.search.serialize.KeyStrict
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestTypoTolerance {

    @Test
    fun raw() {
        Boolean(boolean).raw shouldEqual "$boolean"
        Strict.raw shouldEqual KeyStrict
        Min.raw shouldEqual KeyMin
        Other(unknown).raw shouldEqual unknown
    }
}