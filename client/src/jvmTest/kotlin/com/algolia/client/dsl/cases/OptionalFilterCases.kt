@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.cases

import com.algolia.client.dsl.*
import com.algolia.client.dsl.cases.samples.addFacet
import com.algolia.client.dsl.filter.*
import com.algolia.client.model.search.*

internal object OptionalFilterCases {

  private fun optional(block: DSLFacetFilters.() -> Unit): SearchParamsObject = query {
    optionalFilters(block)
    getRankingInfo = true
  }

  val unquotedFacet =
    LiveCase(
      dsl = { optional { facet("color", "red") } },
      body = """{"optionalFilters":[["color:red"]],"getRankingInfo":true}""",
      expect =
        listOf(
          Expect.Scores(mapOf("1" to 1, "3" to 1), others = 0),
          Expect.FirstHits(setOf("1", "3")),
        ),
    )

  val facetWithScore =
    LiveCase(
      dsl = { optional { facet("color", "red", score = 3) } },
      body = """{"optionalFilters":[["color:red<score=3>"]],"getRankingInfo":true}""",
      expect = listOf(Expect.Scores(mapOf("1" to 3, "3" to 3), others = 0)),
    )

  val facetWithScoreZero =
    LiveCase(
      dsl = { optional { facet("color", "red", score = 0) } },
      body = """{"optionalFilters":[["color:red<score=0>"]],"getRankingInfo":true}""",
      expect = listOf(Expect.HitCount(5), Expect.Scores(emptyMap(), others = 0)),
    )

  val negatedFacetWithScore =
    LiveCase(
      dsl = { optional { facet("color", "red", score = 2, isNegated = true) } },
      body = """{"optionalFilters":[["color:-red<score=2>"]],"getRankingInfo":true}""",
      expect = listOf(Expect.Scores(mapOf("2" to 2, "4" to 2, "5" to 2, "1" to 0, "3" to 0))),
    )

  val leadingDashEscaped =
    LiveCase(
      dsl = { optional { facet("label", "-Movie") } },
      body = """{"optionalFilters":[["label:\\-Movie"]],"getRankingInfo":true}""",
      expect = listOf(Expect.Scores(mapOf("1" to 1), others = 0)),
    )

  val negatedLeadingDashPositional =
    LiveCase(
      dsl = { optional { facet("label", "-Movie", 2, true) } },
      body = """{"optionalFilters":[["label:--Movie<score=2>"]],"getRankingInfo":true}""",
      expect = listOf(Expect.Scores(mapOf("1" to 0, "2" to 2))),
    )

  val andRowsScoresSummed =
    LiveCase(
      dsl = {
        optional {
          and {
            facet("color", "red")
            facet("category", "shirt")
          }
        }
      },
      body = """{"optionalFilters":[["color:red"],["category:shirt"]],"getRankingInfo":true}""",
      expect = listOf(Expect.Scores(mapOf("1" to 2, "2" to 1, "3" to 1, "5" to 1, "4" to 0))),
    )

  val orRowScoresMax =
    LiveCase(
      dsl = {
        optional {
          or {
            facet("color", "red", score = 2)
            facet("category", "shirt", score = 1)
          }
        }
      },
      body =
        """{"optionalFilters":[["color:red<score=2>","category:shirt<score=1>"]],"getRankingInfo":true}""",
      expect = listOf(Expect.Scores(mapOf("1" to 2, "3" to 2, "2" to 1, "5" to 1, "4" to 0))),
    )

  val orRowWithSumOrFiltersScores =
    LiveCase(
      dsl = {
        query {
          optionalFilters {
            or {
              facet("genre", "comedy", score = 500)
              facet("provider", "NBC", score = 500)
            }
          }
          sumOrFiltersScores = true
          getRankingInfo = true
        }
      },
      body =
        """{"optionalFilters":[["genre:comedy<score=500>","provider:NBC<score=500>"]],"sumOrFiltersScores":true,"getRankingInfo":true}""",
      expect =
        listOf(
          Expect.Scores(mapOf("1" to 1000, "3" to 500, "5" to 500, "2" to 0, "4" to 0)),
          Expect.FirstHits(setOf("1")),
        ),
    )

  val andBlockAndOrRowWithNegation =
    LiveCase(
      dsl = {
        query {
          optionalFilters {
            and {
              facet("genre", "comedy", score = 2)
              facet("isFeatured", true, score = 3)
            }
            or { facet("provider", "NBC", score = 1, isNegated = true) }
          }
          getRankingInfo = true
        }
      },
      body =
        """{"optionalFilters":[["genre:comedy<score=2>"],["isFeatured:true<score=3>"],["provider:-NBC<score=1>"]],"getRankingInfo":true}""",
      expect =
        listOf(
          Expect.Scores(mapOf("1" to 5, "4" to 4, "3" to 3, "5" to 3, "2" to 1)),
          Expect.FirstHits(setOf("1")),
        ),
    )

  val rawValueWithSpace =
    LiveCase(
      dsl = { optional { facet("color", "navy blue") } },
      body = """{"optionalFilters":[["color:navy blue"]],"getRankingInfo":true}""",
      expect = listOf(Expect.Scores(mapOf("5" to 1), others = 0)),
    )

  val rawValueWithColonAndQuotes =
    LiveCase(
      dsl = { optional { facet("provider", "NBC: Universal \"East\"") } },
      body = """{"optionalFilters":[["provider:NBC: Universal \"East\""]],"getRankingInfo":true}""",
      expect = listOf(Expect.Scores(mapOf("5" to 1), others = 0)),
    )

  val negatedRawValueWithSpace =
    LiveCase(
      dsl = { optional { facet("color", "navy blue", score = 2, isNegated = true) } },
      body = """{"optionalFilters":[["color:-navy blue<score=2>"]],"getRankingInfo":true}""",
      expect = listOf(Expect.Scores(mapOf("1" to 2, "2" to 2, "3" to 2, "4" to 2, "5" to 0))),
    )

  val addFacetDefaultScoreZero =
    LiveCase(
      dsl = {
        query {
          optionalFilters { addFacet("genre", "comedy") }
          getRankingInfo = true
        }
      },
      body = """{"optionalFilters":[["genre:comedy<score=0>"]],"getRankingInfo":true}""",
      expect = listOf(Expect.HitCount(5), Expect.Scores(emptyMap(), others = 0)),
    )

  val explicitZeroScoreBesideUnscored =
    LiveCase(
      dsl = {
        query {
          optionalFilters {
            facet("genre", "comedy", score = 0)
            facet("provider", "NBC")
          }
          getRankingInfo = true
        }
      },
      body =
        """{"optionalFilters":[["genre:comedy<score=0>"],["provider:NBC"]],"getRankingInfo":true}""",
      expect = listOf(Expect.Scores(mapOf("1" to 1), others = 0)),
    )
}
