package com.algolia.search.saas.model.enums

import com.algolia.search.saas.model.Raw
import com.algolia.search.saas.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.JsonNull.content


@Serializable(QueryLanguage.Companion::class)
sealed class QueryLanguage(override val raw: String) : Raw<String> {

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

    data class Other(override val raw: String) : QueryLanguage(raw)

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<QueryLanguage> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: QueryLanguage) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): QueryLanguage {
            val string = serializer.deserialize(decoder)

            return when (string) {
                KeyAfrikaans -> Afrikaans
                KeyArabic -> Arabic
                KeyAzeri -> Azeri
                KeyBulgarian -> Bulgarian
                KeyBrunei -> Brunei
                KeyCatalan -> Catalan
                KeyCzech -> Czech
                KeyWelsh -> Welsh
                KeyDanish -> Danish
                KeyGerman -> German
                KeyEnglish -> English
                KeyEsperanto -> Esperanto
                KeySpanish -> Spanish
                KeyEstonian -> Estonian
                KeyBasque -> Basque
                KeyFinnish -> Finnish
                KeyFaroese -> Faroese
                KeyFrench -> French
                KeyGalician -> Galician
                KeyHebrew -> Hebrew
                KeyHindi -> Hindi
                KeyHungarian -> Hungarian
                KeyArmenian -> Armenian
                KeyIndonesian -> Indonesian
                KeyIcelandic -> Icelandic
                KeyItalian -> Italian
                KeyJapanese -> Japanese
                KeyGeorgian -> Georgian
                KeyKazakh -> Kazakh
                KeyKorean -> Korean
                KeyKyrgyz -> Kyrgyz
                KeyLithuanian -> Lithuanian
                KeyMaori -> Maori
                KeyMongolian -> Mongolian
                KeyMarathi -> Marathi
                KeyMalay -> Malay
                KeyMaltese -> Maltese
                KeyNorwegian -> Norwegian
                KeyDutch -> Dutch
                KeyNorthernSotho -> NorthernSotho
                KeyPolish -> Polish
                KeyPashto -> Pashto
                KeyPortuguese -> Portuguese
                KeyQuechua -> Quechua
                KeyRomanian -> Romanian
                KeyRussian -> Russian
                KeySlovak -> Slovak
                KeyAlbanian -> Albanian
                KeySwedish -> Swedish
                KeySwahili -> Swahili
                KeyTamil -> Tamil
                KeyTelugu -> Telugu
                KeyTagalog -> Tagalog
                KeyTswana -> Tswana
                KeyTurkish -> Turkish
                KeyTatar -> Tatar
                else -> Other(content)
            }
        }
    }
}