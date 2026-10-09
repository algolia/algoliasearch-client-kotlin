package com.algolia.client

import com.algolia.client.api.SearchClient
import com.algolia.client.configuration.ClientOptions
import com.algolia.client.configuration.Host
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android
import io.ktor.client.engine.apache.Apache
import io.ktor.client.engine.apache5.Apache5
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.java.Java
import io.ktor.client.engine.jetty.Jetty
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.server.engine.EmbeddedServer
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty as JettyServer
import io.ktor.server.request.httpVersion
import io.ktor.server.response.header
import io.ktor.server.response.respondBytes
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import java.io.ByteArrayOutputStream
import java.util.concurrent.CopyOnWriteArrayList
import java.util.zip.GZIPOutputStream
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.jsonPrimitive

/**
 * Every JVM engine must advertise `Accept-Encoding: gzip` and decode gzipped responses, mirroring
 * the CTS "test the response decompression strategy" (which only runs with OkHttp).
 *
 * The Jetty server accepts both HTTP/1.1 and cleartext HTTP/2 (h2c) on the same port, so engines
 * that only speak HTTP/2 (Jetty) are covered alongside HTTP/1.1 ones.
 */
class TestResponseDecompression {

  private val responseBody = """{"message":"ok decompression test server response"}"""

  private val httpVersions = CopyOnWriteArrayList<String>()

  private lateinit var server: EmbeddedServer<*, *>

  private var port: Int = 0

  @BeforeTest
  fun startServer() {
    server =
      embeddedServer(JettyServer, host = "127.0.0.1", port = 0) {
          routing {
            get("/1/test/gzip-response") {
              httpVersions += call.request.httpVersion
              val acceptEncoding = call.request.headers[HttpHeaders.AcceptEncoding].orEmpty()
              if (!acceptEncoding.contains("gzip")) {
                call.respondText(
                  """{"message":"client did not send accept-encoding: gzip"}""",
                  ContentType.Application.Json,
                  HttpStatusCode.BadRequest,
                )
                return@get
              }
              val compressed =
                ByteArrayOutputStream().use { bos ->
                  GZIPOutputStream(bos).use { gzip -> gzip.write(responseBody.toByteArray()) }
                  bos.toByteArray()
                }
              call.response.header(HttpHeaders.ContentEncoding, "gzip")
              call.respondBytes(compressed, ContentType.Application.Json)
            }
            get("/1/test/identity-response") {
              call.response.header(HttpHeaders.ContentEncoding, "identity")
              call.respondText(responseBody, ContentType.Application.Json)
            }
          }
        }
        .start(wait = false)
    port = runBlocking { server.engine.resolvedConnectors().first().port }
  }

  @AfterTest
  fun stopServer() {
    server.stop(gracePeriodMillis = 0, timeoutMillis = 1000)
  }

  private fun assertMessage(
    engine: HttpClientEngine,
    path: String = "1/test/gzip-response",
    logLevel: LogLevel = LogLevel.NONE,
    logger: Logger = Logger.DEFAULT,
  ) = runBlocking {
    engine.use {
      val client =
        SearchClient(
          appId = "test-app-id",
          apiKey = "test-api-key",
          options =
            ClientOptions(
              engine = engine,
              logLevel = logLevel,
              logger = logger,
              hosts = listOf(Host(url = "127.0.0.1", protocol = "http", port = port)),
            ),
        )
      client.use {
        val response = it.customGet(path = path)
        assertEquals(
          "ok decompression test server response",
          response["message"]?.jsonPrimitive?.content,
        )
      }
    }
  }

  @Test fun okHttpEngine() = assertMessage(OkHttp.create())

  @Test fun cioEngine() = assertMessage(CIO.create())

  @Test fun javaEngine() = assertMessage(Java.create())

  @Test fun apache5Engine() = assertMessage(Apache5.create())

  @Suppress("DEPRECATION") @Test fun apacheEngine() = assertMessage(Apache.create())

  @Test fun androidEngine() = assertMessage(Android.create())

  @Test
  fun jettyEngine() {
    assertMessage(Jetty.create())
    assertEquals(listOf("HTTP/2.0"), httpVersions, "Jetty should have used HTTP/2")
  }

  @Test
  fun identityContentEncoding() = assertMessage(CIO.create(), path = "1/test/identity-response")

  @Test
  fun logsDecodedBody() {
    val logs = StringBuilder()
    val logger =
      object : Logger {
        override fun log(message: String) {
          logs.appendLine(message)
        }
      }
    assertMessage(OkHttp.create(), logLevel = LogLevel.BODY, logger = logger)
    assertTrue(responseBody in logs, "response body should be logged decoded, got:\n$logs")
  }
}
