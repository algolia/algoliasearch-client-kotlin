package serialize

import com.algolia.search.saas.data.Synonym
import com.algolia.search.saas.serialize.*
import com.algolia.search.saas.toObjectID
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.list
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestSynonym : TestSerializer<Synonym>(Synonym) {

    private val json = json {}
    private val objectID = "objectID".toObjectID()
    private val words = listOf("iPhone", "samsung")
    private val input = "query"

    override val items = listOf(
        Synonym.OneWay(objectID, input, words) to json {
            KeyType to KeyOneWaySynonym
            KeyObjectId to objectID.raw
            KeyInput to input
            KeySynonyms to Json.plain.toJson(words, StringSerializer.list)
        },
        Synonym.MultiWay(objectID, words) to json {
            KeyType to KeySynonym
            KeyObjectId to objectID.raw
            KeySynonyms to Json.plain.toJson(words, StringSerializer.list)
        },
        Synonym.Other(json) to json
    )
}