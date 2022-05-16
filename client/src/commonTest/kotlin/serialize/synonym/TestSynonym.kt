package serialize.synonym

import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.internal.KeyAlternativeCorrection1
import com.algolia.search.serialize.internal.KeyAlternativeCorrection2
import com.algolia.search.serialize.internal.KeyCorrections
import com.algolia.search.serialize.internal.KeyInput
import com.algolia.search.serialize.internal.KeyObjectID
import com.algolia.search.serialize.internal.KeyOneWaySynonym
import com.algolia.search.serialize.internal.KeyPlaceholder
import com.algolia.search.serialize.internal.KeyReplacements
import com.algolia.search.serialize.internal.KeySynonym
import com.algolia.search.serialize.internal.KeySynonyms
import com.algolia.search.serialize.internal.KeyType
import com.algolia.search.serialize.internal.KeyWord
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
