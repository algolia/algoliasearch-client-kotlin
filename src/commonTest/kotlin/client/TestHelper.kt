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
            "Key" to jsonArray {
                +"A"
                +"B"
            }
        }.urlEncode()

        json shouldEqual "Key=%5B%22A%22%2C%22B%22%5D"
    }
}