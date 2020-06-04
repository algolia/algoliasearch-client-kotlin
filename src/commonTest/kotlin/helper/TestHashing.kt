package helper

import com.algolia.search.helper.decodeBase64
import com.algolia.search.helper.encodeBase64
import com.algolia.search.helper.sha256
import shouldEqual
import kotlin.test.Test

internal abstract class TestHashing {

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

        hash.decodeBase64() shouldEqual url
    }
}
