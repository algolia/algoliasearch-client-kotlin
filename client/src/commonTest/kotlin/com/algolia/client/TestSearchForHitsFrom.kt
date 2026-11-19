package com.algolia.client

import com.algolia.client.extensions.from
import com.algolia.client.model.search.*
import kotlin.test.*

class TestSearchForHitsFrom {
  @Test
  fun copiesSearchParamsAddedAfterTheBridgeWasWritten() {
    val searchParamsObject =
      SearchParamsObject(
        query = "query",
        attributeCriteriaComputedBy = AttributeCriteriaComputedBy.Sum,
        reRankingApplyFilter = null,
        allowReRankingOnPersonalization = true,
      )

    val searchForHits = SearchForHits.from(searchParamsObject, "index_name")

    assertEquals("index_name", searchForHits.indexName)
    assertEquals("query", searchForHits.query)
    assertEquals(AttributeCriteriaComputedBy.Sum, searchForHits.attributeCriteriaComputedBy)
    assertEquals(true, searchForHits.allowReRankingOnPersonalization)
  }
}
