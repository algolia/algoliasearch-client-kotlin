package serialize.search

import com.algolia.search.model.search.Language
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
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer

internal class TestLanguage : TestSerializer<Language>(Language) {

    override val items = listOf(
        Afrikaans to JsonPrimitive(Afrikaans.raw),
        Arabic to JsonPrimitive(Arabic.raw),
        Azeri to JsonPrimitive(Azeri.raw),
        Bulgarian to JsonPrimitive(Bulgarian.raw),
        Brunei to JsonPrimitive(Brunei.raw),
        Catalan to JsonPrimitive(Catalan.raw),
        Czech to JsonPrimitive(Czech.raw),
        Welsh to JsonPrimitive(Welsh.raw),
        Danish to JsonPrimitive(Danish.raw),
        German to JsonPrimitive(German.raw),
        English to JsonPrimitive(English.raw),
        Esperanto to JsonPrimitive(Esperanto.raw),
        Spanish to JsonPrimitive(Spanish.raw),
        Estonian to JsonPrimitive(Estonian.raw),
        Basque to JsonPrimitive(Basque.raw),
        Finnish to JsonPrimitive(Finnish.raw),
        Faroese to JsonPrimitive(Faroese.raw),
        French to JsonPrimitive(French.raw),
        Galician to JsonPrimitive(Galician.raw),
        Hebrew to JsonPrimitive(Hebrew.raw),
        Hindi to JsonPrimitive(Hindi.raw),
        Hungarian to JsonPrimitive(Hungarian.raw),
        Armenian to JsonPrimitive(Armenian.raw),
        Indonesian to JsonPrimitive(Indonesian.raw),
        Icelandic to JsonPrimitive(Icelandic.raw),
        Italian to JsonPrimitive(Italian.raw),
        Japanese to JsonPrimitive(Japanese.raw),
        Georgian to JsonPrimitive(Georgian.raw),
        Kazakh to JsonPrimitive(Kazakh.raw),
        Korean to JsonPrimitive(Korean.raw),
        Kyrgyz to JsonPrimitive(Kyrgyz.raw),
        Lithuanian to JsonPrimitive(Lithuanian.raw),
        Maori to JsonPrimitive(Maori.raw),
        Mongolian to JsonPrimitive(Mongolian.raw),
        Marathi to JsonPrimitive(Marathi.raw),
        Malay to JsonPrimitive(Malay.raw),
        Maltese to JsonPrimitive(Maltese.raw),
        Norwegian to JsonPrimitive(Norwegian.raw),
        Dutch to JsonPrimitive(Dutch.raw),
        NorthernSotho to JsonPrimitive(NorthernSotho.raw),
        Polish to JsonPrimitive(Polish.raw),
        Pashto to JsonPrimitive(Pashto.raw),
        Portuguese to JsonPrimitive(Portuguese.raw),
        Quechua to JsonPrimitive(Quechua.raw),
        Romanian to JsonPrimitive(Romanian.raw),
        Russian to JsonPrimitive(Russian.raw),
        Slovak to JsonPrimitive(Slovak.raw),
        Albanian to JsonPrimitive(Albanian.raw),
        Swedish to JsonPrimitive(Swedish.raw),
        Swahili to JsonPrimitive(Swahili.raw),
        Tamil to JsonPrimitive(Tamil.raw),
        Telugu to JsonPrimitive(Telugu.raw),
        Tagalog to JsonPrimitive(Tagalog.raw),
        Tswana to JsonPrimitive(Tswana.raw),
        Turkish to JsonPrimitive(Turkish.raw),
        Tatar to JsonPrimitive(Tatar.raw)
    )
}
