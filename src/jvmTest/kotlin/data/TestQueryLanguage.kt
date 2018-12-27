package data

import client.data.QueryLanguage
import client.serialize.*
import kotlinx.serialization.json.JsonPrimitive
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestQueryLanguage : TestSerializer<QueryLanguage> {

    override val serializer = QueryLanguage

    @Test
    fun key() {
        assertEquals("af", KeyAfrikaans)
        assertEquals("ar", KeyArabic)
        assertEquals("az", KeyAzeri)
        assertEquals("bg", KeyBulgarian)
        assertEquals("bn", KeyBrunei)
        assertEquals("ca", KeyCatalan)
        assertEquals("cs", KeyCzech)
        assertEquals("cy", KeyWelsh)
        assertEquals("da", KeyDanish)
        assertEquals("de", KeyGerman)
        assertEquals("en", KeyEnglish)
        assertEquals("eo", KeyEsperanto)
        assertEquals("es", KeySpanish)
        assertEquals("et", KeyEstonian)
        assertEquals("eu", KeyBasque)
        assertEquals("fi", KeyFinnish)
        assertEquals("fo", KeyFaroese)
        assertEquals("fr", KeyFrench)
        assertEquals("gl", KeyGalician)
        assertEquals("he", KeyHebrew)
        assertEquals("hi", KeyHindi)
        assertEquals("hu", KeyHungarian)
        assertEquals("hy", KeyArmenian)
        assertEquals("id", KeyIndonesian)
        assertEquals("is", KeyIcelandic)
        assertEquals("it", KeyItalian)
        assertEquals("ja", KeyJapanese)
        assertEquals("ka", KeyGeorgian)
        assertEquals("kk", KeyKazakh)
        assertEquals("ko", KeyKorean)
        assertEquals("ky", KeyKyrgyz)
        assertEquals("lt", KeyLithuanian)
        assertEquals("mi", KeyMaori)
        assertEquals("mn", KeyMongolian)
        assertEquals("mr", KeyMarathi)
        assertEquals("ms", KeyMalay)
        assertEquals("mt", KeyMaltese)
        assertEquals("nb", KeyNorwegian)
        assertEquals("nl", KeyDutch)
        assertEquals("ns", KeyNorthernSotho)
        assertEquals("pl", KeyPolish)
        assertEquals("ps", KeyPashto)
        assertEquals("pt", KeyPortuguese)
        assertEquals("qu", KeyQuechua)
        assertEquals("ro", KeyRomanian)
        assertEquals("ru", KeyRussian)
        assertEquals("sk", KeySlovak)
        assertEquals("sq", KeyAlbanian)
        assertEquals("sv", KeySwedish)
        assertEquals("sw", KeySwahili)
        assertEquals("ta", KeyTamil)
        assertEquals("te", KeyTelugu)
        assertEquals("tl", KeyTagalog)
        assertEquals("tn", KeyTswana)
        assertEquals("tr", KeyTurkish)
        assertEquals("tt", KeyTatar)
    }

    @Test
    fun raw() {
        assertEquals(KeyAfrikaans, QueryLanguage.Afrikaans.raw)
        assertEquals(KeyArabic, QueryLanguage.Arabic.raw)
        assertEquals(KeyAzeri, QueryLanguage.Azeri.raw)
        assertEquals(KeyBulgarian, QueryLanguage.Bulgarian.raw)
        assertEquals(KeyBrunei, QueryLanguage.Brunei.raw)
        assertEquals(KeyCatalan, QueryLanguage.Catalan.raw)
        assertEquals(KeyCzech, QueryLanguage.Czech.raw)
        assertEquals(KeyWelsh, QueryLanguage.Welsh.raw)
        assertEquals(KeyDanish, QueryLanguage.Danish.raw)
        assertEquals(KeyGerman, QueryLanguage.German.raw)
        assertEquals(KeyEnglish, QueryLanguage.English.raw)
        assertEquals(KeyEsperanto, QueryLanguage.Esperanto.raw)
        assertEquals(KeySpanish, QueryLanguage.Spanish.raw)
        assertEquals(KeyEstonian, QueryLanguage.Estonian.raw)
        assertEquals(KeyBasque, QueryLanguage.Basque.raw)
        assertEquals(KeyFinnish, QueryLanguage.Finnish.raw)
        assertEquals(KeyFaroese, QueryLanguage.Faroese.raw)
        assertEquals(KeyFrench, QueryLanguage.French.raw)
        assertEquals(KeyGalician, QueryLanguage.Galician.raw)
        assertEquals(KeyHebrew, QueryLanguage.Hebrew.raw)
        assertEquals(KeyHindi, QueryLanguage.Hindi.raw)
        assertEquals(KeyHungarian, QueryLanguage.Hungarian.raw)
        assertEquals(KeyArmenian, QueryLanguage.Armenian.raw)
        assertEquals(KeyIndonesian, QueryLanguage.Indonesian.raw)
        assertEquals(KeyIcelandic, QueryLanguage.Icelandic.raw)
        assertEquals(KeyItalian, QueryLanguage.Italian.raw)
        assertEquals(KeyJapanese, QueryLanguage.Japanese.raw)
        assertEquals(KeyGeorgian, QueryLanguage.Georgian.raw)
        assertEquals(KeyKazakh, QueryLanguage.Kazakh.raw)
        assertEquals(KeyKorean, QueryLanguage.Korean.raw)
        assertEquals(KeyKyrgyz, QueryLanguage.Kyrgyz.raw)
        assertEquals(KeyLithuanian, QueryLanguage.Lithuanian.raw)
        assertEquals(KeyMaori, QueryLanguage.Maori.raw)
        assertEquals(KeyMongolian, QueryLanguage.Mongolian.raw)
        assertEquals(KeyMarathi, QueryLanguage.Marathi.raw)
        assertEquals(KeyMalay, QueryLanguage.Malay.raw)
        assertEquals(KeyMaltese, QueryLanguage.Maltese.raw)
        assertEquals(KeyNorwegian, QueryLanguage.Norwegian.raw)
        assertEquals(KeyDutch, QueryLanguage.Dutch.raw)
        assertEquals(KeyNorthernSotho, QueryLanguage.NorthernSotho.raw)
        assertEquals(KeyPolish, QueryLanguage.Polish.raw)
        assertEquals(KeyPashto, QueryLanguage.Pashto.raw)
        assertEquals(KeyPortuguese, QueryLanguage.Portuguese.raw)
        assertEquals(KeyQuechua, QueryLanguage.Quechua.raw)
        assertEquals(KeyRomanian, QueryLanguage.Romanian.raw)
        assertEquals(KeyRussian, QueryLanguage.Russian.raw)
        assertEquals(KeySlovak, QueryLanguage.Slovak.raw)
        assertEquals(KeyAlbanian, QueryLanguage.Albanian.raw)
        assertEquals(KeySwedish, QueryLanguage.Swedish.raw)
        assertEquals(KeySwahili, QueryLanguage.Swahili.raw)
        assertEquals(KeyTamil, QueryLanguage.Tamil.raw)
        assertEquals(KeyTelugu, QueryLanguage.Telugu.raw)
        assertEquals(KeyTagalog, QueryLanguage.Tagalog.raw)
        assertEquals(KeyTswana, QueryLanguage.Tswana.raw)
        assertEquals(KeyTurkish, QueryLanguage.Turkish.raw)
        assertEquals(KeyTatar, QueryLanguage.Tatar.raw)
    }

    @Test
    override fun serialize() {
        testSerialize(JsonPrimitive(KeyAfrikaans), QueryLanguage.Afrikaans)
        testSerialize(JsonPrimitive(KeyArabic), QueryLanguage.Arabic)
        testSerialize(JsonPrimitive(KeyAzeri), QueryLanguage.Azeri)
        testSerialize(JsonPrimitive(KeyBulgarian), QueryLanguage.Bulgarian)
        testSerialize(JsonPrimitive(KeyBrunei), QueryLanguage.Brunei)
        testSerialize(JsonPrimitive(KeyCatalan), QueryLanguage.Catalan)
        testSerialize(JsonPrimitive(KeyCzech), QueryLanguage.Czech)
        testSerialize(JsonPrimitive(KeyWelsh), QueryLanguage.Welsh)
        testSerialize(JsonPrimitive(KeyDanish), QueryLanguage.Danish)
        testSerialize(JsonPrimitive(KeyGerman), QueryLanguage.German)
        testSerialize(JsonPrimitive(KeyEnglish), QueryLanguage.English)
        testSerialize(JsonPrimitive(KeyEsperanto), QueryLanguage.Esperanto)
        testSerialize(JsonPrimitive(KeySpanish), QueryLanguage.Spanish)
        testSerialize(JsonPrimitive(KeyEstonian), QueryLanguage.Estonian)
        testSerialize(JsonPrimitive(KeyBasque), QueryLanguage.Basque)
        testSerialize(JsonPrimitive(KeyFinnish), QueryLanguage.Finnish)
        testSerialize(JsonPrimitive(KeyFaroese), QueryLanguage.Faroese)
        testSerialize(JsonPrimitive(KeyFrench), QueryLanguage.French)
        testSerialize(JsonPrimitive(KeyGalician), QueryLanguage.Galician)
        testSerialize(JsonPrimitive(KeyHebrew), QueryLanguage.Hebrew)
        testSerialize(JsonPrimitive(KeyHindi), QueryLanguage.Hindi)
        testSerialize(JsonPrimitive(KeyHungarian), QueryLanguage.Hungarian)
        testSerialize(JsonPrimitive(KeyArmenian), QueryLanguage.Armenian)
        testSerialize(JsonPrimitive(KeyIndonesian), QueryLanguage.Indonesian)
        testSerialize(JsonPrimitive(KeyIcelandic), QueryLanguage.Icelandic)
        testSerialize(JsonPrimitive(KeyItalian), QueryLanguage.Italian)
        testSerialize(JsonPrimitive(KeyJapanese), QueryLanguage.Japanese)
        testSerialize(JsonPrimitive(KeyGeorgian), QueryLanguage.Georgian)
        testSerialize(JsonPrimitive(KeyKazakh), QueryLanguage.Kazakh)
        testSerialize(JsonPrimitive(KeyKorean), QueryLanguage.Korean)
        testSerialize(JsonPrimitive(KeyKyrgyz), QueryLanguage.Kyrgyz)
        testSerialize(JsonPrimitive(KeyLithuanian), QueryLanguage.Lithuanian)
        testSerialize(JsonPrimitive(KeyMaori), QueryLanguage.Maori)
        testSerialize(JsonPrimitive(KeyMongolian), QueryLanguage.Mongolian)
        testSerialize(JsonPrimitive(KeyMarathi), QueryLanguage.Marathi)
        testSerialize(JsonPrimitive(KeyMalay), QueryLanguage.Malay)
        testSerialize(JsonPrimitive(KeyMaltese), QueryLanguage.Maltese)
        testSerialize(JsonPrimitive(KeyNorwegian), QueryLanguage.Norwegian)
        testSerialize(JsonPrimitive(KeyDutch), QueryLanguage.Dutch)
        testSerialize(JsonPrimitive(KeyNorthernSotho), QueryLanguage.NorthernSotho)
        testSerialize(JsonPrimitive(KeyPolish), QueryLanguage.Polish)
        testSerialize(JsonPrimitive(KeyPashto), QueryLanguage.Pashto)
        testSerialize(JsonPrimitive(KeyPortuguese), QueryLanguage.Portuguese)
        testSerialize(JsonPrimitive(KeyQuechua), QueryLanguage.Quechua)
        testSerialize(JsonPrimitive(KeyRomanian), QueryLanguage.Romanian)
        testSerialize(JsonPrimitive(KeyRussian), QueryLanguage.Russian)
        testSerialize(JsonPrimitive(KeySlovak), QueryLanguage.Slovak)
        testSerialize(JsonPrimitive(KeyAlbanian), QueryLanguage.Albanian)
        testSerialize(JsonPrimitive(KeySwedish), QueryLanguage.Swedish)
        testSerialize(JsonPrimitive(KeySwahili), QueryLanguage.Swahili)
        testSerialize(JsonPrimitive(KeyTamil), QueryLanguage.Tamil)
        testSerialize(JsonPrimitive(KeyTelugu), QueryLanguage.Telugu)
        testSerialize(JsonPrimitive(KeyTagalog), QueryLanguage.Tagalog)
        testSerialize(JsonPrimitive(KeyTswana), QueryLanguage.Tswana)
        testSerialize(JsonPrimitive(KeyTurkish), QueryLanguage.Turkish)
        testSerialize(JsonPrimitive(KeyTatar), QueryLanguage.Tatar)
        testSerializeNull()
    }

    @Test
    override fun deserialize() {
        testDeserialize(QueryLanguage.Afrikaans, JsonPrimitive(KeyAfrikaans))
        testDeserialize(QueryLanguage.Arabic, JsonPrimitive(KeyArabic))
        testDeserialize(QueryLanguage.Azeri, JsonPrimitive(KeyAzeri))
        testDeserialize(QueryLanguage.Bulgarian, JsonPrimitive(KeyBulgarian))
        testDeserialize(QueryLanguage.Brunei, JsonPrimitive(KeyBrunei))
        testDeserialize(QueryLanguage.Catalan, JsonPrimitive(KeyCatalan))
        testDeserialize(QueryLanguage.Czech, JsonPrimitive(KeyCzech))
        testDeserialize(QueryLanguage.Welsh, JsonPrimitive(KeyWelsh))
        testDeserialize(QueryLanguage.Danish, JsonPrimitive(KeyDanish))
        testDeserialize(QueryLanguage.German, JsonPrimitive(KeyGerman))
        testDeserialize(QueryLanguage.English, JsonPrimitive(KeyEnglish))
        testDeserialize(QueryLanguage.Esperanto, JsonPrimitive(KeyEsperanto))
        testDeserialize(QueryLanguage.Spanish, JsonPrimitive(KeySpanish))
        testDeserialize(QueryLanguage.Estonian, JsonPrimitive(KeyEstonian))
        testDeserialize(QueryLanguage.Basque, JsonPrimitive(KeyBasque))
        testDeserialize(QueryLanguage.Finnish, JsonPrimitive(KeyFinnish))
        testDeserialize(QueryLanguage.Faroese, JsonPrimitive(KeyFaroese))
        testDeserialize(QueryLanguage.French, JsonPrimitive(KeyFrench))
        testDeserialize(QueryLanguage.Galician, JsonPrimitive(KeyGalician))
        testDeserialize(QueryLanguage.Hebrew, JsonPrimitive(KeyHebrew))
        testDeserialize(QueryLanguage.Hindi, JsonPrimitive(KeyHindi))
        testDeserialize(QueryLanguage.Hungarian, JsonPrimitive(KeyHungarian))
        testDeserialize(QueryLanguage.Armenian, JsonPrimitive(KeyArmenian))
        testDeserialize(QueryLanguage.Indonesian, JsonPrimitive(KeyIndonesian))
        testDeserialize(QueryLanguage.Icelandic, JsonPrimitive(KeyIcelandic))
        testDeserialize(QueryLanguage.Italian, JsonPrimitive(KeyItalian))
        testDeserialize(QueryLanguage.Japanese, JsonPrimitive(KeyJapanese))
        testDeserialize(QueryLanguage.Georgian, JsonPrimitive(KeyGeorgian))
        testDeserialize(QueryLanguage.Kazakh, JsonPrimitive(KeyKazakh))
        testDeserialize(QueryLanguage.Korean, JsonPrimitive(KeyKorean))
        testDeserialize(QueryLanguage.Kyrgyz, JsonPrimitive(KeyKyrgyz))
        testDeserialize(QueryLanguage.Lithuanian, JsonPrimitive(KeyLithuanian))
        testDeserialize(QueryLanguage.Maori, JsonPrimitive(KeyMaori))
        testDeserialize(QueryLanguage.Mongolian, JsonPrimitive(KeyMongolian))
        testDeserialize(QueryLanguage.Marathi, JsonPrimitive(KeyMarathi))
        testDeserialize(QueryLanguage.Malay, JsonPrimitive(KeyMalay))
        testDeserialize(QueryLanguage.Maltese, JsonPrimitive(KeyMaltese))
        testDeserialize(QueryLanguage.Norwegian, JsonPrimitive(KeyNorwegian))
        testDeserialize(QueryLanguage.Dutch, JsonPrimitive(KeyDutch))
        testDeserialize(QueryLanguage.NorthernSotho, JsonPrimitive(KeyNorthernSotho))
        testDeserialize(QueryLanguage.Polish, JsonPrimitive(KeyPolish))
        testDeserialize(QueryLanguage.Pashto, JsonPrimitive(KeyPashto))
        testDeserialize(QueryLanguage.Portuguese, JsonPrimitive(KeyPortuguese))
        testDeserialize(QueryLanguage.Quechua, JsonPrimitive(KeyQuechua))
        testDeserialize(QueryLanguage.Romanian, JsonPrimitive(KeyRomanian))
        testDeserialize(QueryLanguage.Russian, JsonPrimitive(KeyRussian))
        testDeserialize(QueryLanguage.Slovak, JsonPrimitive(KeySlovak))
        testDeserialize(QueryLanguage.Albanian, JsonPrimitive(KeyAlbanian))
        testDeserialize(QueryLanguage.Swedish, JsonPrimitive(KeySwedish))
        testDeserialize(QueryLanguage.Swahili, JsonPrimitive(KeySwahili))
        testDeserialize(QueryLanguage.Tamil, JsonPrimitive(KeyTamil))
        testDeserialize(QueryLanguage.Telugu, JsonPrimitive(KeyTelugu))
        testDeserialize(QueryLanguage.Tagalog, JsonPrimitive(KeyTagalog))
        testDeserialize(QueryLanguage.Tswana, JsonPrimitive(KeyTswana))
        testDeserialize(QueryLanguage.Turkish, JsonPrimitive(KeyTurkish))
        testDeserialize(QueryLanguage.Tatar, JsonPrimitive(KeyTatar))
        testDeserializeNull()
    }
}