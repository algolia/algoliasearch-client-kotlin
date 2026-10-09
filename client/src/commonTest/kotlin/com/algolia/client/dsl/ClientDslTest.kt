@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.api.SearchClient
import com.algolia.client.configuration.ClientOptions
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.OutgoingContent
import io.ktor.http.headersOf
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json

internal class ClientDslTest {

  @Test
  fun setSettingsOverload() = runTest {
    val engine =
      mockEngine("""{"taskID":1,"updatedAt":"2024-01-01T00:00:00Z"}""") { request ->
        assertEquals("/1/indexes/idx/settings", request.url.encodedPath)
        assertEquals("true", request.url.parameters["forwardToReplicas"])
        assertEquals(
          Json.parseToJsonElement("""{"customRanking":["desc(popularity)"]}"""),
          Json.parseToJsonElement(request.bodyText()),
        )
      }
    SearchClient("appId", "apiKey", ClientOptions(engine = engine)).use { client ->
      client.setSettings("idx", forwardToReplicas = true) { customRanking { desc("popularity") } }
    }
  }

  private fun mockEngine(
    responseBody: String,
    assertRequest: (HttpRequestData) -> Unit,
  ): MockEngine = MockEngine { request ->
    assertRequest(request)
    respond(
      content = responseBody,
      status = HttpStatusCode.OK,
      headers = headersOf(HttpHeaders.ContentType, ContentType.Application.Json.toString()),
    )
  }

  private fun HttpRequestData.bodyText(): String {
    val bytes =
      (body as? OutgoingContent.ByteArrayContent)?.bytes()
        ?: error("Unexpected request body type: ${body::class}")
    return bytes.decodeToString()
  }
}
