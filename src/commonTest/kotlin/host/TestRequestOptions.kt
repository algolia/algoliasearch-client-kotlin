package host

import com.algolia.search.helper.requestOptionsBuilder
import com.algolia.search.helper.toUserID
import com.algolia.search.serialize.KeyAlgoliaUserID
import com.algolia.search.serialize.KeyForwardedFor
import shouldEqual
import kotlin.test.Test


internal class TestRequestOptions {

    @Test
    fun headers() {
        val requestOptions = requestOptionsBuilder {
            headers["keyA"] = "valueA"
            headers["keyB"] = "valueB"
        }

        requestOptions.headers shouldEqual mutableMapOf("keyA" to "valueA", "keyB" to "valueB")
    }

    @Test
    fun parameters() {
        val requestOptions = requestOptionsBuilder {
            urlParameters["keyA"] = "valueA"
            urlParameters["keyB"] = "valueB"
        }

        requestOptions.urlParameters shouldEqual mutableMapOf("keyA" to "valueA", "keyB" to "valueB")
    }

    @Test
    fun userId() {
        val requestOptions = requestOptionsBuilder {
            headerAlgoliaUserId("value".toUserID())
        }
        requestOptions.headers shouldEqual mutableMapOf(KeyAlgoliaUserID to "value")
    }

    @Test
    fun forwarded() {
        val requestOptions = requestOptionsBuilder {
            headerForwardedFor("value")
        }

        requestOptions.headers shouldEqual mutableMapOf(KeyForwardedFor to "value")
    }
}