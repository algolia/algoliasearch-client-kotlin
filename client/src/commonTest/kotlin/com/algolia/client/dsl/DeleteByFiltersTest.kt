@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.configuration.ClientOptions
import com.algolia.client.dsl.rule.rule
import com.algolia.client.model.search.Condition
import com.algolia.client.model.search.DeleteByParams
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlinx.serialization.json.encodeToJsonElement

internal class DeleteByFiltersTest {

  private val json = ClientOptions().json

  private fun assertWidens(block: () -> Unit) {
    val error = assertFailsWith<IllegalArgumentException> { block() }
    assertTrue(
      error.message.orEmpty().startsWith("deleteBy filters:"),
      "unexpected message: ${error.message}",
    )
  }

  @Test
  fun emptyOrFacetAmongOtherFiltersThrows() {
    assertWidens {
      deleteBy {
        filters {
          orFacet { emptyList<String>().forEach { facet("entityId", it) } }
          facet("batchId", "b2", isNegated = true)
        }
      }
    }
  }

  @Test
  fun everyEmptyGroupThrows() {
    assertWidens { deleteBy { filters { orTag {} } } }
    assertWidens { deleteBy { filters { orNumeric {} } } }
    assertWidens { deleteBy { filters { and {} } } }
    assertWidens {
      deleteBy {
        filters {
          tag("a")
          and {
            tag("b")
            orFacet {}
          }
        }
      }
    }
    assertWidens { deleteBy { filters { and { and {} } } } }
    assertWidens { deleteBy { filters {} } }
  }

  @Test
  fun composerRefusesAnEmptyFragment() {
    val composer = DSLDeleteByComposer()
    composer.add { filters { facet("locale", "en-US") } }
    composer.add { filters { emptyList<String>().forEach { facet("entityId", it) } } }
    assertWidens { composer.build() }

    assertWidens { DSLDeleteByComposer().apply { add { filters { orFacet {} } } }.build() }
    assertWidens {
      DSLDeleteByComposer()
        .apply {
          add { filters { facet("a", "1") } }
          override { filters { orFacet {} } }
        }
        .build()
    }
  }

  @Test
  fun noFilterAndNoGeoConditionThrows() {
    val noCondition = "deleteBy: no filter and no geo condition"
    fun assertNoCondition(build: () -> Unit) {
      val error = assertFailsWith<IllegalArgumentException> { build() }
      assertTrue(error.message.orEmpty().startsWith(noCondition), error.message)
    }

    assertNoCondition { deleteBy {} }
    assertNoCondition { deleteBy { filters = " " } }
    assertNoCondition { deleteBy { aroundLatLng = "" } }
    assertNoCondition { DSLDeleteByComposer().build() }
    assertNoCondition {
      DSLDeleteByComposer()
        .apply {
          add { filters { facet("a", "1") } }
          override { filters = null }
        }
        .build()
    }

    assertEquals("1,2", deleteBy { aroundLatLng = "1,2" }.aroundLatLng)
    assertEquals(
      DeleteByParams(aroundLatLng = "1,2"),
      DSLDeleteByComposer(base = { aroundLatLng = "1,2" }).build(),
    )
  }

  @Test
  fun emptyAttributeOrValueThrows() {
    assertFailsWith<IllegalArgumentException> { query { filters { facet("v", "") } } }
    assertFailsWith<IllegalArgumentException> { query { filters { facet("", "v") } } }
    assertFailsWith<IllegalArgumentException> { query { filters { tag("") } } }
    assertFailsWith<IllegalArgumentException> { query { filters { range("", 0, 1) } } }
    assertFailsWith<IllegalArgumentException> { query { optionalFilters { facet("v", "") } } }
  }

  @Test
  fun searchAndRuleFiltersStayLenient() {
    assertNull(query { filters { orFacet {} } }.filters)
    assertEquals(
      "a:1",
      query {
          filters {
            orFacet {}
            facet("a", "1")
          }
        }
        .filters,
    )
    assertEquals(
      "a:1",
      DSLQueryComposer()
        .apply {
          add { filters { orFacet {} } }
          add { filters { facet("a", "1") } }
        }
        .build()
        .filters,
    )
    assertEquals(
      Condition(pattern = "p"),
      rule("r") {
          condition {
            pattern = "p"
            filters { orFacet {} }
          }
          consequence {}
        }
        .condition,
    )

    assertNull(
      query {
          optionalFilters {
            and {}
            or {}
          }
        }
        .optionalFilters
    )
    assertEquals(
      json.parseToJsonElement("""{"optionalFilters":[["a:1"]]}"""),
      json.encodeToJsonElement(
        query {
          optionalFilters {
            or {}
            facet("a", "1")
          }
        }
      ),
    )
  }
}
