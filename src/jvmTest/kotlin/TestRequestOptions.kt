import client.requestOptions
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestRequestOptions {

    @Test
    fun headers() {
        val requestOptions = requestOptions {
            header("keyA", "valueA")
            header("keyB", "valueB")
        }

        assertEquals(requestOptions.headers, mutableMapOf("keyA" to "valueA", "keyB" to "valueB"))
    }

    @Test
    fun parameters() {
        val requestOptions = requestOptions {
            urlParameter("keyA", "valueA")
            urlParameter("keyB", "valueB")
        }

        assertEquals(requestOptions.urlParameters, mutableMapOf("keyA" to "valueA", "keyB" to "valueB"))
    }

    @Test
    fun userId() {
        val requestOptions = requestOptions {
            headerAlgoliaUserId("value")
        }
        assertEquals(requestOptions.headers, mutableMapOf("X-Algolia-UserID" to "value"))
    }

    @Test
    fun forwarded() {
        val requestOptions = requestOptions {
            headerForwardedFor("value")
        }

        assertEquals(requestOptions.headers, mutableMapOf("X-Forwarded-For" to "value"))
    }
}