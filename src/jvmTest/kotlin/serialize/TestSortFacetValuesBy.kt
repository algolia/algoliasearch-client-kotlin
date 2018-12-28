package serialize

import client.data.SortFacetValuesBy
import client.data.SortFacetValuesBy.*
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import tmp.TestSerializer
import unknown


@RunWith(JUnit4::class)
internal class TestSortFacetValuesBy: TestSerializer<SortFacetValuesBy>(SortFacetValuesBy, SortFacetValuesBy) {

    override val item = listOf(
        Alpha to JsonPrimitive(Alpha.raw),
        Count to JsonPrimitive(Count.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
    override val items: List<Pair<List<SortFacetValuesBy>, JsonArray>> = listOf()
}