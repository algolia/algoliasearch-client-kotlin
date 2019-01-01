package serialize

import client.data.QueryLanguage
import client.data.QueryLanguage.*
import client.serialize.*
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestQueryLanguage : TestSerializer<QueryLanguage>(QueryLanguage) {

    override val item = listOf(
        Afrikaans to JsonPrimitive(KeyAfrikaans),
        Arabic to JsonPrimitive(KeyArabic),
        Azeri to JsonPrimitive(KeyAzeri),
        Bulgarian to JsonPrimitive(KeyBulgarian),
        Brunei to JsonPrimitive(KeyBrunei),
        Catalan to JsonPrimitive(KeyCatalan),
        Czech to JsonPrimitive(KeyCzech),
        Welsh to JsonPrimitive(KeyWelsh),
        Danish to JsonPrimitive(KeyDanish),
        German to JsonPrimitive(KeyGerman),
        English to JsonPrimitive(KeyEnglish),
        Esperanto to JsonPrimitive(KeyEsperanto),
        Spanish to JsonPrimitive(KeySpanish),
        Estonian to JsonPrimitive(KeyEstonian),
        Basque to JsonPrimitive(KeyBasque),
        Finnish to JsonPrimitive(KeyFinnish),
        Faroese to JsonPrimitive(KeyFaroese),
        French to JsonPrimitive(KeyFrench),
        Galician to JsonPrimitive(KeyGalician),
        Hebrew to JsonPrimitive(KeyHebrew),
        Hindi to JsonPrimitive(KeyHindi),
        Hungarian to JsonPrimitive(KeyHungarian),
        Armenian to JsonPrimitive(KeyArmenian),
        Indonesian to JsonPrimitive(KeyIndonesian),
        Icelandic to JsonPrimitive(KeyIcelandic),
        Italian to JsonPrimitive(KeyItalian),
        Japanese to JsonPrimitive(KeyJapanese),
        Georgian to JsonPrimitive(KeyGeorgian),
        Kazakh to JsonPrimitive(KeyKazakh),
        Korean to JsonPrimitive(KeyKorean),
        Kyrgyz to JsonPrimitive(KeyKyrgyz),
        Lithuanian to JsonPrimitive(KeyLithuanian),
        Maori to JsonPrimitive(KeyMaori),
        Mongolian to JsonPrimitive(KeyMongolian),
        Marathi to JsonPrimitive(KeyMarathi),
        Malay to JsonPrimitive(KeyMalay),
        Maltese to JsonPrimitive(KeyMaltese),
        Norwegian to JsonPrimitive(KeyNorwegian),
        Dutch to JsonPrimitive(KeyDutch),
        NorthernSotho to JsonPrimitive(KeyNorthernSotho),
        Polish to JsonPrimitive(KeyPolish),
        Pashto to JsonPrimitive(KeyPashto),
        Portuguese to JsonPrimitive(KeyPortuguese),
        Quechua to JsonPrimitive(KeyQuechua),
        Romanian to JsonPrimitive(KeyRomanian),
        Russian to JsonPrimitive(KeyRussian),
        Slovak to JsonPrimitive(KeySlovak),
        Albanian to JsonPrimitive(KeyAlbanian),
        Swedish to JsonPrimitive(KeySwedish),
        Swahili to JsonPrimitive(KeySwahili),
        Tamil to JsonPrimitive(KeyTamil),
        Telugu to JsonPrimitive(KeyTelugu),
        Tagalog to JsonPrimitive(KeyTagalog),
        Tswana to JsonPrimitive(KeyTswana),
        Turkish to JsonPrimitive(KeyTurkish),
        Tatar to JsonPrimitive(KeyTatar)
    )
}