package configuration

import com.algolia.search.configuration.internal.extension.ApiKeyPlugin
import kotlin.test.Test
import kotlin.test.assertEquals

class TestApiKey {

    @Test
    fun patchStringJson() {
        val body = """{"query":"a"}"""
        val longApiKey = "A".repeat(500)
        val patchedBody = ApiKeyPlugin.patchRequestBody(body, longApiKey)
        assertEquals("""{"query":"a","apiKey":"$longApiKey"}""", patchedBody.text)
    }

    @Test
    fun patchStringEmptyJson() {
        val body = "{}"
        val longApiKey = "A".repeat(500)
        val patchedBody = ApiKeyPlugin.patchRequestBody(body, longApiKey)
        assertEquals("""{"apiKey":"$longApiKey"}""", patchedBody.text)
    }

    @Test
    fun patchStringEmpty() {
        val body = ""
        val longApiKey = "A".repeat(500)
        val patchedBody = ApiKeyPlugin.patchRequestBody(body, longApiKey)
        assertEquals("""{"apiKey":"$longApiKey"}""", patchedBody.text)
    }
}