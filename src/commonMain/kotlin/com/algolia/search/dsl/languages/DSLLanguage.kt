package com.algolia.search.dsl.languages

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.search.Language

/**
 * DSL for building a [List] of [Language].
 */
@Suppress("PropertyName")
@DSLParameters
class DSLLanguage(
    private val languages: MutableList<Language> = mutableListOf()
) {

    val Afrikaans = Language.Afrikaans
    val Arabic = Language.Arabic
    val Azeri = Language.Azeri
    val Bulgarian = Language.Bulgarian
    val Brunei = Language.Brunei
    val Catalan = Language.Catalan
    val Czech = Language.Czech
    val Welsh = Language.Welsh
    val Danish = Language.Danish
    val German = Language.German
    val English = Language.English
    val Esperanto = Language.Esperanto
    val Spanish = Language.Spanish
    val Estonian = Language.Estonian
    val Basque = Language.Basque
    val Finnish = Language.Finnish
    val Faroese = Language.Faroese
    val French = Language.French
    val Galician = Language.Galician
    val Hebrew = Language.Hebrew
    val Hindi = Language.Hindi
    val Hungarian = Language.Hungarian
    val Armenian = Language.Armenian
    val Indonesian = Language.Indonesian
    val Icelandic = Language.Icelandic
    val Italian = Language.Italian
    val Japanese = Language.Japanese
    val Georgian = Language.Georgian
    val Kazakh = Language.Kazakh
    val Korean = Language.Korean
    val Kyrgyz = Language.Kyrgyz
    val Lithuanian = Language.Lithuanian
    val Maori = Language.Maori
    val Mongolian = Language.Mongolian
    val Marathi = Language.Marathi
    val Malay = Language.Malay
    val Maltese = Language.Maltese
    val Norwegian = Language.Norwegian
    val Dutch = Language.Dutch
    val NorthernSotho = Language.NorthernSotho
    val Polish = Language.Polish
    val Pashto = Language.Pashto
    val Portuguese = Language.Portuguese
    val Quechua = Language.Quechua
    val Romanian = Language.Romanian
    val Russian = Language.Russian
    val Slovak = Language.Slovak
    val Albanian = Language.Albanian
    val Swedish = Language.Swedish
    val Swahili = Language.Swahili
    val Tamil = Language.Tamil
    val Telugu = Language.Telugu
    val Tagalog = Language.Tagalog
    val Tswana = Language.Tswana
    val Turkish = Language.Turkish
    val Tatar = Language.Tatar

    /**
     * Add [this] to [languages].
     */
    operator fun Language.unaryPlus() {
        languages += this
    }

    companion object : DSL<DSLLanguage, List<Language>> {

        override operator fun invoke(block: DSLLanguage.() -> Unit): List<Language> {
            return DSLLanguage().apply(block).languages
        }
    }
}
