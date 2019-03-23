package client

import com.algolia.search.serialize.urlEncode
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import shouldEqual
import kotlin.test.Test


internal class TestHelper {

    @Test
    fun urlEncode() {
        val json = json {
            "Hello" to "A"
            "Key" to jsonArray {
                +"A"
                +"B"
                +jsonArray { +"C" }
            }
        }.urlEncode()

        json shouldEqual "Hello=A&Key=%5BA%2CB%2C%5BC%5D%5D"
    }
}