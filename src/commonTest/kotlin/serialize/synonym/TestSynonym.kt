package serialize.synonym

import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.KeyAlternativeCorrection1
import com.algolia.search.serialize.KeyAlternativeCorrection2
import com.algolia.search.serialize.KeyCorrections
import com.algolia.search.serialize.KeyInput
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyOneWaySynonym
import com.algolia.search.serialize.KeyPlaceholder
import com.algolia.search.serialize.KeyReplacements
import com.algolia.search.serialize.KeySynonym
import com.algolia.search.serialize.KeySynonyms
import com.algolia.search.serialize.KeyType
import com.algolia.search.serialize.KeyWord
import kotlinx.serialization.builtins.list
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.json
import serialize.TestSerializer
import unknown

internal class TestSynonym : TestSerializer<Synonym>(Synonym) {

    private val objectID = ObjectID("objectID")
    private val json = json { KeyObjectID to objectID.raw }
    private val strings = listOf("iPhone", "samsung")
    private val array = Json.toJson(String.serializer().list, strings)

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
        Synonym.Placeholder(objectID, Synonym.Placeholder.Token(unknown), strings) to json {
            KeyObjectID to objectID.raw
            KeyType to KeyPlaceholder
            KeyPlaceholder to "<$unknown>"
            KeyReplacements to array
        },
        Synonym.Other(objectID, json) to json
    )
}
