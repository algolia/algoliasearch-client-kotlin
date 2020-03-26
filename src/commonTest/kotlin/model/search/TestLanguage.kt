package model.search

import com.algolia.search.model.search.Language.*
import com.algolia.search.serialize.*
import shouldEqual
import kotlin.test.Test


internal class TestLanguage {

    @Test
    fun raw() {
        Afrikaans.raw shouldEqual KeyAfrikaans
        Arabic.raw shouldEqual KeyArabic
        Azeri.raw shouldEqual KeyAzeri
        Bulgarian.raw shouldEqual KeyBulgarian
        Brunei.raw shouldEqual KeyBrunei
        Catalan.raw shouldEqual KeyCatalan
        Czech.raw shouldEqual KeyCzech
        Welsh.raw shouldEqual KeyWelsh
        Danish.raw shouldEqual KeyDanish
        German.raw shouldEqual KeyGerman
        English.raw shouldEqual KeyEnglish
        Esperanto.raw shouldEqual KeyEsperanto
        Spanish.raw shouldEqual KeySpanish
        Estonian.raw shouldEqual KeyEstonian
        Basque.raw shouldEqual KeyBasque
        Finnish.raw shouldEqual KeyFinnish
        Faroese.raw shouldEqual KeyFaroese
        French.raw shouldEqual KeyFrench
        Galician.raw shouldEqual KeyGalician
        Hebrew.raw shouldEqual KeyHebrew
        Hindi.raw shouldEqual KeyHindi
        Hungarian.raw shouldEqual KeyHungarian
        Armenian.raw shouldEqual KeyArmenian
        Indonesian.raw shouldEqual KeyIndonesian
        Icelandic.raw shouldEqual KeyIcelandic
        Italian.raw shouldEqual KeyItalian
        Japanese.raw shouldEqual KeyJapanese
        Georgian.raw shouldEqual KeyGeorgian
        Kazakh.raw shouldEqual KeyKazakh
        Korean.raw shouldEqual KeyKorean
        Kyrgyz.raw shouldEqual KeyKyrgyz
        Lithuanian.raw shouldEqual KeyLithuanian
        Maori.raw shouldEqual KeyMaori
        Mongolian.raw shouldEqual KeyMongolian
        Marathi.raw shouldEqual KeyMarathi
        Malay.raw shouldEqual KeyMalay
        Maltese.raw shouldEqual KeyMaltese
        Norwegian.raw shouldEqual KeyNorwegian
        Dutch.raw shouldEqual KeyDutch
        NorthernSotho.raw shouldEqual KeyNorthernSotho
        Polish.raw shouldEqual KeyPolish
        Pashto.raw shouldEqual KeyPashto
        Portuguese.raw shouldEqual KeyPortuguese
        Quechua.raw shouldEqual KeyQuechua
        Romanian.raw shouldEqual KeyRomanian
        Russian.raw shouldEqual KeyRussian
        Slovak.raw shouldEqual KeySlovak
        Albanian.raw shouldEqual KeyAlbanian
        Swedish.raw shouldEqual KeySwedish
        Swahili.raw shouldEqual KeySwahili
        Tamil.raw shouldEqual KeyTamil
        Telugu.raw shouldEqual KeyTelugu
        Tagalog.raw shouldEqual KeyTagalog
        Tswana.raw shouldEqual KeyTswana
        Turkish.raw shouldEqual KeyTurkish
        Tatar.raw shouldEqual KeyTatar
    }
}