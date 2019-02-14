package serialize

import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.*
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.list
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestSynonym : TestSerializer<Synonym>(Synonym) {

    private val objectID = ObjectID("objectID")
    private val json = json { KeyObjectID to objectID.raw }
    private val strings = listOf("iPhone", "samsung")
    private val array = Json.plain.toJson(StringSerializer.list, strings)

    override val items = listOf(
        Synonym.OneWay(objectID, unknown, strings) to json {
            KeyObjectID to objectID.raw
            KeyType to KeyOneWaySynonym
            KeyInput to unknown
            KeySynonyms to array
        },
        Synonym.MultiWay(objectID, strings) to json {
            KeyObjectID to objectID.raw
            KeyType to KeySynonym
            KeySynonyms to array
        },
        Synonym.AlternativeCorrections(objectID, unknown, strings, SynonymType.Typo.One) to json {
            KeyObjectID to objectID.raw
            KeyType to KeyAlternativeCorrection1
            KeyWord to unknown
            KeyCorrections to array
        },
        Synonym.AlternativeCorrections(objectID, unknown, strings, SynonymType.Typo.Two) to json {
            KeyObjectID to objectID.raw
            KeyType to KeyAlternativeCorrection2
            KeyWord to unknown
            KeyCorrections to array
        },
        Synonym.Placeholder(objectID, Synonym.Placeholder.Token(unknown), strings) to kotlinx.serialization.json.json {
            KeyObjectID to objectID.raw
            KeyType to KeyPlaceholder
            KeyPlaceholder to unknown
            KeyReplacements to array
        },
        Synonym.Other(json, objectID) to json
    )
}