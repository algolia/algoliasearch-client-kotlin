package host
import com.algolia.search.requestOptions
import com.algolia.search.serialize.KeyAlgoliaUserID
import com.algolia.search.serialize.KeyForwardedFor
import com.algolia.search.toUserID
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
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
        requestOptions.headers shouldEqual mutableMapOf(KeyAlgoliaUserID to "value")
    }

    @Test
    fun forwarded() {
        val requestOptions = requestOptions {
            headerForwardedFor("value")
        }

        requestOptions.headers shouldEqual mutableMapOf(KeyForwardedFor to "value")
    }
}