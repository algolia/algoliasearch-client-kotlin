package client.data


sealed class CustomRanking(val raw: String, open val attribute: Attribute) {

    data class Asc(override val attribute: Attribute) : CustomRanking("asc", attribute)

    data class Desc(override val attribute: Attribute) : CustomRanking("desc", attribute)
}