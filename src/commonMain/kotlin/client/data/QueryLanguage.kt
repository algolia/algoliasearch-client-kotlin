package client.data

import client.serialize.*
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive


sealed class QueryLanguage(override val raw: String) : RawString {

    object Afrikaans : QueryLanguage(KeyAfrikaans)
    object Arabic : QueryLanguage(KeyArabic)
    object Azeri : QueryLanguage(KeyAzeri)
    object Bulgarian : QueryLanguage(KeyBulgarian)
    object Brunei : QueryLanguage(KeyBrunei)
    object Catalan : QueryLanguage(KeyCatalan)
    object Czech : QueryLanguage(KeyCzech)
    object Welsh : QueryLanguage(KeyWelsh)
    object Danish : QueryLanguage(KeyDanish)
    object German : QueryLanguage(KeyGerman)
    object English : QueryLanguage(KeyEnglish)
    object Esperanto : QueryLanguage(KeyEsperanto)
    object Spanish : QueryLanguage(KeySpanish)
    object Estonian : QueryLanguage(KeyEstonian)
    object Basque : QueryLanguage(KeyBasque)
    object Finnish : QueryLanguage(KeyFinnish)
    object Faroese : QueryLanguage(KeyFaroese)
    object French : QueryLanguage(KeyFrench)
    object Galician : QueryLanguage(KeyGalician)
    object Hebrew : QueryLanguage(KeyHebrew)
    object Hindi : QueryLanguage(KeyHindi)
    object Hungarian : QueryLanguage(KeyHungarian)
    object Armenian : QueryLanguage(KeyArmenian)
    object Indonesian : QueryLanguage(KeyIndonesian)
    object Icelandic : QueryLanguage(KeyIcelandic)
    object Italian : QueryLanguage(KeyItalian)
    object Japanese : QueryLanguage(KeyJapanese)
    object Georgian : QueryLanguage(KeyGeorgian)
    object Kazakh : QueryLanguage(KeyKazakh)
    object Korean : QueryLanguage(KeyKorean)
    object Kyrgyz : QueryLanguage(KeyKyrgyz)
    object Lithuanian : QueryLanguage(KeyLithuanian)
    object Maori : QueryLanguage(KeyMaori)
    object Mongolian : QueryLanguage(KeyMongolian)
    object Marathi : QueryLanguage(KeyMarathi)
    object Malay : QueryLanguage(KeyMalay)
    object Maltese : QueryLanguage(KeyMaltese)
    object Norwegian : QueryLanguage(KeyNorwegian)
    object Dutch : QueryLanguage(KeyDutch)
    object NorthernSotho : QueryLanguage(KeyNorthernSotho)
    object Polish : QueryLanguage(KeyPolish)
    object Pashto : QueryLanguage(KeyPashto)
    object Portuguese : QueryLanguage(KeyPortuguese)
    object Quechua : QueryLanguage(KeyQuechua)
    object Romanian : QueryLanguage(KeyRomanian)
    object Russian : QueryLanguage(KeyRussian)
    object Slovak : QueryLanguage(KeySlovak)
    object Albanian : QueryLanguage(KeyAlbanian)
    object Swedish : QueryLanguage(KeySwedish)
    object Swahili : QueryLanguage(KeySwahili)
    object Tamil : QueryLanguage(KeyTamil)
    object Telugu : QueryLanguage(KeyTelugu)
    object Tagalog : QueryLanguage(KeyTagalog)
    object Tswana : QueryLanguage(KeyTswana)
    object Turkish : QueryLanguage(KeyTurkish)
    object Tatar : QueryLanguage(KeyTatar)

    data class Unknown(override val raw: String) : QueryLanguage(raw)

    override fun toString(): String {
        return raw
    }

    internal companion object : RawStringSerializer<QueryLanguage>, Deserializer<QueryLanguage> {

        override fun deserialize(element: JsonElement): QueryLanguage? {
            return when (element) {
                is JsonPrimitive -> when (val content = element.contentOrNull) {
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
                    null -> null
                    else -> QueryLanguage.Unknown(content)
                }
                else -> null
            }
        }
    }
}