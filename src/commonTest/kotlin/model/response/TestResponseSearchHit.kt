package model.response

import attributeA
import attributeB
import com.algolia.search.model.response.ResponseSearch
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import shouldEqual
import kotlin.test.Test

internal class TestResponseSearchHit {

    @Serializable
    data class Sample(
        val attributeA: String,
        val attributeB: String
    )

    @Test
    fun dx() {
        val json = buildJsonObject {
            put(attributeA.raw, "valueA")
            put(attributeB.raw, "valueB")
        }
        val hit = ResponseSearch.Hit(json)

        hit.getValue(String.serializer(), attributeA) shouldEqual "valueA"
        hit.getValue(String.serializer(), attributeB) shouldEqual "valueB"
        hit.deserialize(Sample.serializer()) shouldEqual Sample("valueA", "valueB")
    }
}
