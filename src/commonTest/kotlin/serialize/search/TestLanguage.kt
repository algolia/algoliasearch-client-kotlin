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
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer

internal class TestLanguage : TestSerializer<Language>(Language) {

    override val items = listOf(
        Afrikaans to JsonLiteral(Afrikaans.raw),
        Arabic to JsonLiteral(Arabic.raw),
        Azeri to JsonLiteral(Azeri.raw),
        Bulgarian to JsonLiteral(Bulgarian.raw),
        Brunei to JsonLiteral(Brunei.raw),
        Catalan to JsonLiteral(Catalan.raw),
        Czech to JsonLiteral(Czech.raw),
        Welsh to JsonLiteral(Welsh.raw),
        Danish to JsonLiteral(Danish.raw),
        German to JsonLiteral(German.raw),
        English to JsonLiteral(English.raw),
        Esperanto to JsonLiteral(Esperanto.raw),
        Spanish to JsonLiteral(Spanish.raw),
        Estonian to JsonLiteral(Estonian.raw),
        Basque to JsonLiteral(Basque.raw),
        Finnish to JsonLiteral(Finnish.raw),
        Faroese to JsonLiteral(Faroese.raw),
        French to JsonLiteral(French.raw),
        Galician to JsonLiteral(Galician.raw),
        Hebrew to JsonLiteral(Hebrew.raw),
        Hindi to JsonLiteral(Hindi.raw),
        Hungarian to JsonLiteral(Hungarian.raw),
        Armenian to JsonLiteral(Armenian.raw),
        Indonesian to JsonLiteral(Indonesian.raw),
        Icelandic to JsonLiteral(Icelandic.raw),
        Italian to JsonLiteral(Italian.raw),
        Japanese to JsonLiteral(Japanese.raw),
        Georgian to JsonLiteral(Georgian.raw),
        Kazakh to JsonLiteral(Kazakh.raw),
        Korean to JsonLiteral(Korean.raw),
        Kyrgyz to JsonLiteral(Kyrgyz.raw),
        Lithuanian to JsonLiteral(Lithuanian.raw),
        Maori to JsonLiteral(Maori.raw),
        Mongolian to JsonLiteral(Mongolian.raw),
        Marathi to JsonLiteral(Marathi.raw),
        Malay to JsonLiteral(Malay.raw),
        Maltese to JsonLiteral(Maltese.raw),
        Norwegian to JsonLiteral(Norwegian.raw),
        Dutch to JsonLiteral(Dutch.raw),
        NorthernSotho to JsonLiteral(NorthernSotho.raw),
        Polish to JsonLiteral(Polish.raw),
        Pashto to JsonLiteral(Pashto.raw),
        Portuguese to JsonLiteral(Portuguese.raw),
        Quechua to JsonLiteral(Quechua.raw),
        Romanian to JsonLiteral(Romanian.raw),
        Russian to JsonLiteral(Russian.raw),
        Slovak to JsonLiteral(Slovak.raw),
        Albanian to JsonLiteral(Albanian.raw),
        Swedish to JsonLiteral(Swedish.raw),
        Swahili to JsonLiteral(Swahili.raw),
        Tamil to JsonLiteral(Tamil.raw),
        Telugu to JsonLiteral(Telugu.raw),
        Tagalog to JsonLiteral(Tagalog.raw),
        Tswana to JsonLiteral(Tswana.raw),
        Turkish to JsonLiteral(Turkish.raw),
        Tatar to JsonLiteral(Tatar.raw)
    )
}
