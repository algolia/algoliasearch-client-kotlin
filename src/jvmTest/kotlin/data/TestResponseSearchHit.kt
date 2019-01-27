package data

import attributeA
import attributeB
import com.algolia.search.response.ResponseSearch
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.json.json
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestResponseSearchHit {

    @Test
    fun dx() {
        val json = json {
            attributeA.raw to "valueA"
            attributeB.raw to "valueB"
        }
        val hit = ResponseSearch.Hit(json)

        hit.get(StringSerializer, attributeA) shouldEqual "valueA"
        hit.get(StringSerializer, attributeB) shouldEqual "valueB"
    }
}