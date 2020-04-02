package dsl.languages

import com.algolia.search.dsl.languages.DSLLanguage
import com.algolia.search.model.search.Language
import shouldEqual
import kotlin.test.Test

internal class TestDSLLanguage {

    @Test
    fun default() {
        val dsl = DSLLanguage {
            +English
        }

        dsl shouldEqual listOf(
            Language.English
        )
    }

    @Test
    fun dsl() {
        com.algolia.search.dsl.languages.languages { +Language.Japanese } shouldEqual listOf(Language.Japanese)
    }

    @Test
    fun languages() {
        DSLLanguage().apply {
            Afrikaans shouldEqual Language.Afrikaans
            Arabic shouldEqual Language.Arabic
            Azeri shouldEqual Language.Azeri
            Bulgarian shouldEqual Language.Bulgarian
            Brunei shouldEqual Language.Brunei
            Catalan shouldEqual Language.Catalan
            Czech shouldEqual Language.Czech
            Welsh shouldEqual Language.Welsh
            Danish shouldEqual Language.Danish
            German shouldEqual Language.German
            English shouldEqual Language.English
            Esperanto shouldEqual Language.Esperanto
            Spanish shouldEqual Language.Spanish
            Estonian shouldEqual Language.Estonian
            Basque shouldEqual Language.Basque
            Finnish shouldEqual Language.Finnish
            Faroese shouldEqual Language.Faroese
            French shouldEqual Language.French
            Galician shouldEqual Language.Galician
            Hebrew shouldEqual Language.Hebrew
            Hindi shouldEqual Language.Hindi
            Hungarian shouldEqual Language.Hungarian
            Armenian shouldEqual Language.Armenian
            Indonesian shouldEqual Language.Indonesian
            Icelandic shouldEqual Language.Icelandic
            Italian shouldEqual Language.Italian
            Japanese shouldEqual Language.Japanese
            Georgian shouldEqual Language.Georgian
            Kazakh shouldEqual Language.Kazakh
            Korean shouldEqual Language.Korean
            Kyrgyz shouldEqual Language.Kyrgyz
            Lithuanian shouldEqual Language.Lithuanian
            Maori shouldEqual Language.Maori
            Mongolian shouldEqual Language.Mongolian
            Marathi shouldEqual Language.Marathi
            Malay shouldEqual Language.Malay
            Maltese shouldEqual Language.Maltese
            Norwegian shouldEqual Language.Norwegian
            Dutch shouldEqual Language.Dutch
            NorthernSotho shouldEqual Language.NorthernSotho
            Polish shouldEqual Language.Polish
            Pashto shouldEqual Language.Pashto
            Portuguese shouldEqual Language.Portuguese
            Quechua shouldEqual Language.Quechua
            Romanian shouldEqual Language.Romanian
            Russian shouldEqual Language.Russian
            Slovak shouldEqual Language.Slovak
            Albanian shouldEqual Language.Albanian
            Swedish shouldEqual Language.Swedish
            Swahili shouldEqual Language.Swahili
            Tamil shouldEqual Language.Tamil
            Telugu shouldEqual Language.Telugu
            Tagalog shouldEqual Language.Tagalog
            Tswana shouldEqual Language.Tswana
            Turkish shouldEqual Language.Turkish
            Tatar shouldEqual Language.Tatar
        }
    }
}
