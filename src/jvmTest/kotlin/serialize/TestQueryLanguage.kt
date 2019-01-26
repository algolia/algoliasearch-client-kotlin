package serialize

import com.algolia.search.saas.model.enums.QueryLanguage
import com.algolia.search.saas.model.enums.QueryLanguage.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestQueryLanguage : TestSerializer<QueryLanguage>(QueryLanguage) {

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