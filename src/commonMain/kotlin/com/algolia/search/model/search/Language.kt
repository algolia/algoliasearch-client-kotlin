package com.algolia.search.model.search

import com.algolia.search.model.Raw
import com.algolia.search.serialize.KeyAfrikaans
import com.algolia.search.serialize.KeyAlbanian
import com.algolia.search.serialize.KeyArabic
import com.algolia.search.serialize.KeyArmenian
import com.algolia.search.serialize.KeyAzeri
import com.algolia.search.serialize.KeyBasque
import com.algolia.search.serialize.KeyBrunei
import com.algolia.search.serialize.KeyBulgarian
import com.algolia.search.serialize.KeyCatalan
import com.algolia.search.serialize.KeyCzech
import com.algolia.search.serialize.KeyDanish
import com.algolia.search.serialize.KeyDutch
import com.algolia.search.serialize.KeyEnglish
import com.algolia.search.serialize.KeyEsperanto
import com.algolia.search.serialize.KeyEstonian
import com.algolia.search.serialize.KeyFaroese
import com.algolia.search.serialize.KeyFinnish
import com.algolia.search.serialize.KeyFrench
import com.algolia.search.serialize.KeyGalician
import com.algolia.search.serialize.KeyGeorgian
import com.algolia.search.serialize.KeyGerman
import com.algolia.search.serialize.KeyHebrew
import com.algolia.search.serialize.KeyHindi
import com.algolia.search.serialize.KeyHungarian
import com.algolia.search.serialize.KeyIcelandic
import com.algolia.search.serialize.KeyIndonesian
import com.algolia.search.serialize.KeyItalian
import com.algolia.search.serialize.KeyJapanese
import com.algolia.search.serialize.KeyKazakh
import com.algolia.search.serialize.KeyKorean
import com.algolia.search.serialize.KeyKyrgyz
import com.algolia.search.serialize.KeyLithuanian
import com.algolia.search.serialize.KeyMalay
import com.algolia.search.serialize.KeyMaltese
import com.algolia.search.serialize.KeyMaori
import com.algolia.search.serialize.KeyMarathi
import com.algolia.search.serialize.KeyMongolian
import com.algolia.search.serialize.KeyNorthernSotho
import com.algolia.search.serialize.KeyNorwegian
import com.algolia.search.serialize.KeyPashto
import com.algolia.search.serialize.KeyPolish
import com.algolia.search.serialize.KeyPortuguese
import com.algolia.search.serialize.KeyQuechua
import com.algolia.search.serialize.KeyRomanian
import com.algolia.search.serialize.KeyRussian
import com.algolia.search.serialize.KeySlovak
import com.algolia.search.serialize.KeySpanish
import com.algolia.search.serialize.KeySwahili
import com.algolia.search.serialize.KeySwedish
import com.algolia.search.serialize.KeyTagalog
import com.algolia.search.serialize.KeyTamil
import com.algolia.search.serialize.KeyTatar
import com.algolia.search.serialize.KeyTelugu
import com.algolia.search.serialize.KeyTswana
import com.algolia.search.serialize.KeyTurkish
import com.algolia.search.serialize.KeyWelsh
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer

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

        private val serializer = String.serializer()

        override val descriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: Language) {
            serializer.serialize(encoder, value.raw)
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
