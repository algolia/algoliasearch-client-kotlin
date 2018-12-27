package client.data

import client.serialize.KeyAttribute
import client.serialize.KeyNone
import client.serialize.KeyWord


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
}