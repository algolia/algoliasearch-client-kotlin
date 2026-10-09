@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.model.search.OptionalFilters
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

internal class DSLQueryComposerTest {

  @Test
  fun buildIsRepeatable() {
    val composer = DSLQueryComposer()
    composer.add { ruleContexts { +"a" } }
    composer.override { hitsPerPage = 5 }

    val first = composer.build()
    assertEquals(first, composer.build())

    composer.add { ruleContexts { +"b" } }
    val second = composer.build()
    assertEquals(listOf("a", "b"), second.ruleContexts)
    assertEquals(5, second.hitsPerPage)
    assertEquals(listOf("a"), first.ruleContexts)
  }

  @Test
  fun nothingRunsBeforeBuild() {
    var runs = 0
    val composer = DSLQueryComposer()
    composer.add {
      runs++
      ruleContexts {
        runs++
        +"x"
      }
    }
    composer.override { runs++ }
    assertEquals(0, runs)

    composer.build()
    assertEquals(3, runs)

    composer.build()
    assertEquals(6, runs)
  }

  @Test
  fun lateBoundCapturesReadAtBuild() {
    var locale = "en-US"
    val contexts = mutableListOf("a")
    val composer = DSLQueryComposer()
    composer.add { filters { facet("locale", locale) } }
    composer.add { ruleContexts { +contexts } }

    locale = "fr-FR"
    contexts += "b"

    val params = composer.build()
    assertEquals("locale:fr-FR", params.filters)
    assertEquals(listOf("a", "b"), params.ruleContexts)
  }

  @Test
  fun rawBaseFilterCannotMergeWithFragments() {
    val error =
      assertFailsWith<IllegalStateException> {
        DSLQueryComposer(base = { filters = "a:1 OR b:2" })
          .apply { add { filters { facet("c", "3") } } }
          .build()
      }
    assertTrue(error.message.orEmpty().contains("filters { } in the base"))

    assertFailsWith<IllegalStateException> {
      DSLQueryComposer(base = { optionalFilters = OptionalFilters.of("a:1") })
        .apply { add { optionalFilters { facet("b", "2") } } }
        .build()
    }

    val untouched =
      DSLQueryComposer(base = { filters = "a:1 OR b:2" })
        .apply { add { ruleContexts { +"x" } } }
        .build()
    assertEquals("a:1 OR b:2", untouched.filters)

    assertFailsWith<IllegalStateException> {
      DSLDeleteByComposer(base = { filters = "a:1 OR b:2" })
        .apply { add { filters { facet("c", "3") } } }
        .build()
    }
  }
}
