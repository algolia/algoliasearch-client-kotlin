package client.data


data class Snippet(
    val attribute: Attribute,
    val count: Int? = null
) {

    val raw = attribute.name + if (count != null) ":$count" else ""
}