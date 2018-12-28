package data

import client.data.QueryLanguage.*
import client.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestQueryLanguage {

    @Test
    fun raw() {
        assertEquals(KeyAfrikaans, Afrikaans.raw)
        assertEquals(KeyArabic, Arabic.raw)
        assertEquals(KeyAzeri, Azeri.raw)
        assertEquals(KeyBulgarian, Bulgarian.raw)
        assertEquals(KeyBrunei, Brunei.raw)
        assertEquals(KeyCatalan, Catalan.raw)
        assertEquals(KeyCzech, Czech.raw)
        assertEquals(KeyWelsh, Welsh.raw)
        assertEquals(KeyDanish, Danish.raw)
        assertEquals(KeyGerman, German.raw)
        assertEquals(KeyEnglish, English.raw)
        assertEquals(KeyEsperanto, Esperanto.raw)
        assertEquals(KeySpanish, Spanish.raw)
        assertEquals(KeyEstonian, Estonian.raw)
        assertEquals(KeyBasque, Basque.raw)
        assertEquals(KeyFinnish, Finnish.raw)
        assertEquals(KeyFaroese, Faroese.raw)
        assertEquals(KeyFrench, French.raw)
        assertEquals(KeyGalician, Galician.raw)
        assertEquals(KeyHebrew, Hebrew.raw)
        assertEquals(KeyHindi, Hindi.raw)
        assertEquals(KeyHungarian, Hungarian.raw)
        assertEquals(KeyArmenian, Armenian.raw)
        assertEquals(KeyIndonesian, Indonesian.raw)
        assertEquals(KeyIcelandic, Icelandic.raw)
        assertEquals(KeyItalian, Italian.raw)
        assertEquals(KeyJapanese, Japanese.raw)
        assertEquals(KeyGeorgian, Georgian.raw)
        assertEquals(KeyKazakh, Kazakh.raw)
        assertEquals(KeyKorean, Korean.raw)
        assertEquals(KeyKyrgyz, Kyrgyz.raw)
        assertEquals(KeyLithuanian, Lithuanian.raw)
        assertEquals(KeyMaori, Maori.raw)
        assertEquals(KeyMongolian, Mongolian.raw)
        assertEquals(KeyMarathi, Marathi.raw)
        assertEquals(KeyMalay, Malay.raw)
        assertEquals(KeyMaltese, Maltese.raw)
        assertEquals(KeyNorwegian, Norwegian.raw)
        assertEquals(KeyDutch, Dutch.raw)
        assertEquals(KeyNorthernSotho, NorthernSotho.raw)
        assertEquals(KeyPolish, Polish.raw)
        assertEquals(KeyPashto, Pashto.raw)
        assertEquals(KeyPortuguese, Portuguese.raw)
        assertEquals(KeyQuechua, Quechua.raw)
        assertEquals(KeyRomanian, Romanian.raw)
        assertEquals(KeyRussian, Russian.raw)
        assertEquals(KeySlovak, Slovak.raw)
        assertEquals(KeyAlbanian, Albanian.raw)
        assertEquals(KeySwedish, Swedish.raw)
        assertEquals(KeySwahili, Swahili.raw)
        assertEquals(KeyTamil, Tamil.raw)
        assertEquals(KeyTelugu, Telugu.raw)
        assertEquals(KeyTagalog, Tagalog.raw)
        assertEquals(KeyTswana, Tswana.raw)
        assertEquals(KeyTurkish, Turkish.raw)
        assertEquals(KeyTatar, Tatar.raw)
    }
}