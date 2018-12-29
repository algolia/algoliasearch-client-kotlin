package client.data

import client.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


sealed class SortFacetValuesBy(override val raw: String) : RawString {

    /**
     * FacetFilter values are sorted by decreasing count.
     * The count is the number of records containing this facet value in the results of the query.
     */
    object Count : SortFacetValuesBy(KeyCount)

    /**
     * FacetFilter values are sorted in alphabetical order, ascending from A to Z.
     * The count is the number of records containing this facet value in the results of the query.
     */
    object Alpha : SortFacetValuesBy(KeyAlpha)

    data class Unknown(override val raw: String) : SortFacetValuesBy(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : RawStringSerializer<SortFacetValuesBy>, Deserializer<SortFacetValuesBy> {

        override fun deserialize(element: JsonElement): SortFacetValuesBy? {
            return when (element) {
                is JsonPrimitive -> {
                    when (val content = element.contentOrNull) {
                        KeyCount -> Count
                        KeyAlpha -> Alpha
                        null -> null
                        else -> Unknown(content)
                    }
                }
                else -> null
            }
        }
    }
}