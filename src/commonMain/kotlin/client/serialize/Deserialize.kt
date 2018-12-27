package client.serialize

import client.data.*
import client.toAttribute
import client.toIndex
import kotlinx.serialization.json.*


internal fun JsonElement.toAttributes() = jsonArray.content.map { it.primitive.content.toAttribute() }

internal fun String.toRanking(): Ranking {
    val asc = Regex("$KeyAsc\\((.*)\\)")
    val desc = Regex("$KeyDesc\\((.*)\\)")
    val findAsc = asc.find(this)
    val findDesc = desc.find(this)

    return when {
        findAsc != null -> client.data.Ranking.Asc(findAsc.groupValues[1].toAttribute())
        findDesc != null -> client.data.Ranking.Desc(findDesc.groupValues[1].toAttribute())
        else -> when (this) {
            KeyTypo -> Ranking.Typo
            KeyGeo -> Ranking.Geo
            KeyWords -> Ranking.Words
            KeyFilters -> Ranking.Filters
            KeyProximity -> Ranking.Proximity
            KeyAttribute -> Ranking.Attribute
            KeyExact -> Ranking.Exact
            KeyCustom -> Ranking.Custom
            else -> Ranking.Unknown(this)
        }
    }
}

internal fun JsonElement.toRankings() = jsonArray.content.map {
    it.primitive.content.toRanking()
}

internal fun JsonElement.toCustomRankings() = jsonArray.content.map { it.primitive.content.toCustomRanking() }

internal fun String.toCustomRanking(): CustomRanking {
    val asc = Regex("$KeyAsc\\((.*)\\)")
    val desc = Regex("$KeyDesc\\((.*)\\)")
    val findAsc = asc.find(this)
    val findDesc = desc.find(this)

    return when {
        findAsc != null -> client.data.CustomRanking.Asc(findAsc.groupValues[1].toAttribute())
        findDesc != null -> client.data.CustomRanking.Desc(findDesc.groupValues[1].toAttribute())
        else -> client.data.CustomRanking.Unknown(this)
    }
}

internal fun JsonElement.toIndexes() = jsonArray.content.map { it.content.toIndex() }

internal fun JsonElement.toSnippets() = jsonArray.content.map { it.primitive.content.toSnippet() }

internal fun String.toSnippet(): Snippet {
    val regex = Regex("(.*):(.*)")
    val findSnippet = regex.find(this)

    return when {
        findSnippet != null -> Snippet(findSnippet.groupValues[1].toAttribute(), findSnippet.groupValues[2].toInt())
        else -> Snippet(client.data.Attribute(this))
    }
}

internal fun JsonElement.toTypoTolerance(): TypoTolerance {
    return when {
        booleanOrNull != null -> client.data.TypoTolerance.Boolean(boolean)
        else -> when (content) {
            KeyStrict -> client.data.TypoTolerance.Strict
            KeyMin -> client.data.TypoTolerance.Min
            else -> client.data.TypoTolerance.Unknown(content)
        }
    }
}

internal fun JsonElement.toBooleanOrQueryLanguages(): BooleanOrQueryLanguages {
    return when (this) {
        is JsonArray -> BooleanOrQueryLanguages.QueryLanguages(this.map { it.content.toQueryLanguage() })
        else -> BooleanOrQueryLanguages.Boolean(boolean)
    }
}

internal fun String.toQueryLanguage(): QueryLanguage {
    return when (this) {
        Afrikaans -> QueryLanguage.Afrikaans
        Arabic -> QueryLanguage.Arabic
        Azeri -> QueryLanguage.Azeri
        Bulgarian -> QueryLanguage.Bulgarian
        Brunei -> QueryLanguage.Brunei
        Catalan -> QueryLanguage.Catalan
        Czech -> QueryLanguage.Czech
        Welsh -> QueryLanguage.Welsh
        Danish -> QueryLanguage.Danish
        German -> QueryLanguage.German
        English -> QueryLanguage.English
        Esperanto -> QueryLanguage.Esperanto
        Spanish -> QueryLanguage.Spanish
        Estonian -> QueryLanguage.Estonian
        Basque -> QueryLanguage.Basque
        Finnish -> QueryLanguage.Finnish
        Faroese -> QueryLanguage.Faroese
        French -> QueryLanguage.French
        Galician -> QueryLanguage.Galician
        Hebrew -> QueryLanguage.Hebrew
        Hindi -> QueryLanguage.Hindi
        Hungarian -> QueryLanguage.Hungarian
        Armenian -> QueryLanguage.Armenian
        Indonesian -> QueryLanguage.Indonesian
        Icelandic -> QueryLanguage.Icelandic
        Italian -> QueryLanguage.Italian
        Japanese -> QueryLanguage.Japanese
        Georgian -> QueryLanguage.Georgian
        Kazakh -> QueryLanguage.Kazakh
        Korean -> QueryLanguage.Korean
        Kyrgyz -> QueryLanguage.Kyrgyz
        Lithuanian -> QueryLanguage.Lithuanian
        Maori -> QueryLanguage.Maori
        Mongolian -> QueryLanguage.Mongolian
        Marathi -> QueryLanguage.Marathi
        Malay -> QueryLanguage.Malay
        Maltese -> QueryLanguage.Maltese
        Norwegian -> QueryLanguage.Norwegian
        Dutch -> QueryLanguage.Dutch
        NorthernSotho -> QueryLanguage.NorthernSotho
        Polish -> QueryLanguage.Polish
        Pashto -> QueryLanguage.Pashto
        Portuguese -> QueryLanguage.Portuguese
        Quechua -> QueryLanguage.Quechua
        Romanian -> QueryLanguage.Romanian
        Russian -> QueryLanguage.Russian
        Slovak -> QueryLanguage.Slovak
        Albanian -> QueryLanguage.Albanian
        Swedish -> QueryLanguage.Swedish
        Swahili -> QueryLanguage.Swahili
        Tamil -> QueryLanguage.Tamil
        Telugu -> QueryLanguage.Telugu
        Tagalog -> QueryLanguage.Tagalog
        Tswana -> QueryLanguage.Tswana
        Turkish -> QueryLanguage.Turkish
        Tatar -> QueryLanguage.Tatar
        else -> QueryLanguage.Unknown(this)
    }
}