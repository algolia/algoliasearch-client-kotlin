package client.query


data class FacetFilter(val attribute: String, val value: String, val negates: Boolean = false) {

    fun render() = "$attribute:${if (negates) "-" else ""}$value"
}