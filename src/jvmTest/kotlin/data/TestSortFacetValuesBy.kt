package data

import com.algolia.search.saas.model.enums.SortFacetValuesBy.*
import com.algolia.search.saas.serialize.KeyAlpha
import com.algolia.search.saas.serialize.KeyCount
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestSortFacetValuesBy {

    @Test
    fun raw() {
        Count.raw shouldEqual KeyCount
        Alpha.raw shouldEqual KeyAlpha
        Other(unknown).raw shouldEqual unknown
    }
}