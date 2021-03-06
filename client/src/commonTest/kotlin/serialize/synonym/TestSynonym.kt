package serialize.synonym

import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
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
import com.algolia.search.serialize.internal.Json
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import unknown

internal class TestSynonym : TestSerializer<Synonym>(Synonym) {

    private val objectID = ObjectID("objectID")
    private val json by lazy { buildJsonObject { put(KeyObjectID, objectID.raw) } }
    private val strings = listOf("iPhone", "samsung")
    private val array = Json.encodeToJsonElement(ListSerializer(String.serializer()), strings)

    override val items = listOf(
        Synonym.OneWay(objectID, unknown, strings) to buildJsonObject {
            put(KeyObjectID, objectID.raw)
            put(KeyType, KeyOneWaySynonym)
            put(KeyInput, unknown)
            put(KeySynonyms, array)
        },
        Synonym.MultiWay(objectID, strings) to buildJsonObject {
            put(KeyObjectID, objectID.raw)
            put(KeyType, KeySynonym)
            put(KeySynonyms, array)
        },
        Synonym.AlternativeCorrections(objectID, unknown, strings, SynonymType.Typo.One) to buildJsonObject {
            put(KeyObjectID, objectID.raw)
            put(KeyType, KeyAlternativeCorrection1)
            put(KeyWord, unknown)
            put(KeyCorrections, array)
        },
        Synonym.AlternativeCorrections(objectID, unknown, strings, SynonymType.Typo.Two) to buildJsonObject {
            put(KeyObjectID, objectID.raw)
            put(KeyType, KeyAlternativeCorrection2)
            put(KeyWord, unknown)
            put(KeyCorrections, array)
        },
        Synonym.Placeholder(objectID, Synonym.Placeholder.Token(unknown), strings) to buildJsonObject {
            put(KeyObjectID, objectID.raw)
            put(KeyType, KeyPlaceholder)
            put(KeyPlaceholder, "<$unknown>")
            put(KeyReplacements, array)
        },
        Synonym.Other(objectID, json) to json
    )
}
