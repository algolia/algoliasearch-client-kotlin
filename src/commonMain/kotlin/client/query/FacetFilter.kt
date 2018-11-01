package client.query


data class FacetFilter(val attribute: String, val value: String) {

    fun render() = "$attribute:$value"
}