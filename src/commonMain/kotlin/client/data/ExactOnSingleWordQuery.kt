package client.data

import client.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.contentOrNull


sealed class ExactOnSingleWordQuery(open val raw: String) {

    /**
     * The exact ranking criterion is set to 1 if the query matches exactly an entire attribute value (default behavior).
     * For example, if you search for the TV show “V”, you want it to match the query “V” before all popular
     * TV shows starting with the letter V.
     */
    object Attribute : ExactOnSingleWordQuery(KeyAttribute)

    /**
     * The exact ranking criterion is ignored on single word queries.
     */
    object None : ExactOnSingleWordQuery(KeyNone)

    /**
     * The exact ranking criterion is set to 1 if the query word is found in the record.
     * The query word must be at least 3 characters long and must not be a stop word in any supported language.
     * For example, if you search for the TV show “Road”, and in your dataset you have 2 records,
     * “Road” and “Road Trip”, both will be considered to match exactly.
     */
    object Word : ExactOnSingleWordQuery(KeyWord)

    data class Unknown(override val raw: String) : ExactOnSingleWordQuery(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : Serializer<ExactOnSingleWordQuery>, Deserializer<ExactOnSingleWordQuery> {

        override fun serialize(input: ExactOnSingleWordQuery): JsonElement {
            return JsonPrimitive(input.raw)
        }

        override fun deserialize(element: JsonElement): ExactOnSingleWordQuery? {
            return when (val content = element.contentOrNull) {
                KeyAttribute -> Attribute
                KeyNone -> None
                KeyWord -> Word
                null -> null
                else -> Unknown(content)
            }
        }
    }
}