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
import com.algolia.search.serialize.internal.Languages
import shouldEqual
import kotlin.test.Test

internal class TestLanguage {

    @Test
    fun raw() {
        Afrikaans.raw shouldEqual Languages.Afrikaans
        Arabic.raw shouldEqual Languages.Arabic
        Azeri.raw shouldEqual Languages.Azeri
        Bulgarian.raw shouldEqual Languages.Bulgarian
        Brunei.raw shouldEqual Languages.Brunei
        Catalan.raw shouldEqual Languages.Catalan
        Czech.raw shouldEqual Languages.Czech
        Welsh.raw shouldEqual Languages.Welsh
        Danish.raw shouldEqual Languages.Danish
        German.raw shouldEqual Languages.German
        English.raw shouldEqual Languages.English
        Esperanto.raw shouldEqual Languages.Esperanto
        Spanish.raw shouldEqual Languages.Spanish
        Estonian.raw shouldEqual Languages.Estonian
        Basque.raw shouldEqual Languages.Basque
        Finnish.raw shouldEqual Languages.Finnish
        Faroese.raw shouldEqual Languages.Faroese
        French.raw shouldEqual Languages.French
        Galician.raw shouldEqual Languages.Galician
        Hebrew.raw shouldEqual Languages.Hebrew
        Hindi.raw shouldEqual Languages.Hindi
        Hungarian.raw shouldEqual Languages.Hungarian
        Armenian.raw shouldEqual Languages.Armenian
        Indonesian.raw shouldEqual Languages.Indonesian
        Icelandic.raw shouldEqual Languages.Icelandic
        Italian.raw shouldEqual Languages.Italian
        Japanese.raw shouldEqual Languages.Japanese
        Georgian.raw shouldEqual Languages.Georgian
        Kazakh.raw shouldEqual Languages.Kazakh
        Korean.raw shouldEqual Languages.Korean
        Kyrgyz.raw shouldEqual Languages.Kyrgyz
        Lithuanian.raw shouldEqual Languages.Lithuanian
        Maori.raw shouldEqual Languages.Maori
        Mongolian.raw shouldEqual Languages.Mongolian
        Marathi.raw shouldEqual Languages.Marathi
        Malay.raw shouldEqual Languages.Malay
        Maltese.raw shouldEqual Languages.Maltese
        Norwegian.raw shouldEqual Languages.Norwegian
        Dutch.raw shouldEqual Languages.Dutch
        NorthernSotho.raw shouldEqual Languages.NorthernSotho
        Polish.raw shouldEqual Languages.Polish
        Pashto.raw shouldEqual Languages.Pashto
        Portuguese.raw shouldEqual Languages.Portuguese
        Quechua.raw shouldEqual Languages.Quechua
        Romanian.raw shouldEqual Languages.Romanian
        Russian.raw shouldEqual Languages.Russian
        Slovak.raw shouldEqual Languages.Slovak
        Albanian.raw shouldEqual Languages.Albanian
        Swedish.raw shouldEqual Languages.Swedish
        Swahili.raw shouldEqual Languages.Swahili
        Tamil.raw shouldEqual Languages.Tamil
        Telugu.raw shouldEqual Languages.Telugu
        Tagalog.raw shouldEqual Languages.Tagalog
        Tswana.raw shouldEqual Languages.Tswana
        Turkish.raw shouldEqual Languages.Turkish
        Tatar.raw shouldEqual Languages.Tatar
    }
}
