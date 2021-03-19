package serialize.search

import com.algolia.search.model.search.SortFacetsBy
import com.algolia.search.model.search.SortFacetsBy.Alpha
import com.algolia.search.model.search.SortFacetsBy.Count
import com.algolia.search.model.search.SortFacetsBy.Other
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestSortFacetsBy : TestSerializer<SortFacetsBy>(SortFacetsBy) {

    override val items = listOf(
        Alpha to JsonPrimitive(Alpha.raw),
        Count to JsonPrimitive(Count.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
