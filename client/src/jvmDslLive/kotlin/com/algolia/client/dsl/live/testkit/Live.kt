package com.algolia.client.dsl.live.testkit

import com.algolia.client.api.SearchClient
import com.algolia.client.dsl.cases.DeleteCase
import com.algolia.client.dsl.cases.Expect
import com.algolia.client.dsl.cases.LiveCase
import com.algolia.client.dsl.cases.json
import com.algolia.client.dsl.cases.wire
import com.algolia.client.extensions.saveObjects
import com.algolia.client.extensions.waitForTask
import com.algolia.client.model.search.OperationIndexParams
import com.algolia.client.model.search.OperationType
import io.github.cdimascio.dotenv.Dotenv
import kotlin.random.Random
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.fail
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

private const val APP_ID_ENV = "ALGOLIA_APPLICATION_ID"
private const val ADMIN_KEY_ENV = "ALGOLIA_ADMIN_KEY"
private const val LIVE_PROPERTY = "algolia.dsl.live"
private const val REPO_ROOT_PROPERTY = "algolia.repoRoot"
private const val INDEX_PREFIX = "kotlin_dsl_live_"
private const val LOG_PREFIX = "[kotlin-dsl-live]"
private const val JANITOR_HITS_PER_PAGE = 100
private const val JANITOR_MAX_PAGES = 1_000
private const val JANITOR_MAX_AGE_SECONDS: Long = 24 * 60 * 60

private val INDEX_EPOCH = Regex("^kotlin_dsl_live_(\\d+)_")

private val WRITE_TIMEOUT: Duration = 60.seconds
private val SETUP_TIMEOUT: Duration = 180.seconds
private val CLOSE_TIMEOUT: Duration = 30.seconds

internal object LiveCredentials {
  fun load(): Pair<String, String> {
    val repoRoot: String? = System.getProperty(REPO_ROOT_PROPERTY)
    val appId: String?
    val apiKey: String?
    if (System.getenv("CI") == "true") {
      appId = System.getenv(APP_ID_ENV)
      apiKey = System.getenv(ADMIN_KEY_ENV)
    } else {
      checkNotNull(repoRoot) {
        "Kotlin DSL live tests must run through the Gradle live task (jvmDslLiveTest): " +
          "system property `$REPO_ROOT_PROPERTY` is not set."
      }
      val fromFile =
        Dotenv.configure()
          .directory(repoRoot)
          .ignoreIfMissing()
          .load()
          .entries(Dotenv.Filter.DECLARED_IN_ENV_FILE)
          .associate { it.key to it.value }
      appId = fromFile[APP_ID_ENV] ?: System.getenv(APP_ID_ENV)
      apiKey = fromFile[ADMIN_KEY_ENV] ?: System.getenv(ADMIN_KEY_ENV)
    }
    if (appId.isNullOrBlank() || apiKey.isNullOrBlank()) {
      throw IllegalStateException(
        "Kotlin DSL live tests need $APP_ID_ENV and $ADMIN_KEY_ENV: export them with CI=true, " +
          "or put them in ${repoRoot ?: "<repoRoot>"}/.env. " +
          "Skip live tests with `yarn cli cts run kotlin --no-e2e`."
      )
    }
    return appId to apiKey
  }
}

internal class LiveIndex
private constructor(
  val client: SearchClient,
  val name: String,
  private val fixture: LiveFixture = MAIN_FIXTURE,
) : AutoCloseable {

  companion object {
    fun create(purpose: String, fixture: LiveFixture = MAIN_FIXTURE): LiveIndex {
      check(System.getProperty(LIVE_PROPERTY) == "true") {
        "Kotlin DSL live tests only run through the Gradle live task (jvmDslLiveTest): " +
          "system property `$LIVE_PROPERTY` is not \"true\"."
      }
      val (appId, apiKey) = LiveCredentials.load()
      val client = SearchClient(appId = appId, apiKey = apiKey)
      val index = LiveIndex(client, indexName(purpose), fixture)
      println("$LOG_PREFIX creating ${index.name}")
      try {
        runBlocking {
          withTimeout(SETUP_TIMEOUT) {
            janitor(client)
            index.populate()
          }
        }
      } catch (e: Throwable) {
        index.close()
        throw e
      }
      println("$LOG_PREFIX ready ${index.name}")
      return index
    }

    private fun indexName(purpose: String): String {
      val epoch = System.currentTimeMillis() / 1000
      val hex = Random.nextInt(0, 0x1000000).toString(16).padStart(6, '0')
      return "$INDEX_PREFIX${epoch}_${hex}_$purpose"
    }

    private suspend fun janitor(client: SearchClient) {
      val cutoff = System.currentTimeMillis() / 1000 - JANITOR_MAX_AGE_SECONDS
      logged("janitor") {
        val stale = mutableListOf<String>()
        var page = 0
        while (true) {
          if (page >= JANITOR_MAX_PAGES) {
            println("$LOG_PREFIX janitor stopped listing after $JANITOR_MAX_PAGES pages")
            break
          }
          val listing = client.listIndices(page = page, hitsPerPage = JANITOR_HITS_PER_PAGE)
          for (index in listing.items) {
            val epoch =
              INDEX_EPOCH.find(index.name)?.groupValues?.get(1)?.toLongOrNull() ?: continue
            if (epoch < cutoff) stale += index.name
          }
          page++
          if (listing.items.size < JANITOR_HITS_PER_PAGE) break
          if (listing.nbPages?.let { page >= it } == true) break
        }
        for (name in stale) {
          logged("janitor delete of $name") {
            client.deleteIndex(name)
            println("$LOG_PREFIX janitor deleted stale $name")
          }
        }
      }
    }
  }

  private suspend fun populate() {
    val settings = client.setSettings(name, fixture.settings)
    client.waitForTask(name, settings.taskID, timeout = WRITE_TIMEOUT)
    client.saveObjects(name, fixture.records, waitForTasks = true)
    fixture.rule?.let { rule ->
      val saved = client.saveRule(name, rule.objectID, rule)
      client.waitForTask(name, saved.taskID, timeout = WRITE_TIMEOUT)
    }
  }

  suspend fun query(params: JsonObject): JsonObject =
    client.customPost("1/indexes/$name/query", body = params)

  suspend fun <T> withCopy(purpose: String, block: suspend (LiveIndex) -> T): T {
    val copy = LiveIndex(client, indexName(purpose), fixture)
    println("$LOG_PREFIX copying $name to ${copy.name}")
    try {
      val task =
        client.operationIndex(
          name,
          OperationIndexParams(operation = OperationType.Copy, destination = copy.name),
        )
      client.waitForTask(copy.name, task.taskID, timeout = WRITE_TIMEOUT)
      return block(copy)
    } finally {
      withContext(NonCancellable) { copy.delete() }
    }
  }

  suspend fun remainingIds(): Set<String> =
    query(json("""{"hitsPerPage":100}""")).hits().map { it.objectID() }.toSet()

  override fun close() {
    runBlocking { delete() }
  }

  private suspend fun delete() {
    logged("delete of $name") {
      val task = client.deleteIndex(name)
      client.waitForTask(name, task.taskID, timeout = CLOSE_TIMEOUT)
      println("$LOG_PREFIX deleted $name")
    }
  }
}

