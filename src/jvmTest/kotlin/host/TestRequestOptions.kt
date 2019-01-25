package host
import com.algolia.search.saas.client.requestOptions
import com.algolia.search.saas.toUserID
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestRequestOptions {

    @Test
    fun headers() {
        val requestOptions = requestOptions {
            header("keyA", "valueA")
            header("keyB", "valueB")
        }

        requestOptions.headers shouldEqual mutableMapOf("keyA" to "valueA", "keyB" to "valueB")
    }

    @Test
    fun parameters() {
        val requestOptions = requestOptions {
            urlParameter("keyA", "valueA")
            urlParameter("keyB", "valueB")
        }

        requestOptions.urlParameters shouldEqual mutableMapOf("keyA" to "valueA", "keyB" to "valueB")
    }

    @Test
    fun userId() {
        val requestOptions = requestOptions {
            headerAlgoliaUserId("value".toUserID())
        }
        requestOptions.headers shouldEqual mutableMapOf("X-Algolia-UserID" to "value")
    }

    @Test
    fun forwarded() {
        val requestOptions = requestOptions {
            headerForwardedFor("value")
        }

        requestOptions.headers shouldEqual mutableMapOf("X-Forwarded-For" to "value")
    }
}