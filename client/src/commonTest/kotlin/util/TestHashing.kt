package util

import com.algolia.search.helper.internal.sha256
import io.ktor.util.decodeBase64String
import io.ktor.util.encodeBase64
import shouldEqual
import kotlin.test.Test

internal class TestHashing {

    @Test
    fun sha256() {
        "1234".sha256("test") shouldEqual "24c4f0295e1bea74f9a5cb5bc40525c8889d11c78c4255808be00defe666671f"
    }

    @Test
    fun encoding() {
        val url = "AZ:/19&@';#"
        val hash = "QVo6LzE5JkAnOyM="
        url.encodeBase64() shouldEqual hash
    }

    @Test
    fun decoding() {
        val url = "AZ:/19&@';#"
        val hash = "QVo6LzE5JkAnOyM="

        hash.decodeBase64String() shouldEqual url
    }
}
