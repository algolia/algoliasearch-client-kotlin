package client

import com.algolia.search.serialize.urlEncode
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

        json shouldEqual "Hello=A&Key=%5B%22A%22%2C%22B%22%2C%5B%22C%22%5D%5D"
    }
}
