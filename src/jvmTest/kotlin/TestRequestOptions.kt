import client.RequestOptions
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestRequestOptions {


    @Test
    fun headers() {
        val requestOptions = RequestOptions()

        requestOptions.setHeader("keyA", "valueA")
        requestOptions.setHeader("keyB", "valueB")

        assertEquals(requestOptions.headers, mutableMapOf("keyA" to "valueA", "keyB" to "valueB"))
    }

    @Test
    fun parameters() {
        val requestOptions = RequestOptions()

        requestOptions.setUrlParameters("keyA", "valueA")
        requestOptions.setUrlParameters("keyB", "valueB")

        assertEquals(requestOptions.urlParameters, mutableMapOf("keyA" to "valueA", "keyB" to "valueB"))
    }

    @Test
    fun userId() {
        val requestOptions = RequestOptions()

        requestOptions.setHeaderAlgoliaUserId("value")
        assertEquals(requestOptions.headers, mutableMapOf("X-Algolia-UserID" to "value"))
    }

    @Test
    fun forwarded() {
        val requestOptions = RequestOptions()

        requestOptions.setHeaderForwardedFor("value")
        assertEquals(requestOptions.headers, mutableMapOf("X-Forwarded-For" to "value"))
    }
}