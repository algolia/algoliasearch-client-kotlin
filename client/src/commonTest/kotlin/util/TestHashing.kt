package util

import com.algolia.search.helper.internal.sha256
import com.algolia.search.util.internal.decodeBase64String
import com.algolia.search.util.internal.encodeBase64
import shouldEqual
import kotlin.test.Test

internal class TestHashing {

    @Test
    fun sha256() {
        "1234".sha256("test") shouldEqual "5471d39e681ffc00128c11b573f4a3356ceba766956bb928d562d2c7c0c2db6a"
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
