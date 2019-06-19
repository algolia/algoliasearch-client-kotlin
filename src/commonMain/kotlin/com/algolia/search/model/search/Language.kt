package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.*
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer


@Deprecated(
    message = "Obsolete name from the preview version of library.",
    replaceWith = ReplaceWith("Language"),
    level = DeprecationLevel.WARNING
)
public typealias QueryLanguage = Language

/**
 * List of supported languages with their associated language ISO code.
 */
@Serializable(Language.Companion::class)
public sealed class Language(override val raw: String) : Raw<String> {

    public object Afrikaans : Language(KeyAfrikaans)
    public object Arabic : Language(KeyArabic)
    public object Azeri : Language(KeyAzeri)
    public object Bulgarian : Language(KeyBulgarian)
    public object Brunei : Language(KeyBrunei)
    public object Catalan : Language(KeyCatalan)
    public object Czech : Language(KeyCzech)
    public object Welsh : Language(KeyWelsh)
    public object Danish : Language(KeyDanish)
    public object German : Language(KeyGerman)
    public object English : Language(KeyEnglish)
    public object Esperanto : Language(KeyEsperanto)
    public object Spanish : Language(KeySpanish)
    public object Estonian : Language(KeyEstonian)
    public object Basque : Language(KeyBasque)
    public object Finnish : Language(KeyFinnish)
    public object Faroese : Language(KeyFaroese)
    public object French : Language(KeyFrench)
    public object Galician : Language(KeyGalician)
    public object Hebrew : Language(KeyHebrew)
    public object Hindi : Language(KeyHindi)
    public object Hungarian : Language(KeyHungarian)
    public object Armenian : Language(KeyArmenian)
    public object Indonesian : Language(KeyIndonesian)
    public object Icelandic : Language(KeyIcelandic)
    public object Italian : Language(KeyItalian)
    public object Japanese : Language(KeyJapanese)
    public object Georgian : Language(KeyGeorgian)
    public object Kazakh : Language(KeyKazakh)
    public object Korean : Language(KeyKorean)
    public object Kyrgyz : Language(KeyKyrgyz)
    public object Lithuanian : Language(KeyLithuanian)
    public object Maori : Language(KeyMaori)
    public object Mongolian : Language(KeyMongolian)
    public object Marathi : Language(KeyMarathi)
    public object Malay : Language(KeyMalay)
    public object Maltese : Language(KeyMaltese)
    public object Norwegian : Language(KeyNorwegian)
    public object Dutch : Language(KeyDutch)
    public object NorthernSotho : Language(KeyNorthernSotho)
    public object Polish : Language(KeyPolish)
    public object Pashto : Language(KeyPashto)
    public object Portuguese : Language(KeyPortuguese)
    public object Quechua : Language(KeyQuechua)
    public object Romanian : Language(KeyRomanian)
    public object Russian : Language(KeyRussian)
    public object Slovak : Language(KeySlovak)
    public object Albanian : Language(KeyAlbanian)
    public object Swedish : Language(KeySwedish)
    public object Swahili : Language(KeySwahili)
    public object Tamil : Language(KeyTamil)
    public object Telugu : Language(KeyTelugu)
    public object Tagalog : Language(KeyTagalog)
    public object Tswana : Language(KeyTswana)
    public object Turkish : Language(KeyTurkish)
    public object Tatar : Language(KeyTatar)

    public data class Other(override val raw: String) : Language(raw)

    override fun toString(): String {
        return raw
    }

    companion object : KSerializer<Language> {

        private val serializer = StringSerializer

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, obj: Language) {
            serializer.serialize(encoder, obj.raw)
        }

        override fun deserialize(decoder: Decoder): Language {
            return when (val string = serializer.deserialize(decoder)) {
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
                else -> Other(string)
            }
        }
    }
}