internal fun live(timeout: Duration = 60.seconds, block: suspend CoroutineScope.() -> Unit): Unit =
  runBlocking {
    withTimeout(timeout) { block() }
  }

internal suspend fun LiveIndex.assertCase(case: LiveCase) {
  val params = json(case.body)
  assertEquals(params, wire(case.dsl()), "DSL wire differs from the expected body")
  val response = query(params)
  case.expect.forEach { checkExpect(response, it) }
}

internal suspend fun LiveIndex.assertDelete(
  case: DeleteCase,
  delete: suspend (copy: LiveIndex) -> Long,
) {
  assertEquals(json(case.body), wire(case.dsl()), "DSL wire differs from the expected body")
  withCopy("delete") { copy ->
    val taskID = delete(copy)
    copy.client.waitForTask(copy.name, taskID, timeout = WRITE_TIMEOUT)
    assertEquals(case.remaining, copy.remainingIds(), "objectIDs remaining after deleteBy")
  }
}

private fun checkExpect(response: JsonObject, expect: Expect) {
  when (expect) {
    is Expect.Hits ->
      assertEquals(expect.ids, response.hits().map { it.objectID() }.toSet(), "hits")
    is Expect.FirstHits ->
      assertEquals(
        expect.ids,
        response.hits().take(expect.ids.size).map { it.objectID() }.toSet(),
        "first ${expect.ids.size} hits",
      )
    is Expect.Scores -> {
      val scores = response.hits().associate { it.objectID() to it.rankingFilters() }
      expect.byId.forEach { (objectID, score) ->
        assertEquals(
          score,
          scores[objectID],
          "_rankingInfo.filters of $objectID (hits: ${scores.keys})",
        )
      }
      expect.others?.let { others ->
        scores
          .filterKeys { it !in expect.byId }
          .forEach { (objectID, score) ->
            assertEquals(others, score, "_rankingInfo.filters of unlisted hit $objectID")
          }
      }
    }
    is Expect.NbHits -> assertEquals(expect.n, response["nbHits"]?.jsonPrimitive?.int, "nbHits")
    is Expect.HitCount -> assertEquals(expect.n, response.hits().size, "number of hits")
    is Expect.Keys -> assertEquals(expect.keys, response.keys, "top-level response keys")
    is Expect.HasKey ->
      assertTrue(expect.key in response, "response has no `${expect.key}` (keys: ${response.keys})")
    is Expect.Absent ->
      assertFalse(expect.key in response, "response must not contain `${expect.key}`")
    is Expect.EveryHitHasKey ->
      response.hits().forEach { hit ->
        assertTrue(expect.key in hit, "hit ${hit.objectID()} has no `${expect.key}`")
      }
    is Expect.HighlightKeys ->
      response.hits().forEach { hit ->
        assertEquals(
          expect.keys,
          hit["_highlightResult"]?.jsonObject?.keys,
          "_highlightResult keys of hit ${hit.objectID()}",
        )
      }
    is Expect.HitKeys ->
      response.hits().forEach { hit ->
        assertEquals(
          expect.keys,
          hit.keys.filterNot { it.startsWith("_") }.toSet(),
          "attributes of hit ${hit.objectID()}",
        )
      }
    is Expect.UserData -> assertEquals<JsonElement?>(expect.value, response["userData"], "userData")
  }
}

private fun JsonObject.hits(): List<JsonObject> =
  (this["hits"] ?: fail("response has no `hits` (keys: $keys)")).jsonArray.map { it.jsonObject }

private fun JsonObject.objectID(): String =
  this["objectID"]?.jsonPrimitive?.content ?: "<no objectID>"

private fun JsonObject.rankingFilters(): Int? =
  this["_rankingInfo"]?.jsonObject?.get("filters")?.jsonPrimitive?.int

private inline fun logged(what: String, block: () -> Unit) {
  try {
    block()
  } catch (e: CancellationException) {
    throw e
  } catch (e: Exception) {
    println("$LOG_PREFIX $what failed: ${e.message}")
  }
}
