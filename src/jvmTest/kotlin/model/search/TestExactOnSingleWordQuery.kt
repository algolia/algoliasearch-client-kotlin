package model.search

import com.algolia.search.model.search.ExactOnSingleWordQuery.*
import com.algolia.search.serialize.KeyAttribute
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyWord
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestExactOnSingleWordQuery {

    @Test
    fun raw() {
        Attribute.raw shouldEqual KeyAttribute
        None.raw shouldEqual KeyNone
        Word.raw shouldEqual KeyWord
        Other(unknown).raw shouldEqual unknown
    }
}