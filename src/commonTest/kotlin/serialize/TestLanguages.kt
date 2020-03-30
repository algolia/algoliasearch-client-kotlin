package serialize

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
import kotlin.test.Test
import shouldEqual

internal class TestLanguages {

    @Test
    fun test() {
        KeyAfrikaans shouldEqual "af"
        KeyArabic shouldEqual "ar"
        KeyAzeri shouldEqual "az"
        KeyBulgarian shouldEqual "bg"
        KeyBrunei shouldEqual "bn"
        KeyCatalan shouldEqual "ca"
        KeyCzech shouldEqual "cs"
        KeyWelsh shouldEqual "cy"
        KeyDanish shouldEqual "da"
        KeyGerman shouldEqual "de"
        KeyEnglish shouldEqual "en"
        KeyEsperanto shouldEqual "eo"
        KeySpanish shouldEqual "es"
        KeyEstonian shouldEqual "et"
        KeyBasque shouldEqual "eu"
        KeyFinnish shouldEqual "fi"
        KeyFaroese shouldEqual "fo"
        KeyFrench shouldEqual "fr"
        KeyGalician shouldEqual "gl"
        KeyHebrew shouldEqual "he"
        KeyHindi shouldEqual "hi"
        KeyHungarian shouldEqual "hu"
        KeyArmenian shouldEqual "hy"
        KeyIndonesian shouldEqual "id"
        KeyIcelandic shouldEqual "is"
        KeyItalian shouldEqual "it"
        KeyJapanese shouldEqual "ja"
        KeyGeorgian shouldEqual "ka"
        KeyKazakh shouldEqual "kk"
        KeyKorean shouldEqual "ko"
        KeyKyrgyz shouldEqual "ky"
        KeyLithuanian shouldEqual "lt"
        KeyMaori shouldEqual "mi"
        KeyMongolian shouldEqual "mn"
        KeyMarathi shouldEqual "mr"
        KeyMalay shouldEqual "ms"
        KeyMaltese shouldEqual "mt"
        KeyNorwegian shouldEqual "nb"
        KeyDutch shouldEqual "nl"
        KeyNorthernSotho shouldEqual "ns"
        KeyPolish shouldEqual "pl"
        KeyPashto shouldEqual "ps"
        KeyPortuguese shouldEqual "pt"
        KeyQuechua shouldEqual "qu"
        KeyRomanian shouldEqual "ro"
        KeyRussian shouldEqual "ru"
        KeySlovak shouldEqual "sk"
        KeyAlbanian shouldEqual "sq"
        KeySwedish shouldEqual "sv"
        KeySwahili shouldEqual "sw"
        KeyTamil shouldEqual "ta"
        KeyTelugu shouldEqual "te"
        KeyTagalog shouldEqual "tl"
        KeyTswana shouldEqual "tn"
        KeyTurkish shouldEqual "tr"
        KeyTatar shouldEqual "tt"
    }
}
