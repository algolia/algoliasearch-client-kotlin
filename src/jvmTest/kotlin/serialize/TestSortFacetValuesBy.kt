package serialize

import com.algolia.search.saas.data.SortFacetValuesBy
import com.algolia.search.saas.data.SortFacetValuesBy.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestSortFacetValuesBy : TestSerializer<SortFacetValuesBy>(SortFacetValuesBy) {

    override val items = listOf(
        Alpha,
        Count,
        Other(unknown)
    )
}