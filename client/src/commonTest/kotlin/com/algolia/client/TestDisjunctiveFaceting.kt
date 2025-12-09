package com.algolia.client

import com.algolia.client.extensions.from
import com.algolia.client.extensions.internal.DisjunctiveFaceting
import com.algolia.client.model.search.*
import kotlin.test.*

class TestDisjunctiveFaceting {
  @Test
  fun testBuildFilters() {
    val refinements =
      mapOf(
        "size" to listOf("m", "s"),
        "color" to listOf("blue", "green", "red"),
        "brand" to listOf("apple", "samsung", "sony"),
      )

    val disjunctiveFacets = setOf("color")

    val helper =
      DisjunctiveFaceting(
        query = SearchForHits.from(SearchParamsObject(), "index_name"),
        refinements = refinements,
        disjunctiveFacets = disjunctiveFacets,
      )

    assertEquals(
      """("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" OR "color":"green" OR "color":"red") AND ("size":"m" AND "size":"s")""",
      helper.buildFilters(null),
    )
    assertEquals(
      """("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" OR "color":"green" OR "color":"red") AND ("size":"m" AND "size":"s")""",
      helper.buildFilters("popularity"),
    )
    assertEquals(
      """("color":"blue" OR "color":"green" OR "color":"red") AND ("size":"m" AND "size":"s")""",
      helper.buildFilters("brand"),
    )
    assertEquals(
      """("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("size":"m" AND "size":"s")""",
      helper.buildFilters("color"),
    )
    assertEquals(
      """("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" OR "color":"green" OR "color":"red")""",
      helper.buildFilters("size"),
    )
  }

  @Test
  fun testAppliedDisjunctiveFacetValues() {
    val refinements =
      mapOf(
        "size" to listOf("m", "s"),
        "color" to listOf("blue", "green", "red"),
        "brand" to listOf("apple", "samsung", "sony"),
      )

    val disjunctiveFacets = setOf("color", "brand")

    val helper =
      DisjunctiveFaceting(
        query = SearchForHits.from(SearchParamsObject(), "index_name"),
        refinements = refinements,
        disjunctiveFacets = disjunctiveFacets,
      )

    assertTrue(helper.appliedDisjunctiveFacetValues("popularity").isEmpty())
    assertTrue(helper.appliedDisjunctiveFacetValues("size").isEmpty())
    assertEquals(setOf("red", "green", "blue"), helper.appliedDisjunctiveFacetValues("color"))
    assertEquals(setOf("samsung", "sony", "apple"), helper.appliedDisjunctiveFacetValues("brand"))
  }

  @Test
  fun testBuildQueriesNoDisjunctive() {
    val refinements =
      mapOf(
        "size" to listOf("m", "s"),
        "color" to listOf("blue", "green", "red"),
        "brand" to listOf("apple", "samsung", "sony"),
      )

    val helper =
      DisjunctiveFaceting(
        query = SearchForHits.from(SearchParamsObject(), "index_name"),
        refinements = refinements,
        disjunctiveFacets = emptySet(),
      )
    val queries = helper.buildQueries()
    assertEquals(1, queries.size)

    val searchForHits = queries.first()
    assertEquals(
      """("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" AND "color":"green" AND "color":"red") AND ("size":"m" AND "size":"s")""",
      searchForHits.filters,
    )
  }

  @Test
  fun testBuildQueriesDisjunctiveSingle() {
    val refinements =
      mapOf(
        "size" to listOf("m", "s"),
        "color" to listOf("blue", "green", "red"),
        "brand" to listOf("apple", "samsung", "sony"),
      )

    val helper =
      DisjunctiveFaceting(
        query = SearchForHits.from(SearchParamsObject(), "index_name"),
        refinements = refinements,
        disjunctiveFacets = setOf("color"),
      )
    val queries = helper.buildQueries()
    assertEquals(2, queries.size)
    val searchForHits = queries.first()
    assertEquals(
      """("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" OR "color":"green" OR "color":"red") AND ("size":"m" AND "size":"s")""",
      searchForHits.filters,
    )
    val searchForHitsLast = queries.last()
    assertEquals(listOf("color"), searchForHitsLast.facets)
    assertEquals(
      """("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("size":"m" AND "size":"s")""",
      searchForHitsLast.filters,
    )
  }

