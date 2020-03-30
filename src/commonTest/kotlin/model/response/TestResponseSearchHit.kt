package model.response

import attributeA
import attributeB
import com.algolia.search.model.response.ResponseSearch
import kotlin.test.Test
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.json
import shouldEqual

internal class TestResponseSearchHit {

    @Serializable
    data class Sample(
        val attributeA: String,
        val attributeB: String
    )

    @Test
    fun dx() {
        val json = json {
            attributeA.raw to "valueA"
            attributeB.raw to "valueB"
        }
        val hit = ResponseSearch.Hit(json)

        hit.getValue(StringSerializer, attributeA) shouldEqual "valueA"
        hit.getValue(StringSerializer, attributeB) shouldEqual "valueB"
        hit.deserialize(Sample.serializer()) shouldEqual Sample("valueA", "valueB")
    }
}
