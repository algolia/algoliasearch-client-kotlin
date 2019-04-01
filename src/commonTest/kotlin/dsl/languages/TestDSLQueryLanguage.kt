package dsl.languages

import com.algolia.search.dsl.languages.DSLQueryLanguage
import com.algolia.search.model.search.QueryLanguage
import shouldEqual
import kotlin.test.Test


internal class TestDSLQueryLanguage {

    @Test
    fun default() {
        val dsl = DSLQueryLanguage().apply {
            +English
        }

        dsl.build() shouldEqual listOf(
            QueryLanguage.English
        )
    }

    @Test
    fun languages() {
        DSLQueryLanguage().apply {
            Afrikaans shouldEqual QueryLanguage.Afrikaans
            Arabic shouldEqual QueryLanguage.Arabic
            Azeri shouldEqual QueryLanguage.Azeri
            Bulgarian shouldEqual QueryLanguage.Bulgarian
            Brunei shouldEqual QueryLanguage.Brunei
            Catalan shouldEqual QueryLanguage.Catalan
            Czech shouldEqual QueryLanguage.Czech
            Welsh shouldEqual QueryLanguage.Welsh
            Danish shouldEqual QueryLanguage.Danish
            German shouldEqual QueryLanguage.German
            English shouldEqual QueryLanguage.English
            Esperanto shouldEqual QueryLanguage.Esperanto
            Spanish shouldEqual QueryLanguage.Spanish
            Estonian shouldEqual QueryLanguage.Estonian
            Basque shouldEqual QueryLanguage.Basque
            Finnish shouldEqual QueryLanguage.Finnish
            Faroese shouldEqual QueryLanguage.Faroese
            French shouldEqual QueryLanguage.French
            Galician shouldEqual QueryLanguage.Galician
            Hebrew shouldEqual QueryLanguage.Hebrew
            QueryLanguage.Hindi shouldEqual QueryLanguage.Hindi
            Hungarian shouldEqual QueryLanguage.Hungarian
            Armenian shouldEqual QueryLanguage.Armenian
            Indonesian shouldEqual QueryLanguage.Indonesian
            Icelandic shouldEqual QueryLanguage.Icelandic
            Italian shouldEqual QueryLanguage.Italian
            Japanese shouldEqual QueryLanguage.Japanese
            Georgian shouldEqual QueryLanguage.Georgian
            Kazakh shouldEqual QueryLanguage.Kazakh
            Korean shouldEqual QueryLanguage.Korean
            Kyrgyz shouldEqual QueryLanguage.Kyrgyz
            Lithuanian shouldEqual QueryLanguage.Lithuanian
            Maori shouldEqual QueryLanguage.Maori
            Mongolian shouldEqual QueryLanguage.Mongolian
            Marathi shouldEqual QueryLanguage.Marathi
            Malay shouldEqual QueryLanguage.Malay
            Maltese shouldEqual QueryLanguage.Maltese
            Norwegian shouldEqual QueryLanguage.Norwegian
            Dutch shouldEqual QueryLanguage.Dutch
            NorthernSotho shouldEqual QueryLanguage.NorthernSotho
            Polish shouldEqual QueryLanguage.Polish
            Pashto shouldEqual QueryLanguage.Pashto
            Portuguese shouldEqual QueryLanguage.Portuguese
            Quechua shouldEqual QueryLanguage.Quechua
            Romanian shouldEqual QueryLanguage.Romanian
            Russian shouldEqual QueryLanguage.Russian
            Slovak shouldEqual QueryLanguage.Slovak
            Albanian shouldEqual QueryLanguage.Albanian
            Swedish shouldEqual QueryLanguage.Swedish
            Swahili shouldEqual QueryLanguage.Swahili
            Tamil shouldEqual QueryLanguage.Tamil
            Telugu shouldEqual QueryLanguage.Telugu
            Tagalog shouldEqual QueryLanguage.Tagalog
            Tswana shouldEqual QueryLanguage.Tswana
            Turkish shouldEqual QueryLanguage.Turkish
            Tatar shouldEqual QueryLanguage.Tatar
        }
    }
}