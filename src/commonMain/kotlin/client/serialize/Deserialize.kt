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
        KeyAfrikaans -> QueryLanguage.Afrikaans
        KeyArabic -> QueryLanguage.Arabic
        KeyAzeri -> QueryLanguage.Azeri
        KeyBulgarian -> QueryLanguage.Bulgarian
        KeyBrunei -> QueryLanguage.Brunei
        KeyCatalan -> QueryLanguage.Catalan
        KeyCzech -> QueryLanguage.Czech
        KeyWelsh -> QueryLanguage.Welsh
        KeyDanish -> QueryLanguage.Danish
        KeyGerman -> QueryLanguage.German
        KeyEnglish -> QueryLanguage.English
        KeyEsperanto -> QueryLanguage.Esperanto
        KeySpanish -> QueryLanguage.Spanish
        KeyEstonian -> QueryLanguage.Estonian
        KeyBasque -> QueryLanguage.Basque
        KeyFinnish -> QueryLanguage.Finnish
        KeyFaroese -> QueryLanguage.Faroese
        KeyFrench -> QueryLanguage.French
        KeyGalician -> QueryLanguage.Galician
        KeyHebrew -> QueryLanguage.Hebrew
        KeyHindi -> QueryLanguage.Hindi
        KeyHungarian -> QueryLanguage.Hungarian
        KeyArmenian -> QueryLanguage.Armenian
        KeyIndonesian -> QueryLanguage.Indonesian
        KeyIcelandic -> QueryLanguage.Icelandic
        KeyItalian -> QueryLanguage.Italian
        KeyJapanese -> QueryLanguage.Japanese
        KeyGeorgian -> QueryLanguage.Georgian
        KeyKazakh -> QueryLanguage.Kazakh
        KeyKorean -> QueryLanguage.Korean
        KeyKyrgyz -> QueryLanguage.Kyrgyz
        KeyLithuanian -> QueryLanguage.Lithuanian
        KeyMaori -> QueryLanguage.Maori
        KeyMongolian -> QueryLanguage.Mongolian
        KeyMarathi -> QueryLanguage.Marathi
        KeyMalay -> QueryLanguage.Malay
        KeyMaltese -> QueryLanguage.Maltese
        KeyNorwegian -> QueryLanguage.Norwegian
        KeyDutch -> QueryLanguage.Dutch
        KeyNorthernSotho -> QueryLanguage.NorthernSotho
        KeyPolish -> QueryLanguage.Polish
        KeyPashto -> QueryLanguage.Pashto
        KeyPortuguese -> QueryLanguage.Portuguese
        KeyQuechua -> QueryLanguage.Quechua
        KeyRomanian -> QueryLanguage.Romanian
        KeyRussian -> QueryLanguage.Russian
        KeySlovak -> QueryLanguage.Slovak
        KeyAlbanian -> QueryLanguage.Albanian
        KeySwedish -> QueryLanguage.Swedish
        KeySwahili -> QueryLanguage.Swahili
        KeyTamil -> QueryLanguage.Tamil
        KeyTelugu -> QueryLanguage.Telugu
        KeyTagalog -> QueryLanguage.Tagalog
        KeyTswana -> QueryLanguage.Tswana
        KeyTurkish -> QueryLanguage.Turkish
        KeyTatar -> QueryLanguage.Tatar
        else -> QueryLanguage.Unknown(this)
    }
}