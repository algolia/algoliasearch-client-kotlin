package client.data

import client.serialize.*


sealed class Ranking(open val raw: String) {

    object Typo : Ranking(KeyTypo)

    object Geo : Ranking(KeyGeo)

    object Words : Ranking(KeyWords)

    object Filters : Ranking(KeyFilters)

    object Proximity : Ranking(KeyProximity)

    object Attribute : Ranking(KeyAttribute)

    object Exact : Ranking(KeyExact)

    object Custom : Ranking(KeyCustom)

    data class Asc(val attribute: client.data.Attribute) : Ranking("$KeyAsc($attribute)")

    data class Desc(val attribute: client.data.Attribute) : Ranking("$KeyDesc($attribute)")

    data class Unknown(override val raw: String) : Ranking(raw)
}