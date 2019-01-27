package data.search

import attributeA
import attributeB
import com.algolia.search.model.search.SearchResponse
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.json
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSearchResponseHit {

    @Test
    fun dx() {
        val json = json {
            attributeA.raw to "valueA"
            attributeB.raw to "valueB"
        }
        val hit = SearchResponse.Search.Hit(json)

        hit.get(StringSerializer, attributeA) shouldEqual "valueA"
        hit.get(StringSerializer, attributeB) shouldEqual "valueB"
    }
}