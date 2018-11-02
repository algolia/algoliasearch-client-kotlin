package client.query


data class FacetFilter(val attribute: String, val value: String, val negates: Boolean = false) {

    val raw = if (negates) "$attribute:-$value" else "$attribute:$value"
}