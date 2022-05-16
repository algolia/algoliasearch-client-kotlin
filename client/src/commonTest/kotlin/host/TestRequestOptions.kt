package host

import com.algolia.search.dsl.requestOptions
import com.algolia.search.helper.toUserID
import com.algolia.search.serialize.internal.Key
import shouldEqual
import kotlin.test.Test

internal class TestRequestOptions {

    @Test
    fun headers() {
        val requestOptions = requestOptions {
            headers["keyA"] = "valueA"
            headers["keyB"] = "valueB"
        }

        requestOptions.headers shouldEqual mutableMapOf("keyA" to "valueA", "keyB" to "valueB")
    }

    @Test
    fun parameters() {
        val requestOptions = requestOptions {
            urlParameters["keyA"] = "valueA"
            urlParameters["keyB"] = "valueB"
        }

        requestOptions.urlParameters shouldEqual mutableMapOf("keyA" to "valueA", "keyB" to "valueB")
    }

    @Test
    fun userId() {
        val requestOptions = requestOptions {
            headerAlgoliaUserId("value".toUserID())
        }
        requestOptions.headers shouldEqual mutableMapOf(Key.AlgoliaUserID to "value")
    }

    @Test
    fun forwarded() {
        val requestOptions = requestOptions {
            headerForwardedFor("value")
        }

        requestOptions.headers shouldEqual mutableMapOf(Key.ForwardedFor to "value")
    }
}
