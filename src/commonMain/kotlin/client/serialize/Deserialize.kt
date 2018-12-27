package client.serialize

import client.data.CustomRanking
import client.data.Ranking
import client.toAttribute
import client.toIndex
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.content


internal fun JsonElement.toAttributes() = jsonArray.content.map { it.primitive.content.toAttribute() }

internal fun String.toRanking(): Ranking {
    val asc = Regex("$Asc\\((.*)\\)")
    val desc = Regex("$Desc\\((.*)\\)")
    val findAsc = asc.find(this)
    val findDesc = desc.find(this)

    return when {
        findAsc != null -> client.data.Ranking.Asc(findAsc.groupValues[1].toAttribute())
        findDesc != null -> client.data.Ranking.Desc(findDesc.groupValues[1].toAttribute())
        else -> when (this) {
            Typo -> client.data.Ranking.Typo
            Geo -> client.data.Ranking.Geo
            Words -> client.data.Ranking.Words
            Filters -> client.data.Ranking.Filters
            Proximity -> client.data.Ranking.Proximity
            Attribute -> client.data.Ranking.Attribute
            Exact -> client.data.Ranking.Exact
            Custom -> client.data.Ranking.Custom
            else -> client.data.Ranking.Unknown(this)
        }
    }
}

internal fun JsonElement.toRankings() = jsonArray.content.map {
    it.primitive.content.toRanking()
}

internal fun JsonElement.toCustomRankings() = jsonArray.content.map { it.primitive.content.toCustomRanking() }

internal fun String.toCustomRanking(): CustomRanking {
    val asc = Regex("$Asc\\((.*)\\)")
    val desc = Regex("$Desc\\((.*)\\)")
    val findAsc = asc.find(this)
    val findDesc = desc.find(this)

    return when {
        findAsc != null -> client.data.CustomRanking.Asc(findAsc.groupValues[1].toAttribute())
        findDesc != null -> client.data.CustomRanking.Desc(findDesc.groupValues[1].toAttribute())
        else -> client.data.CustomRanking.Unknown(this)
    }
}

internal fun JsonElement.toIndexes() = jsonArray.content.map { it.content.toIndex() }