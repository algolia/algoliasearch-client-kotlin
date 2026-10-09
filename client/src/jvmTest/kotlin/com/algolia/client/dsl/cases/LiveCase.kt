package com.algolia.client.dsl.cases

import com.algolia.client.configuration.ClientOptions
import com.algolia.client.model.search.DeleteByParams
import com.algolia.client.model.search.SearchParamsObject
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

internal class LiveCase(
  val dsl: () -> SearchParamsObject,
  val body: String,
  val expect: List<Expect>,
)

internal class DeleteCase(
  val dsl: () -> DeleteByParams,
  val body: String,
  val remaining: Set<String>,
)

internal sealed interface Expect {
  data class Hits(val ids: Set<String>) : Expect

  data class FirstHits(val ids: Set<String>) : Expect

  data class Scores(val byId: Map<String, Int>, val others: Int? = null) : Expect

  data class NbHits(val n: Int) : Expect

  data class HitCount(val n: Int) : Expect

  data class Keys(val keys: Set<String>) : Expect

  data class HasKey(val key: String) : Expect

  data class Absent(val key: String) : Expect

  data class EveryHitHasKey(val key: String) : Expect

  data class HighlightKeys(val keys: Set<String>) : Expect

  data class HitKeys(val keys: Set<String>) : Expect

  data class UserData(val value: JsonArray) : Expect
}

internal val wireJson: Json = ClientOptions().json

internal fun json(text: String): JsonObject = Json.parseToJsonElement(text).jsonObject

internal fun jsonArray(text: String): JsonArray = Json.parseToJsonElement(text).jsonArray

internal fun wire(params: SearchParamsObject): JsonObject =
  wireJson.encodeToJsonElement(params).jsonObject

internal fun wire(params: DeleteByParams): JsonObject =
  wireJson.encodeToJsonElement(params).jsonObject
