package client

import com.algolia.search.serialize.internal.urlEncode
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import shouldEqual
import kotlin.test.Test

internal class TestHelper {

    @Test
    fun urlEncode() {
        val json = buildJsonObject {
            put("Hello", "A")
            put(
                "Key",
                buildJsonArray {
                    add("A")
                    add("B")
                    add(buildJsonArray { add("C") })
                }
            )
        }.urlEncode()

        json shouldEqual "Hello=A&Key=%5B%22A%22%2C%22B%22%2C%5B%22C%22%5D%5D"
    }
}
