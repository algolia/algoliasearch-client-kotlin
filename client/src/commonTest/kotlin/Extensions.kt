import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.filter.Filter
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

internal val nestedListsJson = buildJsonArray {
    add(buildJsonArray { add(string) })
    add(buildJsonArray { add(string) })
}

internal val attributesJson = buildJsonArray {
    add(attributeA.raw)
    add(attributeB.raw)
}
