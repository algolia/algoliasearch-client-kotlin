package client.data


sealed class Ranking(open val raw: String) {

    object Typo : Ranking(client.serialize.Typo)

    object Geo : Ranking(client.serialize.Geo)

    object Words : Ranking(client.serialize.Words)

    object Filters : Ranking(client.serialize.Filters)

    object Proximity : Ranking(client.serialize.Proximity)

    object Attribute : Ranking(client.serialize.Attribute)

    object Exact : Ranking(client.serialize.Exact)

    object Custom : Ranking(client.serialize.Custom)

    data class Asc(val attribute: client.data.Attribute) : Ranking(client.serialize.Asc)

    data class Desc(val attribute: client.data.Attribute) : Ranking(client.serialize.Desc)

    data class Unknown(override val raw: String): Ranking(raw)
}