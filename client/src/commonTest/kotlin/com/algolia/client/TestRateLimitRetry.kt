package com.algolia.client

import com.algolia.client.api.ApiClient
import com.algolia.client.api.SearchClient
import com.algolia.client.configuration.ClientOptions
import com.algolia.client.configuration.Host
import com.algolia.client.configuration.TransformationOptions
import com.algolia.client.exception.AlgoliaApiException
import com.algolia.client.exception.AlgoliaClientException
import com.algolia.client.exception.AlgoliaRetryException
import com.algolia.client.transport.internal.DEFAULT_RATE_LIMIT_WAIT
import com.algolia.client.transport.internal.HEADER_CORRELATION_ID
import com.algolia.client.transport.internal.KtorRequester
import com.algolia.client.transport.internal.retryAfterWait
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.test.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds
import kotlin.time.TimeSource
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class TestRateLimitRetry {

  private val hosts = listOf(Host("first.host"), Host("second.host"))

  private fun optionsOf(engine: MockEngine, maxRateLimitRetries: Int? = null): ClientOptions =
    if (maxRateLimitRetries == null) ClientOptions(engine = engine, hosts = hosts)
    else ClientOptions(engine = engine, hosts = hosts, maxRateLimitRetries = maxRateLimitRetries)

  private fun clientOf(engine: MockEngine, maxRateLimitRetries: Int? = null): SearchClient =
    SearchClient(
      appId = "appId",
      apiKey = "apiKey",
      options = optionsOf(engine, maxRateLimitRetries),
    )

  private fun ApiClient.recordedWaits(): List<Duration> {
    val waits = mutableListOf<Duration>()
    (requester as KtorRequester).rateLimitWait = { waits += it }
    return waits
  }

  private fun MockRequestHandleScope.rateLimited(
    retryAfter: String? = null,
    contentType: String = "application/json",
    body: String = """{"message":"Too many requests"}""",
    correlationId: String? = null,
  ) =
    respond(
      content = body,
      status = HttpStatusCode.TooManyRequests,
      headers =
        headersOf(
          HttpHeaders.ContentType to listOf(contentType),
          HttpHeaders.RetryAfter to listOfNotNull(retryAfter),
          HEADER_CORRELATION_ID to listOfNotNull(correlationId),
        ),
    )

  private fun MockRequestHandleScope.ok() =
    respond(
      content = """{"message":"ok"}""",
      status = HttpStatusCode.OK,
      headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )

  private fun MockRequestHandleScope.serverError() =
    respond(
      content = """{"message":"Internal Server Error"}""",
      status = HttpStatusCode.InternalServerError,
      headers = headersOf(HttpHeaders.ContentType, "application/json"),
    )

  private fun retryAfterOf(value: String?): Headers =
    if (value == null) Headers.Empty else headersOf(HttpHeaders.RetryAfter, value)

  @Test
  fun retryAfterWaitHonorsPositiveWholeSeconds() {
    assertEquals(2.seconds, retryAfterWait(retryAfterOf("2")))
    assertEquals(86400.seconds, retryAfterWait(retryAfterOf("86400")))
    assertEquals(5.seconds, retryAfterWait(retryAfterOf(" 5 ")))
  }

  @Test
  fun retryAfterWaitFallsBackToOneSecond() {
    assertEquals(1.seconds, DEFAULT_RATE_LIMIT_WAIT)
    for (value in
      listOf(null, "", " ", "0", "-5", "1.5", "120abc", "Wed, 21 Oct 2015 07:28:00 GMT")) {
      assertEquals(
        DEFAULT_RATE_LIMIT_WAIT,
        retryAfterWait(retryAfterOf(value)),
        "Retry-After: $value",
      )
    }
  }

  @Test
  fun retryAfterWaitSaturatesWhenTooLargeToRepresent() {
    assertEquals(Duration.INFINITE, retryAfterWait(retryAfterOf("99999999999999999999")))
    assertEquals(Duration.INFINITE, retryAfterWait(retryAfterOf(Long.MAX_VALUE.toString())))
  }

  @Test
  fun rateLimitWaitIsNotFastForwardedByRunTest() = runTest {
    clientOf(MockEngine { ok() }).use { client ->
      val start = TimeSource.Monotonic.markNow()
      (client.requester as KtorRequester).rateLimitWait(200.milliseconds)
      val elapsed = start.elapsedNow()

      assertTrue(elapsed >= 200.milliseconds, "waited $elapsed, expected at least 200ms")
    }
  }

  @Test
  fun waitsThenRetriesTheSameHost() = runTest {
    var calls = 0
    val engine = MockEngine { if (++calls == 1) rateLimited(retryAfter = "2") else ok() }
    clientOf(engine).use { client ->
      val waits = client.recordedWaits()
      val response = client.customGet(path = "1/test")

      assertEquals(buildJsonObject { put("message", "ok") }, response)
      assertEquals(listOf(2.seconds), waits)
      assertEquals(2, engine.requestHistory.size)
      assertEquals(listOf("first.host", "first.host"), engine.requestHistory.map { it.url.host })

      client.customGet(path = "1/test")
      assertEquals("first.host", engine.requestHistory.last().url.host)
    }
  }

  @Test
  fun surfacesTheRateLimitOnceTheBudgetIsSpent() = runTest {
    val engine = MockEngine { rateLimited(retryAfter = "1") }
    clientOf(engine).use { client ->
      val waits = client.recordedWaits()
      val exception = assertFailsWith<AlgoliaApiException> { client.customGet(path = "1/test") }

      assertEquals(429, exception.httpErrorCode)
      assertEquals(listOf(1.seconds, 1.seconds, 1.seconds), waits)
      assertEquals(4, engine.requestHistory.size)
      assertTrue(engine.requestHistory.all { it.url.host == "first.host" })
    }
  }

  @Test
  fun sharesTheBudgetAcrossHosts() = runTest {
    val statuses = ArrayDeque(listOf(429, 500, 429, 429, 429))
    val engine = MockEngine {
      if (statuses.removeFirst() == 500) serverError() else rateLimited(retryAfter = "1")
    }
    clientOf(engine).use { client ->
      val waits = client.recordedWaits()
      val exception = assertFailsWith<AlgoliaApiException> { client.customGet(path = "1/test") }

      assertEquals(429, exception.httpErrorCode)
      assertEquals(listOf(1.seconds, 1.seconds, 1.seconds), waits)
      assertEquals(
        listOf("first.host", "first.host", "second.host", "second.host", "second.host"),
        engine.requestHistory.map { it.url.host },
      )
    }
  }

  @Test
  fun keepsWaitedOutRateLimitsAmongTheRetryErrors() = runTest {
    val statuses = ArrayDeque(listOf(429, 500, 500))
    val engine = MockEngine {
      if (statuses.removeFirst() == 500) serverError()
      else rateLimited(retryAfter = "1", correlationId = "rate-limited-call")
    }
    clientOf(engine).use { client ->
      val waits = client.recordedWaits()
      val exception = assertFailsWith<AlgoliaRetryException> { client.customGet(path = "1/test") }

      assertEquals(listOf(1.seconds), waits)
      assertEquals(
        listOf("first.host", "first.host", "second.host"),
        engine.requestHistory.map { it.url.host },
      )
      assertEquals(3, exception.exceptions.size)
      val rateLimited = assertIs<AlgoliaApiException>(exception.exceptions.first())
      assertEquals(429, rateLimited.httpErrorCode)
      assertEquals("rate-limited-call", rateLimited.correlationId)
      assertEquals("rate-limited-call", exception.correlationId)
      assertTrue(exception.exceptions.drop(1).all { it is AlgoliaClientException })
    }
  }

  @Test
  fun surfacesAnHtmlRateLimitOnceTheBudgetIsSpent() = runTest {
    val html = "<html><body>429 Too Many Requests</body></html>"
    val engine = MockEngine { rateLimited(contentType = "text/html", body = html) }
    clientOf(engine, maxRateLimitRetries = 1).use { client ->
      val waits = client.recordedWaits()
      val exception = assertFailsWith<AlgoliaApiException> { client.customGet(path = "1/test") }

      assertEquals(429, exception.httpErrorCode)
      assertTrue(exception.message!!.contains(html), exception.message!!)
      assertEquals(listOf(DEFAULT_RATE_LIMIT_WAIT), waits)
      assertEquals(2, engine.requestHistory.size)
    }
  }

  @Test
  fun failsOnTheFirstRateLimitWhenRetriesAreDisabled() = runTest {
    val engine = MockEngine { rateLimited(retryAfter = "30") }
    clientOf(engine, maxRateLimitRetries = 0).use { client ->
      val waits = client.recordedWaits()
      val exception = assertFailsWith<AlgoliaApiException> { client.customGet(path = "1/test") }

      assertEquals(429, exception.httpErrorCode)
      assertEquals(1, engine.requestHistory.size)
      assertTrue(waits.isEmpty(), "waited $waits, expected no wait")
    }
  }

  @Test
  fun defaultsToThreeRetries() {
    assertEquals(3, ClientOptions().maxRateLimitRetries)
    assertNull(TransformationOptions("us").clientOptions)
  }

  @Test
  fun transformationOptionsCarryTheBudgetToTheIngestionTransporter() = runTest {
    val engine = MockEngine { rateLimited(retryAfter = "30") }
    val transformationOptions =
      TransformationOptions(
        region = "us",
        clientOptions = optionsOf(engine, maxRateLimitRetries = 0),
      )
    SearchClient.withTransformation("appId", "apiKey", transformationOptions).use { client ->
      val ingestion = assertNotNull(client.ingestionTransporter)
      val waits = ingestion.recordedWaits()
      val exception = assertFailsWith<AlgoliaApiException> { ingestion.customGet(path = "1/test") }

      assertEquals(429, exception.httpErrorCode)
      assertEquals(1, engine.requestHistory.size)
      assertTrue(waits.isEmpty(), "waited $waits, expected no wait")
    }
  }

  @Test
  fun ingestionTransporterKeepsTheDefaultBudget() = runTest {
    val engine = MockEngine { rateLimited(retryAfter = "1") }
    val transformationOptions =
      TransformationOptions(region = "us", clientOptions = optionsOf(engine))
    SearchClient.withTransformation("appId", "apiKey", transformationOptions).use { client ->
      val ingestion = assertNotNull(client.ingestionTransporter)
      val waits = ingestion.recordedWaits()
      val exception = assertFailsWith<AlgoliaApiException> { ingestion.customGet(path = "1/test") }

      assertEquals(429, exception.httpErrorCode)
      assertEquals(listOf(1.seconds, 1.seconds, 1.seconds), waits)
      assertEquals(4, engine.requestHistory.size)
    }
  }
}
