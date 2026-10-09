@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.configuration.ClientOptions
import com.algolia.client.model.search.IndexSettings
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

internal class DslSerializationTest {

  private val json = ClientOptions().json

  @Test
  fun settingsLastWriteWinsSearchableAttributesBlockOverwritesList() {
    val dsl = settings {
      searchableAttributes = listOf("old")
      searchableAttributes { ordered("name") }
    }
    assertJsonEquals(IndexSettings(searchableAttributes = listOf("name")), dsl)
  }

  @Test
  fun settingsOrderedVarargJoinsWithCommaSpace() {
    val dsl = settings { searchableAttributes { ordered("title", "name") } }
    assertJsonEquals(IndexSettings(searchableAttributes = listOf("title, name")), dsl)
  }

  private inline fun <reified T> assertJsonEquals(constructor: T, dsl: T) {
    val constructorJson = json.encodeToJsonElement(constructor)
    val dslJson = json.encodeToJsonElement(dsl)
    assertIs<JsonObject>(constructorJson)
    assertIs<JsonObject>(dslJson)
    assertEquals(constructorJson.jsonObject, dslJson.jsonObject)
  }
}
