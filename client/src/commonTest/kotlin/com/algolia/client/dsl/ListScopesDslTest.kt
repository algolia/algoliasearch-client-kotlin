@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.dsl.generated.DSLConsequenceParams
import com.algolia.client.dsl.rule.rule
import com.algolia.client.model.search.ConsequenceParams
import com.algolia.client.model.search.SupportedLanguage
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

internal class ListScopesDslTest {

  @Test
  fun emptyBlockSendsEmptyList() {
    val cleared = query {
      attributesToRetrieve { +"a" }
      attributesToRetrieve {}
    }
    assertEquals(emptyList(), cleared.attributesToRetrieve)

    assertEquals(emptyList(), browse { queryLanguages {} }.queryLanguages)
    assertEquals(emptyList(), consequenceParams { responseFields {} }.responseFields)

    assertNull(query { query = "x" }.attributesToRetrieve)

    assertEquals(emptyList(), settings { searchableAttributes {} }.searchableAttributes)
  }

  @Test
  fun lastWriteWins() {
    val twoBlocks = query {
      ruleContexts { +"a" }
      ruleContexts { +"b" }
    }
    assertEquals(listOf("b"), twoBlocks.ruleContexts)

    val blockAfterAssignment = query {
      ruleContexts = listOf("a")
      ruleContexts { +"b" }
    }
    assertEquals(listOf("b"), blockAfterAssignment.ruleContexts)

    val assignmentAfterBlock = query {
      ruleContexts { +"a" }
      ruleContexts = listOf("b")
    }
    assertEquals(listOf("b"), assignmentAfterBlock.ruleContexts)
  }

  @Test
  fun plusIterable() {
    assertEquals(listOf("a", "b"), query { facets { +listOf("a", "b") } }.facets)
    assertEquals(
      listOf(SupportedLanguage.En, SupportedLanguage.Fr),
      query { queryLanguages { +listOf(SupportedLanguage.En, SupportedLanguage.Fr) } }
        .queryLanguages,
    )
    assertEquals(
      listOf("hits", "nbHits"),
      query { responseFields { +listOf("hits", "nbHits") } }.responseFields,
    )

    val mixed = query {
      analyticsTags {
        +"a"
        +listOf("b", "c")
        +"d"
      }
    }
    assertEquals(listOf("a", "b", "c", "d"), mixed.analyticsTags)
  }

  private fun consequenceParams(block: DSLConsequenceParams.() -> Unit): ConsequenceParams =
    assertNotNull(rule("r") { consequence { params(block) } }.consequence.params)
}
