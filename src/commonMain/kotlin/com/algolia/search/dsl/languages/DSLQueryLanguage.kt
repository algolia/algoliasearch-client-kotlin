package com.algolia.search.dsl.languages

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.QueryLanguage


@Suppress("PropertyName")
@DSLParameters
public class DSLQueryLanguage(
    private val queryLanguages: MutableList<QueryLanguage> = mutableListOf()
) {

    public val Afrikaans = QueryLanguage.Afrikaans
    public val Arabic = QueryLanguage.Arabic
    public val Azeri = QueryLanguage.Azeri
    public val Bulgarian = QueryLanguage.Bulgarian
    public val Brunei = QueryLanguage.Brunei
    public val Catalan = QueryLanguage.Catalan
    public val Czech = QueryLanguage.Czech
    public val Welsh = QueryLanguage.Welsh
    public val Danish = QueryLanguage.Danish
    public val German = QueryLanguage.German
    public val English = QueryLanguage.English
    public val Esperanto = QueryLanguage.Esperanto
    public val Spanish = QueryLanguage.Spanish
    public val Estonian = QueryLanguage.Estonian
    public val Basque = QueryLanguage.Basque
    public val Finnish = QueryLanguage.Finnish
    public val Faroese = QueryLanguage.Faroese
    public val French = QueryLanguage.French
    public val Galician = QueryLanguage.Galician
    public val Hebrew = QueryLanguage.Hebrew
    public val Hindi = QueryLanguage.Hindi
    public val Hungarian = QueryLanguage.Hungarian
    public val Armenian = QueryLanguage.Armenian
    public val Indonesian = QueryLanguage.Indonesian
    public val Icelandic = QueryLanguage.Icelandic
    public val Italian = QueryLanguage.Italian
    public val Japanese = QueryLanguage.Japanese
    public val Georgian = QueryLanguage.Georgian
    public val Kazakh = QueryLanguage.Kazakh
    public val Korean = QueryLanguage.Korean
    public val Kyrgyz = QueryLanguage.Kyrgyz
    public val Lithuanian = QueryLanguage.Lithuanian
    public val Maori = QueryLanguage.Maori
    public val Mongolian = QueryLanguage.Mongolian
    public val Marathi = QueryLanguage.Marathi
    public val Malay = QueryLanguage.Malay
    public val Maltese = QueryLanguage.Maltese
    public val Norwegian = QueryLanguage.Norwegian
    public val Dutch = QueryLanguage.Dutch
    public val NorthernSotho = QueryLanguage.NorthernSotho
    public val Polish = QueryLanguage.Polish
    public val Pashto = QueryLanguage.Pashto
    public val Portuguese = QueryLanguage.Portuguese
    public val Quechua = QueryLanguage.Quechua
    public val Romanian = QueryLanguage.Romanian
    public val Russian = QueryLanguage.Russian
    public val Slovak = QueryLanguage.Slovak
    public val Albanian = QueryLanguage.Albanian
    public val Swedish = QueryLanguage.Swedish
    public val Swahili = QueryLanguage.Swahili
    public val Tamil = QueryLanguage.Tamil
    public val Telugu = QueryLanguage.Telugu
    public val Tagalog = QueryLanguage.Tagalog
    public val Tswana = QueryLanguage.Tswana
    public val Turkish = QueryLanguage.Turkish
    public val Tatar = QueryLanguage.Tatar

    public operator fun QueryLanguage.unaryPlus() {
        queryLanguages += this
    }

    public companion object : DSL<DSLQueryLanguage, List<QueryLanguage>> {

        override operator fun invoke(block: DSLQueryLanguage.() -> Unit): List<QueryLanguage> {
            return DSLQueryLanguage().apply(block).queryLanguages
        }
    }
}