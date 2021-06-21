import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.rule.FacetOrdering
import com.algolia.search.model.rule.FacetValuesOrder
import com.algolia.search.model.rule.FacetsOrder
import com.algolia.search.model.rule.RenderingContent
import com.algolia.search.model.rule.SortRule
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray

internal fun set(vararg filters: Filter) = mutableSetOf(*filters)

internal const val unknown = "unknown"
internal const val int = 0
internal const val boolean = true
internal const val string = "string"
internal val attributeA = Attribute("attributeA")
internal val attributeB = Attribute("attributeB")
internal val indexA = IndexName("indexA")
internal val indexB = IndexName("indexB")
internal val objectIDA = ObjectID("442854")
internal val objectIDB = ObjectID("322601")
internal val nestedLists = listOf(listOf(string), listOf(string))
internal val attributes = listOf(attributeA, attributeB)
internal val renderingContent = RenderingContent(
    facetOrdering = FacetOrdering(
        facets = FacetsOrder(
            order = listOf("size", "brand", "country", "color")
        ),
        values = mapOf(
            Attribute("brand") to FacetValuesOrder(
                order = listOf("Uniqlo"),
                sortRemainingBy = SortRule.Alpha
            ),
            Attribute("color") to FacetValuesOrder(
                order = listOf("red", "green", "blue"),
                sortRemainingBy = SortRule.Count
            ),
            Attribute("country") to FacetValuesOrder(
                order = listOf("France", "Germany", "Finland"),
                sortRemainingBy = SortRule.Hidden
            ),
            Attribute("size") to FacetValuesOrder(
                order = emptyList(),
                sortRemainingBy = SortRule.Count
            )
        )
    )
)

internal val nestedListsJson = buildJsonArray {
    add(buildJsonArray { add(string) })
    add(buildJsonArray { add(string) })
}

internal val attributesJson = buildJsonArray {
    add(attributeA.raw)
    add(attributeB.raw)
}
