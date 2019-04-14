package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.JsonNull.content


@Serializable(QueryLanguage.Companion::class)
public sealed class QueryLanguage(override val raw: String) : Raw<String> {

    public object Afrikaans : QueryLanguage(KeyAfrikaans)
    public object Arabic : QueryLanguage(KeyArabic)
    public object Azeri : QueryLanguage(KeyAzeri)
    public object Bulgarian : QueryLanguage(KeyBulgarian)
    public object Brunei : QueryLanguage(KeyBrunei)
    public object Catalan : QueryLanguage(KeyCatalan)
    public object Czech : QueryLanguage(KeyCzech)
    public object Welsh : QueryLanguage(KeyWelsh)
    public object Danish : QueryLanguage(KeyDanish)
    public object German : QueryLanguage(KeyGerman)
    public object English : QueryLanguage(KeyEnglish)
    public object Esperanto : QueryLanguage(KeyEsperanto)
    public object Spanish : QueryLanguage(KeySpanish)
    public object Estonian : QueryLanguage(KeyEstonian)
    public object Basque : QueryLanguage(KeyBasque)
    public object Finnish : QueryLanguage(KeyFinnish)
    public object Faroese : QueryLanguage(KeyFaroese)
    public object French : QueryLanguage(KeyFrench)
    public object Galician : QueryLanguage(KeyGalician)
    public object Hebrew : QueryLanguage(KeyHebrew)
    public object Hindi : QueryLanguage(KeyHindi)
    public object Hungarian : QueryLanguage(KeyHungarian)
    public object Armenian : QueryLanguage(KeyArmenian)
    public object Indonesian : QueryLanguage(KeyIndonesian)
    public object Icelandic : QueryLanguage(KeyIcelandic)
    public object Italian : QueryLanguage(KeyItalian)
    public object Japanese : QueryLanguage(KeyJapanese)
    public object Georgian : QueryLanguage(KeyGeorgian)
    public object Kazakh : QueryLanguage(KeyKazakh)
    public object Korean : QueryLanguage(KeyKorean)
    public object Kyrgyz : QueryLanguage(KeyKyrgyz)
    public object Lithuanian : QueryLanguage(KeyLithuanian)
    public object Maori : QueryLanguage(KeyMaori)
    public object Mongolian : QueryLanguage(KeyMongolian)
    public object Marathi : QueryLanguage(KeyMarathi)
    public object Malay : QueryLanguage(KeyMalay)
    public object Maltese : QueryLanguage(KeyMaltese)
    public object Norwegian : QueryLanguage(KeyNorwegian)
    public object Dutch : QueryLanguage(KeyDutch)
    public object NorthernSotho : QueryLanguage(KeyNorthernSotho)
    public object Polish : QueryLanguage(KeyPolish)
    public object Pashto : QueryLanguage(KeyPashto)
    public object Portuguese : QueryLanguage(KeyPortuguese)
    public object Quechua : QueryLanguage(KeyQuechua)
    public object Romanian : QueryLanguage(KeyRomanian)
    public object Russian : QueryLanguage(KeyRussian)
    public object Slovak : QueryLanguage(KeySlovak)
    public object Albanian : QueryLanguage(KeyAlbanian)
    public object Swedish : QueryLanguage(KeySwedish)
    public object Swahili : QueryLanguage(KeySwahili)
    public object Tamil : QueryLanguage(KeyTamil)
    public object Telugu : QueryLanguage(KeyTelugu)
    public object Tagalog : QueryLanguage(KeyTagalog)
    public object Tswana : QueryLanguage(KeyTswana)
    public object Turkish : QueryLanguage(KeyTurkish)
    public object Tatar : QueryLanguage(KeyTatar)

    public data class Other(override val raw: String) : QueryLanguage(raw)

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
            return when (serializer.deserialize(decoder)) {
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