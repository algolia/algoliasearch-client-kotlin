package client.data


sealed class CustomRanking(open val raw: String) {

    data class Asc(val attribute: Attribute) : CustomRanking(client.serialize.Asc)

    data class Desc(val attribute: Attribute) : CustomRanking(client.serialize.Desc)

    data class Unknown(override val raw: String) : CustomRanking(raw)
}