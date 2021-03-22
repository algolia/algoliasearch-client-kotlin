package com.algolia.search.dsl.languages

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.Language

/**
 * DSL for building a [List] of [Language].
 */
@Suppress("PropertyName")
@DSLParameters
public class DSLLanguage(
    private val languages: MutableList<Language> = mutableListOf()
) {

    public val Afrikaans: Language.Afrikaans = Language.Afrikaans
    public val Arabic: Language.Arabic = Language.Arabic
    public val Azeri: Language.Azeri = Language.Azeri
    public val Bulgarian: Language.Bulgarian = Language.Bulgarian
    public val Brunei: Language.Brunei = Language.Brunei
    public val Catalan: Language.Catalan = Language.Catalan
    public val Czech: Language.Czech = Language.Czech
    public val Welsh: Language.Welsh = Language.Welsh
    public val Danish: Language.Danish = Language.Danish
    public val German: Language.German = Language.German
    public val English: Language.English = Language.English
    public val Esperanto: Language.Esperanto = Language.Esperanto
    public val Spanish: Language.Spanish = Language.Spanish
    public val Estonian: Language.Estonian = Language.Estonian
    public val Basque: Language.Basque = Language.Basque
    public val Finnish: Language.Finnish = Language.Finnish
    public val Faroese: Language.Faroese = Language.Faroese
    public val French: Language.French = Language.French
    public val Galician: Language.Galician = Language.Galician
    public val Hebrew: Language.Hebrew = Language.Hebrew
    public val Hindi: Language.Hindi = Language.Hindi
    public val Hungarian: Language.Hungarian = Language.Hungarian
    public val Armenian: Language.Armenian = Language.Armenian
    public val Indonesian: Language.Indonesian = Language.Indonesian
    public val Icelandic: Language.Icelandic = Language.Icelandic
    public val Italian: Language.Italian = Language.Italian
    public val Japanese: Language.Japanese = Language.Japanese
    public val Georgian: Language.Georgian = Language.Georgian
    public val Kazakh: Language.Kazakh = Language.Kazakh
    public val Korean: Language.Korean = Language.Korean
    public val Kyrgyz: Language.Kyrgyz = Language.Kyrgyz
    public val Lithuanian: Language.Lithuanian = Language.Lithuanian
    public val Maori: Language.Maori = Language.Maori
    public val Mongolian: Language.Mongolian = Language.Mongolian
    public val Marathi: Language.Marathi = Language.Marathi
    public val Malay: Language.Malay = Language.Malay
    public val Maltese: Language.Maltese = Language.Maltese
    public val Norwegian: Language.Norwegian = Language.Norwegian
    public val Dutch: Language.Dutch = Language.Dutch
    public val NorthernSotho: Language.NorthernSotho = Language.NorthernSotho
    public val Polish: Language.Polish = Language.Polish
    public val Pashto: Language.Pashto = Language.Pashto
    public val Portuguese: Language.Portuguese = Language.Portuguese
    public val Quechua: Language.Quechua = Language.Quechua
    public val Romanian: Language.Romanian = Language.Romanian
    public val Russian: Language.Russian = Language.Russian
    public val Slovak: Language.Slovak = Language.Slovak
    public val Albanian: Language.Albanian = Language.Albanian
    public val Swedish: Language.Swedish = Language.Swedish
    public val Swahili: Language.Swahili = Language.Swahili
    public val Tamil: Language.Tamil = Language.Tamil
    public val Telugu: Language.Telugu = Language.Telugu
    public val Tagalog: Language.Tagalog = Language.Tagalog
    public val Tswana: Language.Tswana = Language.Tswana
    public val Turkish: Language.Turkish = Language.Turkish
    public val Tatar: Language.Tatar = Language.Tatar

    /**
     * Add [this] to [languages].
     */
    public operator fun Language.unaryPlus() {
        languages += this
    }

    public companion object : DSL<DSLLanguage, List<Language>> {

        override operator fun invoke(block: DSLLanguage.() -> Unit): List<Language> {
            return DSLLanguage().apply(block).languages
        }
    }
}
