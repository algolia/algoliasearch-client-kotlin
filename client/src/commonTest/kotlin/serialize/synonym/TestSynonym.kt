package serialize.synonym

import com.algolia.search.model.ObjectID
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.serialize.internal.Json
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer
import unknown

internal class TestSynonym : TestSerializer<Synonym>(Synonym) {

    private val objectID = ObjectID("objectID")
    private val json by lazy { buildJsonObject { put(Key.ObjectID, objectID.raw) } }
    private val strings = listOf("iPhone", "samsung")
    private val array = Json.encodeToJsonElement(ListSerializer(String.serializer()), strings)

    override val items = listOf(
        Synonym.OneWay(objectID, unknown, strings) to buildJsonObject {
            put(Key.ObjectID, objectID.raw)
            put(Key.Type, Key.OneWaySynonym)
            put(Key.Input, unknown)
            put(Key.Synonyms, array)
        },
        Synonym.MultiWay(objectID, strings) to buildJsonObject {
            put(Key.ObjectID, objectID.raw)
            put(Key.Type, Key.Synonym)
            put(Key.Synonyms, array)
        },
        Synonym.AlternativeCorrections(objectID, unknown, strings, SynonymType.Typo.One) to buildJsonObject {
            put(Key.ObjectID, objectID.raw)
            put(Key.Type, Key.AlternativeCorrection1)
            put(Key.Word, unknown)
            put(Key.Corrections, array)
        },
        Synonym.AlternativeCorrections(objectID, unknown, strings, SynonymType.Typo.Two) to buildJsonObject {
            put(Key.ObjectID, objectID.raw)
            put(Key.Type, Key.AlternativeCorrection2)
            put(Key.Word, unknown)
            put(Key.Corrections, array)
        },
        Synonym.Placeholder(objectID, Synonym.Placeholder.Token(unknown), strings) to buildJsonObject {
            put(Key.ObjectID, objectID.raw)
            put(Key.Type, Key.Placeholder)
            put(Key.Placeholder, "<$unknown>")
            put(Key.Replacements, array)
        },
        Synonym.Other(objectID, json) to json
    )
}
