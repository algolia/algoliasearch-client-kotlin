package serialize.search

import com.algolia.search.model.search.SortFacetValuesBy
import com.algolia.search.model.search.SortFacetValuesBy.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import serialize.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestSortFacetValuesBy : TestSerializer<SortFacetValuesBy>(SortFacetValuesBy) {

    override val items = listOf(
        Alpha to JsonLiteral(Alpha.raw),
        Count to JsonLiteral(Count.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}