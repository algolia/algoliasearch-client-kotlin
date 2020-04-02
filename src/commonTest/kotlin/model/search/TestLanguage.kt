package model.search

import com.algolia.search.model.search.Language.Afrikaans
import com.algolia.search.model.search.Language.Albanian
import com.algolia.search.model.search.Language.Arabic
import com.algolia.search.model.search.Language.Armenian
import com.algolia.search.model.search.Language.Azeri
import com.algolia.search.model.search.Language.Basque
import com.algolia.search.model.search.Language.Brunei
import com.algolia.search.model.search.Language.Bulgarian
import com.algolia.search.model.search.Language.Catalan
import com.algolia.search.model.search.Language.Czech
import com.algolia.search.model.search.Language.Danish
import com.algolia.search.model.search.Language.Dutch
import com.algolia.search.model.search.Language.English
import com.algolia.search.model.search.Language.Esperanto
import com.algolia.search.model.search.Language.Estonian
import com.algolia.search.model.search.Language.Faroese
import com.algolia.search.model.search.Language.Finnish
import com.algolia.search.model.search.Language.French
import com.algolia.search.model.search.Language.Galician
import com.algolia.search.model.search.Language.Georgian
import com.algolia.search.model.search.Language.German
import com.algolia.search.model.search.Language.Hebrew
import com.algolia.search.model.search.Language.Hindi
import com.algolia.search.model.search.Language.Hungarian
import com.algolia.search.model.search.Language.Icelandic
import com.algolia.search.model.search.Language.Indonesian
import com.algolia.search.model.search.Language.Italian
import com.algolia.search.model.search.Language.Japanese
import com.algolia.search.model.search.Language.Kazakh
import com.algolia.search.model.search.Language.Korean
import com.algolia.search.model.search.Language.Kyrgyz
import com.algolia.search.model.search.Language.Lithuanian
import com.algolia.search.model.search.Language.Malay
import com.algolia.search.model.search.Language.Maltese
import com.algolia.search.model.search.Language.Maori
import com.algolia.search.model.search.Language.Marathi
import com.algolia.search.model.search.Language.Mongolian
import com.algolia.search.model.search.Language.NorthernSotho
import com.algolia.search.model.search.Language.Norwegian
import com.algolia.search.model.search.Language.Pashto
import com.algolia.search.model.search.Language.Polish
import com.algolia.search.model.search.Language.Portuguese
import com.algolia.search.model.search.Language.Quechua
import com.algolia.search.model.search.Language.Romanian
import com.algolia.search.model.search.Language.Russian
import com.algolia.search.model.search.Language.Slovak
import com.algolia.search.model.search.Language.Spanish
import com.algolia.search.model.search.Language.Swahili
import com.algolia.search.model.search.Language.Swedish
import com.algolia.search.model.search.Language.Tagalog
import com.algolia.search.model.search.Language.Tamil
import com.algolia.search.model.search.Language.Tatar
import com.algolia.search.model.search.Language.Telugu
import com.algolia.search.model.search.Language.Tswana
import com.algolia.search.model.search.Language.Turkish
import com.algolia.search.model.search.Language.Welsh
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
