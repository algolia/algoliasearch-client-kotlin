import client.Index
import client.data.Attribute
import client.query.helper.*
import client.serialize.Serializer
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlin.test.assertEquals


internal fun groupMap(): GroupMap<Filter> = mutableMapOf()
internal fun set(vararg filters: Filter) = mutableSetOf(*filters)

internal const val nameA = "nameA"
internal const val nameB = "nameB"
internal const val unknown = "unknown"
internal const val int = 0
internal const val boolean = true
internal const val string = "string"
internal const val all = "*"
internal val attributeA = Attribute("attributeA")
internal val attributeB = Attribute("attributeB")
internal val attributeC = Attribute("attributeC")
internal val indexA = Index("indexA")
internal val indexB = Index("indexB")
internal val groupOrA = GroupOr(nameA)
internal val groupOrB = GroupOr(nameB)
internal val groupAndA = GroupAnd(nameA)
internal val groupAndB = GroupAnd(nameB)
internal val attributeAll = Attribute("*")
internal val attributes = listOf(attributeA, attributeB)
internal val facetA = FilterFacet(attributeA, "facetA")
internal val facetB = FilterFacet(attributeB, false)
internal val comparisonA = FilterComparison(attributeA, NumericOperator.Greater, 5.0)
internal val comparisonB = FilterComparison(attributeB, NumericOperator.NotEquals, 10.0)
internal val rangeA = FilterRange(attributeA, 0.0, 5.0)
internal val rangeB = FilterRange(attributeB, 5.0, 10.0)
internal val tagA = FilterTag("tagA")
internal val tagB = FilterTag("tagB")

internal fun FilterBuilder.buildTest() = build().replace("\"", "")

internal fun <T> testDeserialize(expected: T, actual: JsonElement, serializer: Serializer<T>) {
    assertEquals(expected, serializer.deserialize(actual))
}

internal fun <T> testDeserializeNull(serializer: Serializer<T>) {
    assertEquals(null, serializer.deserialize(JsonNull))
    assertEquals(null, serializer.deserializes(JsonNull))
}

internal fun <T> testDeserializeArray(expected: List<T>, actual: JsonArray, serializer: Serializer<T>) {
    assertEquals(expected, serializer.deserializes(actual))
}

internal fun <T> testSerialize(expected: JsonElement, actual: T, serializer: Serializer<T>) {
    assertEquals(expected, serializer.serialize(actual))
}

internal fun <T> testSerializeNull(serializer: Serializer<T>) {
    assertEquals(JsonNull, serializer.serialize(null))
    assertEquals(JsonNull, serializer.serializes(null))
}

internal fun <T> testSerializeArray(expected: JsonArray, actual: List<T>, serializer: Serializer<T>) {
    assertEquals(expected, serializer.serializes(actual))
}

