package serialize

import com.algolia.search.saas.data.Synonym
import com.algolia.search.saas.serialize.*
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.list
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestSynonym : TestSerializer<Synonym>(Synonym) {

    private val json = json {}
    private val strings = listOf("iPhone", "samsung")
    private val array = Json.plain.toJson(strings, StringSerializer.list)

    override val items = listOf(
        Synonym.OneWay(unknown, strings) to json {
            KeyType to KeyOneWaySynonym
            KeyInput to unknown
            KeySynonyms to array
        },
        Synonym.MultiWay(strings) to json {
            KeyType to KeySynonym
            KeySynonyms to array
        },
        Synonym.AlternativeCorrections(unknown, strings, Synonym.Typo.One) to json {
            KeyType to KeyAlternativeCorrection1
            KeyWord to unknown
            KeyCorrections to array
        },
        Synonym.AlternativeCorrections(unknown, strings, Synonym.Typo.Two) to json {
            KeyType to KeyAlternativeCorrection2
            KeyWord to unknown
            KeyCorrections to array
        },
        Synonym.Placeholder(unknown, strings) to kotlinx.serialization.json.json {
            KeyType to KeyPlaceholder
            KeyPlaceholder to unknown
            KeyReplacements to array
        },
        Synonym.Other(json) to json
    )
}