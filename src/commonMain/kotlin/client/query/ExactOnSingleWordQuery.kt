package client.query


enum class ExactOnSingleWordQuery(val raw: String) {
    /**
     * The exact ranking criterion is set to 1 if the query matches exactly an entire attribute value (default behavior).
     * For example, if you search for the TV show “V”, you want it to match the query “V” before all popular
     * TV shows starting with the letter V.
     */
    Attribute("attribute"),
    /**
     * The exact ranking criterion is ignored on single word queries.
     */
    None("none"),
    /**
     * The exact ranking criterion is set to 1 if the query word is found in the record.
     * The query word must be at least 3 characters long and must not be a stop word in any supported language.
     * For example, if you search for the TV show “Road”, and in your dataset you have 2 records,
     * “Road” and “Road Trip”, both will be considered to match exactly.
     */
    Word("word")
}