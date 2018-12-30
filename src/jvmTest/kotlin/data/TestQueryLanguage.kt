package data

import client.data.QueryLanguage.*
import client.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestQueryLanguage {

    @Test
    fun raw() {
        KeyAfrikaans shouldEqual Afrikaans.raw
        KeyArabic shouldEqual Arabic.raw
        KeyAzeri shouldEqual Azeri.raw
        KeyBulgarian shouldEqual Bulgarian.raw
        KeyBrunei shouldEqual Brunei.raw
        KeyCatalan shouldEqual Catalan.raw
        KeyCzech shouldEqual Czech.raw
        KeyWelsh shouldEqual Welsh.raw
        KeyDanish shouldEqual Danish.raw
        KeyGerman shouldEqual German.raw
        KeyEnglish shouldEqual English.raw
        KeyEsperanto shouldEqual Esperanto.raw
        KeySpanish shouldEqual Spanish.raw
        KeyEstonian shouldEqual Estonian.raw
        KeyBasque shouldEqual Basque.raw
        KeyFinnish shouldEqual Finnish.raw
        KeyFaroese shouldEqual Faroese.raw
        KeyFrench shouldEqual French.raw
        KeyGalician shouldEqual Galician.raw
        KeyHebrew shouldEqual Hebrew.raw
        KeyHindi shouldEqual Hindi.raw
        KeyHungarian shouldEqual Hungarian.raw
        KeyArmenian shouldEqual Armenian.raw
        KeyIndonesian shouldEqual Indonesian.raw
        KeyIcelandic shouldEqual Icelandic.raw
        KeyItalian shouldEqual Italian.raw
        KeyJapanese shouldEqual Japanese.raw
        KeyGeorgian shouldEqual Georgian.raw
        KeyKazakh shouldEqual Kazakh.raw
        KeyKorean shouldEqual Korean.raw
        KeyKyrgyz shouldEqual Kyrgyz.raw
        KeyLithuanian shouldEqual Lithuanian.raw
        KeyMaori shouldEqual Maori.raw
        KeyMongolian shouldEqual Mongolian.raw
        KeyMarathi shouldEqual Marathi.raw
        KeyMalay shouldEqual Malay.raw
        KeyMaltese shouldEqual Maltese.raw
        KeyNorwegian shouldEqual Norwegian.raw
        KeyDutch shouldEqual Dutch.raw
        KeyNorthernSotho shouldEqual NorthernSotho.raw
        KeyPolish shouldEqual Polish.raw
        KeyPashto shouldEqual Pashto.raw
        KeyPortuguese shouldEqual Portuguese.raw
        KeyQuechua shouldEqual Quechua.raw
        KeyRomanian shouldEqual Romanian.raw
        KeyRussian shouldEqual Russian.raw
        KeySlovak shouldEqual Slovak.raw
        KeyAlbanian shouldEqual Albanian.raw
        KeySwedish shouldEqual Swedish.raw
        KeySwahili shouldEqual Swahili.raw
        KeyTamil shouldEqual Tamil.raw
        KeyTelugu shouldEqual Telugu.raw
        KeyTagalog shouldEqual Tagalog.raw
        KeyTswana shouldEqual Tswana.raw
        KeyTurkish shouldEqual Turkish.raw
        KeyTatar shouldEqual Tatar.raw
    }
}