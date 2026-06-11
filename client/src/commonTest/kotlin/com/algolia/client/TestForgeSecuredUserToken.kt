package com.algolia.client

import com.algolia.client.api.AgentStudioClient
import com.algolia.client.extensions.internal.encodeKeySHA256
import kotlin.io.encoding.Base64
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class TestForgeSecuredUserToken {

  @Test
  fun forgeSecuredUserToken() {
    val client = AgentStudioClient(appId = "appID", apiKey = "apiKey")

    val token = client.forgeSecuredUserToken("my-secret-key", "my-key-id", "user-123")

    val parts = token.split(".")
    assertEquals(3, parts.size)

    val headerJson = Base64.UrlSafe.decode(parts[0]).decodeToString()
    val header = Json.decodeFromString<JsonObject>(headerJson)
    assertEquals("HS256", header["alg"]!!.jsonPrimitive.content)
    assertEquals("JWT", header["typ"]!!.jsonPrimitive.content)
    assertEquals("my-key-id", header["kid"]!!.jsonPrimitive.content)

    val payloadJson = Base64.UrlSafe.decode(parts[1]).decodeToString()
    val payload = Json.decodeFromString<JsonObject>(payloadJson)
    assertEquals("user-123", payload["sub"]!!.jsonPrimitive.content)
    val exp = payload["exp"]!!.jsonPrimitive.content.toLong()
    val expectedExp = Clock.System.now().epochSeconds + 24 * 3600
    assertTrue(
      kotlin.math.abs(exp - expectedExp) < 5,
      "exp $exp should be within 5s of $expectedExp",
    )

    val expectedHmacHex =
      encodeKeySHA256(key = "my-secret-key", message = "${parts[0]}.${parts[1]}")
    val actualSigBytes = Base64.UrlSafe.decode(parts[2])
    val actualSigHex =
      actualSigBytes.joinToString("") {
        val i = it.toInt() and 0xFF
        "${"0123456789abcdef"[i shr 4]}${"0123456789abcdef"[i and 0xF]}"
      }
    assertEquals(expectedHmacHex, actualSigHex)
  }

  @Test
  fun forgeSecuredUserTokenCustomExpiry() {
    val client = AgentStudioClient(appId = "appID", apiKey = "apiKey")

    val token = client.forgeSecuredUserToken("my-secret-key", "my-key-id", "user-456", 3600)

    val parts = token.split(".")
    val payloadJson = Base64.UrlSafe.decode(parts[1]).decodeToString()
    val payload = Json.decodeFromString<JsonObject>(payloadJson)
    val exp = payload["exp"]!!.jsonPrimitive.content.toLong()
    val expectedExp = Clock.System.now().epochSeconds + 3600
    assertTrue(
      kotlin.math.abs(exp - expectedExp) < 5,
      "exp $exp should be within 5s of $expectedExp",
    )
  }
}
