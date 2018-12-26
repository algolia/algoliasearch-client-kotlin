package client.serialize

import client.data.CustomRanking
import client.data.Ranking
import client.toAttribute
import client.toIndex
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.content


internal fun JsonElement.toAttributes() = jsonArray.content.map { it.primitive.content.toAttribute() }

internal fun String.toRanking(): Ranking {
    val asc = Regex("$asc\\((.*)\\)")
    val desc = Regex("$desc\\((.*)\\)")
    val findAsc = asc.find(this)
    val findDesc = desc.find(this)

    return when {
        findAsc != null -> Ranking.Asc(findAsc.groupValues[1].toAttribute())
        findDesc != null -> Ranking.Desc(findDesc.groupValues[1].toAttribute())
        else -> when (this) {
            typo -> Ranking.Typo
            geo -> Ranking.Geo
            words -> Ranking.Words
            filters -> Ranking.Filters
            proximity -> Ranking.Proximity
            attribute -> Ranking.Attribute
            exact -> Ranking.Exact
            custom -> Ranking.Custom
            else -> Ranking.Unknown(this)
        }
    }
}

internal fun JsonElement.toRankings() = jsonArray.content.map {
    it.primitive.content.toRanking()
}

internal fun JsonElement.toCustomRankings() = jsonArray.content.map { it.primitive.content.toCustomRanking() }

internal fun String.toCustomRanking(): CustomRanking {
    val asc = Regex("$asc\\((.*)\\)")
    val desc = Regex("$desc\\((.*)\\)")
    val findAsc = asc.find(this)
    val findDesc = desc.find(this)

    return when {
        findAsc != null -> CustomRanking.Asc(findAsc.groupValues[1].toAttribute())
        findDesc != null -> CustomRanking.Desc(findDesc.groupValues[1].toAttribute())
        else -> CustomRanking.Unknown(this)
    }
}

internal fun JsonElement.toIndexes() = jsonArray.content.map { it.content.toIndex() }