  @Test
  fun testBuildQueriesDisjunctiveDouble() {
    val refinements =
      mapOf(
        "size" to listOf("m", "s"),
        "color" to listOf("blue", "green", "red"),
        "brand" to listOf("apple", "samsung", "sony"),
      )
    val disjunctiveFacets = setOf("color", "size")
    val helper =
      DisjunctiveFaceting(
        query = SearchForHits.from(SearchParamsObject(), "index_name"),
        refinements = refinements,
        disjunctiveFacets = disjunctiveFacets,
      )
    val queries = helper.buildQueries()
    assertEquals(3, queries.size)
    val searchForHits = queries.first()
    assertEquals(
      """("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" OR "color":"green" OR "color":"red") AND ("size":"m" OR "size":"s")""",
      searchForHits.filters,
    )
    val searchForHits1 = queries[1]
    assertEquals(listOf("color"), searchForHits1.facets)
    assertEquals(
      """("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("size":"m" OR "size":"s")""",
      searchForHits1.filters,
    )
    val searchForHits2 = queries[2]
    assertEquals(listOf("size"), searchForHits2.facets)
    assertEquals(
      """("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" OR "color":"green" OR "color":"red")""",
      searchForHits2.filters,
    )
  }

  @Test
  fun testMergeEmptyResponses() {
    val refinements =
      mapOf(
        "size" to listOf("m", "s"),
        "color" to listOf("blue", "green", "red"),
        "brand" to listOf("apple", "samsung", "sony"),
      )
    val disjunctiveFacets = setOf("color", "size")
    val helper =
      DisjunctiveFaceting(
        query = SearchForHits.from(SearchParamsObject(), "index_name"),
        refinements = refinements,
        disjunctiveFacets = disjunctiveFacets,
      )

    val exception = assertFailsWith<NoSuchElementException> { helper.mergeResponses(emptyList()) }
    assertEquals("List is empty.", exception.message)
  }

