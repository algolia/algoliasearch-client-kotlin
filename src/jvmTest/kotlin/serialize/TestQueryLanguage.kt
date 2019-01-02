package serialize

import com.algolia.search.saas.data.QueryLanguage
import com.algolia.search.saas.data.QueryLanguage.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestQueryLanguage : TestSerializer<QueryLanguage>(QueryLanguage) {

    override val items = listOf(
        Afrikaans,
        Arabic,
        Azeri,
        Bulgarian,
        Brunei,
        Catalan,
        Czech,
        Welsh,
        Danish,
        German,
        English,
        Esperanto,
        Spanish,
        Estonian,
        Basque,
        Finnish,
        Faroese,
        French,
        Galician,
        Hebrew,
        Hindi,
        Hungarian,
        Armenian,
        Indonesian,
        Icelandic,
        Italian,
        Japanese,
        Georgian,
        Kazakh,
        Korean,
        Kyrgyz,
        Lithuanian,
        Maori,
        Mongolian,
        Marathi,
        Malay,
        Maltese,
        Norwegian,
        Dutch,
        NorthernSotho,
        Polish,
        Pashto,
        Portuguese,
        Quechua,
        Romanian,
        Russian,
        Slovak,
        Albanian,
        Swedish,
        Swahili,
        Tamil,
        Telugu,
        Tagalog,
        Tswana,
        Turkish,
        Tatar
    )
}