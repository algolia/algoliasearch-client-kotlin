
import com.algolia.search.dsl.filtering.DSLFilters
import com.algolia.search.dsl.filtering.Filter
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import kotlinx.serialization.json.jsonArray


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

internal val nestedListsJson = jsonArray {
    +jsonArray { +string }
    +jsonArray { +string }
}

internal val attributesJson = jsonArray {
    +attributeA.raw
    +attributeB.raw
}


internal fun DSLFilters.buildTest() = build().replace("\"", "")