package model.response

import attributeA
import attributeB
import com.algolia.search.model.response.ResponseSearch
import kotlinx.serialization.Serializable
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.json
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
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

        hit.get(StringSerializer, attributeA) shouldEqual "valueA"
        hit.get(StringSerializer, attributeB) shouldEqual "valueB"
        hit.parse(Sample.serializer()) shouldEqual Sample("valueA", "valueB")
    }
}