@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.configuration.ClientOptions
import com.algolia.client.dsl.rule.*
import com.algolia.client.dsl.synonym.altCorrection1
import com.algolia.client.dsl.synonym.altCorrection2
import com.algolia.client.dsl.synonym.oneWaySynonym
import com.algolia.client.dsl.synonym.placeholder
import com.algolia.client.dsl.synonym.synonym
import com.algolia.client.model.search.Consequence
import com.algolia.client.model.search.ConsequenceHide
import com.algolia.client.model.search.Promote
import com.algolia.client.model.search.PromoteObjectID
import com.algolia.client.model.search.PromoteObjectIDs
import com.algolia.client.model.search.Rule
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonObject

internal class RuleSynonymDslTest {

  private val json = ClientOptions().json

  @Test
  fun ruleWithoutConsequenceThrows() {
    assertFailsWith<IllegalArgumentException> { rule("x") {} }
  }

  @Test
  fun promoteAndHideBlocksReplaceOnSecondCall() {
    val dsl =
      rule("x") {
        consequence {
          promote { objectID("object-1", position = 0) }
          promote { objectID("object-2", position = 1) }
          hide { +"object-9" }
          hide { +"object-8" }
        }
      }
    val ctor =
      Rule(
        objectID = "x",
        consequence =
          Consequence(
            promote = listOf(Promote.of(PromoteObjectID("object-2", 1))),
            hide = listOf(ConsequenceHide("object-8")),
          ),
      )
    assertJsonEquals(ctor, dsl)
  }

  @Test
  fun promoteObjectIDsEncodesGroup() {
    val dsl = rule("x") { consequence { promote { objectIDs(listOf("a", "b"), position = 1) } } }
    val ctor =
      Rule(
        objectID = "x",
        consequence =
          Consequence(promote = listOf(Promote.of(PromoteObjectIDs(listOf("a", "b"), 1)))),
      )
    assertJsonEquals(ctor, dsl)
  }

  @Test
  fun synonymFactoriesLeaveOtherVariantFieldsUnset() {
    assertEquals(
      setOf("objectID", "type", "synonyms"),
      json
        .encodeToJsonElement(
          synonym("syn-1") {
            +"car"
            +"auto"
            +"vehicle"
          }
        )
        .jsonObject
        .keys
        .toSet(),
    )
    assertEquals(
      setOf("objectID", "type", "input", "synonyms"),
      json
        .encodeToJsonElement(
          oneWaySynonym("syn-2", input = "tablet") {
            +"ipad"
            +"galaxy tab"
          }
        )
        .jsonObject
        .keys
        .toSet(),
    )
    assertEquals(
      setOf("objectID", "type", "word", "corrections"),
      json
        .encodeToJsonElement(altCorrection1("syn-3", word = "trousers") { +"pants" })
        .jsonObject
        .keys
        .toSet(),
    )
    assertEquals(
      setOf("objectID", "type", "word", "corrections"),
      json
        .encodeToJsonElement(altCorrection2("syn-4", word = "trousers") { +"pants" })
        .jsonObject
        .keys
        .toSet(),
    )
    assertEquals(
      setOf("objectID", "type", "placeholder", "replacements"),
      json
        .encodeToJsonElement(
          placeholder("syn-5", placeholder = "<Street>") {
            +"street"
            +"st"
          }
        )
        .jsonObject
        .keys
        .toSet(),
    )
  }

  private inline fun <reified T> assertJsonEquals(constructor: T, dsl: T) {
    val constructorJson = json.encodeToJsonElement(constructor)
    val dslJson = json.encodeToJsonElement(dsl)
    assertIs<JsonObject>(constructorJson)
    assertIs<JsonObject>(dslJson)
    assertEquals(constructorJson.jsonObject, dslJson.jsonObject)
  }
}
