@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.cases

import com.algolia.client.dsl.*
import com.algolia.client.dsl.filter.*
import com.algolia.client.model.search.*

internal object DeleteCases {

  val orFacetOfTwoValues =
    DeleteCase(
      dsl = {
        deleteBy {
          filters {
            orFacet {
              facet("entityId", "e1")
              facet("entityId", "e2")
            }
          }
        }
      },
      body = """{"filters":"(entityId:e1 OR entityId:e2)"}""",
      remaining = setOf("3", "4", "5"),
    )

  val entityIdsExcludingBatch =
    DeleteCase(
      dsl = {
        deleteBy {
          filters(
            FilterCases.entityIdsExcludingBatchFilters(
              FilterCases.entityIds,
              FilterCases.CURRENT_BATCH_ID,
            )
          )
        }
      },
      body = """{"filters":"(entityId:e1 OR entityId:e2 OR entityId:e3) AND NOT batchId:b2"}""",
      remaining = setOf("3", "4", "5"),
    )

  val composerFragmentsAnded =
    DeleteCase(
      dsl = {
        val composer = DSLDeleteByComposer()
        composer.add {
          filters { orFacet { listOf("e1", "e2", "e3").forEach { facet("entityId", it) } } }
        }
        composer.add { filters { facet("batchId", "b2", isNegated = true) } }
        composer.build()
      },
      body = """{"filters":"(entityId:e1 OR entityId:e2 OR entityId:e3) AND NOT batchId:b2"}""",
      remaining = setOf("3", "4", "5"),
    )

  val composerBaseMergedWithFragments =
    DeleteCase(
      dsl = {
        DSLDeleteByComposer(
            base = {
              filters { orFacet { listOf("e1", "e2", "e3").forEach { facet("entityId", it) } } }
            }
          )
          .apply { add { filters { facet("batchId", "b2", isNegated = true) } } }
          .build()
      },
      body = """{"filters":"(entityId:e1 OR entityId:e2 OR entityId:e3) AND NOT batchId:b2"}""",
      remaining = setOf("3", "4", "5"),
    )
}