  @Test
  fun testMergeDisjunctiveSingle() {
    val refinements =
      mapOf(
        "size" to listOf("m", "s"),
        "color" to listOf("blue", "green", "red"),
        "brand" to listOf("apple", "samsung", "sony"),
      )
    val disjunctiveFacets = setOf("color")
    val helper =
      DisjunctiveFaceting(
        query = SearchForHits.from(SearchParamsObject(), "index_name"),
        refinements = refinements,
        disjunctiveFacets = disjunctiveFacets,
      )
    val mainResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        facets =
          mapOf(
            "size" to mapOf("s" to 5, "m" to 7),
            "color" to mapOf("red" to 1, "green" to 2, "blue" to 3),
            "brand" to mapOf("samsung" to 5, "sony" to 10, "apple" to 15),
          ),
      )
    val disjunctiveResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        facets = mapOf("color" to mapOf("red" to 10, "green" to 20, "blue" to 30)),
      )
    val result = helper.mergeResponses(listOf(mainResponse, disjunctiveResponse))
    assertEquals(
      mapOf(
        "size" to mapOf("s" to 5, "m" to 7),
        "color" to mapOf("red" to 1, "green" to 2, "blue" to 3),
        "brand" to mapOf("samsung" to 5, "sony" to 10, "apple" to 15),
      ),
      result.response.facets,
    )
    assertEquals(
      mapOf("color" to mapOf("red" to 10, "green" to 20, "blue" to 30)),
      result.disjunctiveFacets,
    )
  }

  @Test
  fun testMergeDisjunctiveDouble() {
    val refinements =
      mapOf(
        "size" to listOf("m", "s"),
        "color" to listOf("blue", "green", "red"),
        "brand" to listOf("apple", "samsung", "sony"),
      )
    val disjunctiveFacets = setOf("color", "size")
    val helper =
      DisjunctiveFaceting(
        query = SearchForHits.from(SearchParamsObject(), "index_name"),
        refinements = refinements,
        disjunctiveFacets = disjunctiveFacets,
      )
    val mainResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        facets =
          mapOf(
            "size" to mapOf("s" to 5, "m" to 7),
            "color" to mapOf("red" to 1, "green" to 2, "blue" to 3),
            "brand" to mapOf("samsung" to 5, "sony" to 10, "apple" to 15),
          ),
      )
    val firstDisjunctiveResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        facets = mapOf("color" to mapOf("red" to 10, "green" to 20, "blue" to 30)),
      )
    val secondDisjunctiveResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        facets = mapOf("size" to mapOf("s" to 3, "m" to 4)),
      )
    val result =
      helper.mergeResponses(
        listOf(mainResponse, firstDisjunctiveResponse, secondDisjunctiveResponse)
      )
    assertEquals(
      mapOf(
        "size" to mapOf("s" to 5, "m" to 7),
        "color" to mapOf("red" to 1, "green" to 2, "blue" to 3),
        "brand" to mapOf("samsung" to 5, "sony" to 10, "apple" to 15),
      ),
      result.response.facets,
    )
    assertEquals(
      mapOf(
        "color" to mapOf("red" to 10, "green" to 20, "blue" to 30),
        "size" to mapOf("s" to 3, "m" to 4),
      ),
      result.disjunctiveFacets,
    )
  }

  @Test
  fun testMergeFacetStats() {
    val helper =
      DisjunctiveFaceting(
        query = SearchForHits.from(SearchParamsObject(), "index_name"),
        refinements = emptyMap(),
        disjunctiveFacets = emptySet(),
      )

    val mainResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        facetsStats =
          mapOf(
            "price" to FacetStats(min = 5.0, max = 100.0, avg = 52.5, sum = 2400.0),
            "target" to FacetStats(min = 1.0, max = 10.0, avg = 5.5, sum = 43.0),
          ),
      )

    val firstDisjunctiveResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        facetsStats =
          mapOf(
            "price" to FacetStats(min = 7.0, max = 103.0, avg = 55.0, sum = 3000.0),
            "note" to FacetStats(min = 1.0, max = 5.0, avg = 3.0, sum = 37.0),
          ),
      )

    val secondDisjunctiveResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        facetsStats = mapOf("size" to FacetStats(min = 20.0, max = 56.0, avg = 38.0, sum = 242.0)),
      )

    val result =
      helper.mergeResponses(
        listOf(mainResponse, firstDisjunctiveResponse, secondDisjunctiveResponse)
      )

    assertEquals(4, result.response.facetsStats?.size)
    assertEqual(
      FacetStats(min = 7.0, max = 103.0, avg = 55.0, sum = 3000.0),
      result.response.facetsStats?.get("price"),
    )
    assertEqual(
      FacetStats(min = 1.0, max = 10.0, avg = 5.5, sum = 43.0),
      result.response.facetsStats?.get("target"),
    )
    assertEqual(
      FacetStats(min = 20.0, max = 56.0, avg = 38.0, sum = 242.0),
      result.response.facetsStats?.get("size"),
    )
    assertEqual(
      FacetStats(min = 1.0, max = 5.0, avg = 3.0, sum = 37.0),
      result.response.facetsStats?.get("note"),
    )
  }

  private fun assertEqual(lhs: FacetStats?, rhs: FacetStats?) {
    if (lhs == null) {
      assertTrue(rhs == null)
      return
    }
    if (rhs == null) {
      fail("Expected non-null FacetStats")
    }

    if (lhs.avg != null && rhs.avg != null) {
      assertEquals(lhs.min!!, rhs.min!!, 0.01)
    } else {
      assertEquals(lhs.min, rhs.min)
    }
    if (lhs.avg != null && rhs.avg != null) {
      assertEquals(lhs.max!!, rhs.max!!, 0.01)
    } else {
      assertEquals(lhs.max, rhs.max)
    }
    if (lhs.avg != null && rhs.avg != null) {
      assertEquals(lhs.avg!!, rhs.avg!!, 0.01)
    } else {
      assertEquals(lhs.avg, rhs.avg)
    }
    if (lhs.sum != null && rhs.sum != null) {
      assertEquals(lhs.sum!!, rhs.sum!!, 0.01)
    } else {
      assertEquals(lhs.sum, rhs.sum)
    }
  }

  @Test
  fun testMergeExhaustiveFacetsCount() {
    val helper =
      DisjunctiveFaceting(
        query = SearchForHits.from(SearchParamsObject(), "index_name"),
        refinements = emptyMap(),
        disjunctiveFacets = emptySet(),
      )

    val mainResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        exhaustive = Exhaustive(facetsCount = true),
      )

    val firstDisjunctiveResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        exhaustive = Exhaustive(facetsCount = true),
      )

    var secondDisjunctiveResponse =
      SearchResponse(
        processingTimeMS = 100,
        hits = emptyList(),
        query = "",
        params = "",
        exhaustive = Exhaustive(facetsCount = false),
      )

    var result =
      helper.mergeResponses(
        listOf(mainResponse, firstDisjunctiveResponse, secondDisjunctiveResponse)
      )

    assertNotNull(result.response.exhaustive?.facetsCount)
    assertFalse(result.response.exhaustive!!.facetsCount!!)

    secondDisjunctiveResponse =
      secondDisjunctiveResponse.copy(exhaustive = Exhaustive(facetsCount = true))
    result =
      helper.mergeResponses(
        listOf(mainResponse, firstDisjunctiveResponse, secondDisjunctiveResponse)
      )

    assertNotNull(result.response.exhaustive?.facetsCount)
    assertTrue(result.response.exhaustive!!.facetsCount!!)
  }

  @Test
  fun testKeepExistingFilters() {
    val query =
      SearchForHits.from(SearchParamsObject(), "index_name").copy(filters = "NOT color:blue")

    val refinements =
      mapOf(
        "size" to listOf("m", "s"),
        "color" to listOf("blue", "green", "red"),
        "brand" to listOf("apple", "samsung", "sony"),
      )

    val disjunctiveFacets = setOf("color", "size")

    val helper =
      DisjunctiveFaceting(
        query = query,
        refinements = refinements,
        disjunctiveFacets = disjunctiveFacets,
      )

    val queries = helper.buildQueries()
    assertEquals(3, queries.size)

    val firstQuery = queries[0]
    assertEquals(
      firstQuery.filters,
      """
      NOT color:blue AND ("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" OR "color":"green" OR "color":"red") AND ("size":"m" OR "size":"s")
      """
        .trimIndent(),
    )

    val secondQuery = queries[1]
    assertEquals(listOf("color"), secondQuery.facets)
    assertEquals(
      """
      NOT color:blue AND ("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("size":"m" OR "size":"s")
      """
        .trimIndent(),
      secondQuery.filters,
    )

    val thirdQuery = queries[2]
    assertEquals(listOf("size"), thirdQuery.facets)
    assertEquals(
      """
      NOT color:blue AND ("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" OR "color":"green" OR "color":"red")
      """
        .trimIndent(),
      thirdQuery.filters,
    )
  }

  @Test
  fun testKeepExistingFiltersEmpty() {
    val query = SearchForHits.from(SearchParamsObject(), "index_name").copy(filters = "")

    val refinements =
      mapOf(
        "size" to listOf("m", "s"),
        "color" to listOf("blue", "green", "red"),
        "brand" to listOf("apple", "samsung", "sony"),
      )

    val disjunctiveFacets = setOf("color", "size")

    val helper =
      DisjunctiveFaceting(
        query = query,
        refinements = refinements,
        disjunctiveFacets = disjunctiveFacets,
      )

    val queries = helper.buildQueries()
    assertEquals(3, queries.size)

    val firstQuery = queries[0]
    assertEquals(
      """
      ("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" OR "color":"green" OR "color":"red") AND ("size":"m" OR "size":"s")
      """
        .trimIndent(),
      firstQuery.filters,
    )

    val secondQuery = queries[1]
    assertEquals(listOf("color"), secondQuery.facets)
    assertEquals(
      """
      ("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("size":"m" OR "size":"s")
      """
        .trimIndent(),
      secondQuery.filters,
    )

    val thirdQuery = queries[2]
    assertEquals(listOf("size"), thirdQuery.facets)
    assertEquals(
      """
      ("brand":"apple" AND "brand":"samsung" AND "brand":"sony") AND ("color":"blue" OR "color":"green" OR "color":"red")
      """
        .trimIndent(),
      thirdQuery.filters,
    )
  }

  @Test
  fun testKeepExistingFiltersNoRefinement() {
    val query =
      SearchForHits.from(SearchParamsObject(), "index_name").copy(filters = "NOT color:blue")

    val disjunctiveFacets = setOf("color", "size")

    val helper =
      DisjunctiveFaceting(
        query = query,
        refinements = emptyMap(),
        disjunctiveFacets = disjunctiveFacets,
      )

    val queries = helper.buildQueries()
    assertEquals(3, queries.size)

    val firstQuery = queries[0]
    assertEquals("NOT color:blue", firstQuery.filters)

    val secondQuery = queries[1]
    assertEquals(listOf("color"), secondQuery.facets)
    assertEquals("NOT color:blue", secondQuery.filters)

    val thirdQuery = queries[2]
    assertEquals(listOf("size"), thirdQuery.facets)
    assertEquals("NOT color:blue", thirdQuery.filters)
  }
}
