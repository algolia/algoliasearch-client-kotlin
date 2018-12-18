package client.data


sealed class Ranking(val raw: String) {

    object Typo : Ranking("typo")

    object Geo : Ranking("geo")

    object Words : Ranking("words")

    object Filters : Ranking("filters")

    object Proximity : Ranking("proximity")

    object Attribute : Ranking("attribute")

    object Exact : Ranking("exact")

    object Custom : Ranking("custom")

    data class Asc(val attribute: client.data.Attribute) : Ranking("asc")

    data class Desc(val attribute: client.data.Attribute) : Ranking("desc")
}