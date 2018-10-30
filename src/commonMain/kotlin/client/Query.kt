package client


class Query {

    var query: String? = null

    private var attributesToRetrieve = listOf<String>()

    /**
     * [Documentation][https://www.algolia.com/doc/api-reference/api-parameters/attributesToRetrieve/]
     * @return A list of attributes set for retrieval.
     */
    fun getAttributesToRetrieve(): List<String> = attributesToRetrieve

    /**
     * @param attributes A list of attributes
     * Set which attributes should be included the in the search response.
     */
    fun setAttributesToRetrieve(vararg attributes: String) {
        attributesToRetrieve = attributes.toList()
    }

    /**
     * @param attributes A list of attributes
     * Set which attributes should be excluded from the search response.
     */
    fun setAttributesToRetrieveExcept(vararg attributes: String) {
        val excepts = attributes.map { "-$it" }
        attributesToRetrieve = if (excepts.isNotEmpty()) excepts.plus("*") else excepts
    }
}