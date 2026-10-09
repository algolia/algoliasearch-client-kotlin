@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.cases

import com.algolia.client.dsl.*
import com.algolia.client.dsl.cases.samples.IS_PINNED
import com.algolia.client.dsl.cases.samples.IsPinned
import com.algolia.client.dsl.cases.samples.PRIORITY
import com.algolia.client.dsl.cases.samples.SUGGESTION_TYPE
import com.algolia.client.dsl.cases.samples.addFacet
import com.algolia.client.dsl.cases.samples.lookupById
import com.algolia.client.dsl.filter.*
import com.algolia.client.model.search.*

internal object FilterCases {

  val rootAndNoParens =
    LiveCase(
      dsl = {
        query {
          filters {
            facet("color", "red")
            facet("category", "shirt")
          }
        }
      },
      body = """{"filters":"color:red AND category:shirt"}""",
      expect = listOf(Expect.Hits(setOf("1"))),
    )

  val orOfNegatedLeaves =
    LiveCase(
      dsl = {
        query {
          filters {
            orFacet {
              facet("color", "red", isNegated = true)
              facet("category", "shirt", isNegated = true)
            }
          }
        }
      },
      body = """{"filters":"(NOT color:red OR NOT category:shirt)"}""",
      expect = listOf(Expect.Hits(setOf("2", "3", "4", "5"))),
    )

  val negatedLeafInsideOr =
    LiveCase(
      dsl = {
        query {
          filters {
            orFacet {
              facet("color", "red")
              facet("category", "shirt", isNegated = true)
            }
          }
        }
      },
      body = """{"filters":"(color:red OR NOT category:shirt)"}""",
      expect = listOf(Expect.Hits(setOf("1", "3", "4"))),
    )

  val negatedFacetsAnded =
    LiveCase(
      dsl = {
        query {
          filters {
            facet("color", "red", isNegated = true)
            facet("color", "blue", isNegated = true)
          }
        }
      },
      body = """{"filters":"NOT color:red AND NOT color:blue"}""",
      expect = listOf(Expect.Hits(setOf("4", "5"))),
    )

  val andBlockFlattenedBeforeNumericOr =
    LiveCase(
      dsl = {
        query {
          filters {
            and {
              facet("color", "red")
              facet("category", "shirt")
            }
            orNumeric {
              range("count", 0..9)
              comparison("count", NumericOperator.Equals, 10)
            }
          }
        }
      },
      body = """{"filters":"color:red AND category:shirt AND (count:0 TO 9 OR count = 10)"}""",
      expect = listOf(Expect.Hits(setOf("1"))),
    )

  val facetAndOrGroupFlat =
    LiveCase(
      dsl = {
        query {
          filters {
            facet("color", "red")
            orFacet {
              facet("category", "shirt")
              facet("category", "pants")
            }
          }
        }
      },
      body = """{"filters":"color:red AND (category:shirt OR category:pants)"}""",
      expect = listOf(Expect.Hits(setOf("1", "3"))),
    )

  val negatedRangeLeaf =
    LiveCase(
      dsl = { query { filters { range("count", 0..9, isNegated = true) } } },
      body = """{"filters":"NOT count:0 TO 9"}""",
      expect = listOf(Expect.Hits(setOf("1", "2"))),
    )

  val orGroupsPerFamily =
    LiveCase(
      dsl = {
        query {
          filters {
            orNumeric {
              range("count", 0..9)
              comparison("count", NumericOperator.Equals, 10)
            }
            orTag {
              tag("featured")
              tag("x")
            }
          }
        }
      },
      body = """{"filters":"(count:0 TO 9 OR count = 10) AND (_tags:featured OR _tags:x)"}""",
      expect = listOf(Expect.Hits(setOf("1"))),
    )

  val orFacetOfTwoValues =
    LiveCase(
      dsl = {
        query {
          filters {
            orFacet {
              facet("entityId", "e1")
              facet("entityId", "e2")
            }
          }
        }
      },
      body = """{"filters":"(entityId:e1 OR entityId:e2)"}""",
      expect = listOf(Expect.Hits(setOf("1", "2"))),
    )

  fun entityIdsExcludingBatchFilters(
    entityIds: List<String>,
    currentBatchId: String,
  ): DSLFilters.() -> Unit = {
    orFacet { entityIds.forEach { facet("entityId", it) } }
    and { facet("batchId", currentBatchId, isNegated = true) }
  }

  val entityIds: List<String> = listOf("e1", "e2", "e3")
  const val CURRENT_BATCH_ID: String = "b2"

  val entityIdsExcludingBatch =
    LiveCase(
      dsl = { query { filters(entityIdsExcludingBatchFilters(entityIds, CURRENT_BATCH_ID)) } },
      body = """{"filters":"(entityId:e1 OR entityId:e2 OR entityId:e3) AND NOT batchId:b2"}""",
      expect = listOf(Expect.Hits(setOf("1", "2"))),
    )

  val filtersBlockOverwritesString =
    LiveCase(
      dsl = {
        query {
          filters = "genre:drama"
          filters { facet("genre", "comedy") }
        }
      },
      body = """{"filters":"genre:comedy"}""",
      expect = listOf(Expect.Hits(setOf("1", "3", "5"))),
    )

  val secondFiltersBlockReplacesFirst =
    LiveCase(
      dsl = {
        query {
          filters { facet("genre", "comedy") }
          filters { facet("genre", "drama") }
        }
      },
      body = """{"filters":"genre:drama"}""",
      expect = listOf(Expect.Hits(setOf("2"))),
    )

  val emptyGroupsOmitFields =
    LiveCase(
      dsl = {
        query {
          query = "office"
          filters {
            and {}
            orFacet {}
          }
          optionalFilters { or {} }
        }
      },
      body = """{"query":"office"}""",
      expect = listOf(Expect.Hits(setOf("1", "2", "3", "4"))),
    )

  val emptyGroupsBesideLeavesDropped =
    LiveCase(
      dsl = {
        query {
          filters {
            facet("color", "red")
            and {}
            orTag {}
          }
          optionalFilters {
            and {}
            or {}
            facet("genre", "comedy")
          }
        }
      },
      body = """{"filters":"color:red","optionalFilters":[["genre:comedy"]]}""",
      expect = listOf(Expect.Hits(setOf("1", "3"))),
    )

  val leadingDashValueIsLiteral =
    LiveCase(
      dsl = { query { filters { facet("label", "-Movie") } } },
      body = """{"filters":"label:-Movie"}""",
      expect = listOf(Expect.Hits(setOf("1"))),
    )

  val negativeNumberValueIsLiteral =
    LiveCase(
      dsl = { query { filters { facet("count", -12) } } },
      body = """{"filters":"count:-12"}""",
      expect = listOf(Expect.Hits(setOf("2"))),
    )

  val typedFacetValuesAnded =
    LiveCase(
      dsl = {
        query {
          filters {
            facet("genre", "comedy")
            facet("priority", 1)
            facet("isFeatured", true)
          }
        }
      },
      body = """{"filters":"genre:comedy AND priority:1 AND isFeatured:true"}""",
      expect = listOf(Expect.Hits(setOf("1"))),
    )

  val isNegatedOnEveryValueType =
    LiveCase(
      dsl = {
        query {
          filters {
            facet("genre", "comedy", isNegated = true)
            facet("isFeatured", true, isNegated = true)
            facet("priority", 1, isNegated = true)
          }
        }
      },
      body = """{"filters":"NOT genre:comedy AND NOT isFeatured:true AND NOT priority:1"}""",
      expect = listOf(Expect.Hits(setOf("2"))),
    )

  val isNegatedOnSingleFacet =
    LiveCase(
      dsl = { query { filters { facet("genre", "comedy", isNegated = true) } } },
      body = """{"filters":"NOT genre:comedy"}""",
      expect = listOf(Expect.Hits(setOf("2", "4"))),
    )

  val isNegatedOnNumericAndTagLeaves =
    LiveCase(
      dsl = {
        query {
          filters {
            comparison("priority", NumericOperator.Equals, 1, isNegated = true)
            range("priority", 3, 3, isNegated = true)
            range("count", 0L..9L, isNegated = true)
            tag("featured", isNegated = true)
          }
        }
      },
      body =
        """{"filters":"NOT priority = 1 AND NOT priority:3 TO 3 AND NOT count:0 TO 9 AND NOT _tags:featured"}""",
      expect = listOf(Expect.Hits(setOf("2"))),
    )

  val positionalScoreAndNegation =
    LiveCase(
      dsl = { query { filters { facet("genre", "comedy", 5, true) } } },
      body = """{"filters":"NOT genre:comedy<score=5>"}""",
      expect = listOf(Expect.Hits(setOf("2", "4"))),
    )

  val orScoresSummed =
    LiveCase(
      dsl = {
        query {
          filters {
            orFacet {
              facet("color", "red", score = 3)
              facet("color", "blue", score = 1)
            }
          }
          sumOrFiltersScores = true
          getRankingInfo = true
        }
      },
      body =
        """{"filters":"(color:red<score=3> OR color:blue<score=1>)","sumOrFiltersScores":true,"getRankingInfo":true}""",
      expect =
        listOf(
          Expect.Hits(setOf("1", "2", "3")),
          Expect.Scores(mapOf("1" to 3, "3" to 3, "2" to 1)),
        ),
    )

  val orScoresIncludingZero =
    LiveCase(
      dsl = {
        query {
          filters {
            orFacet {
              facet("color", "red", score = 0)
              facet("color", "blue", score = 2)
            }
          }
          getRankingInfo = true
        }
      },
      body = """{"filters":"(color:red<score=0> OR color:blue<score=2>)","getRankingInfo":true}""",
      expect =
        listOf(
          Expect.Hits(setOf("1", "2", "3")),
          Expect.Scores(mapOf("1" to 0, "3" to 0, "2" to 2)),
        ),
    )

  val andScoresSummedFlat =
    LiveCase(
      dsl = {
        query {
          filters {
            facet("color", "red", score = 3)
            facet("category", "shirt")
          }
          getRankingInfo = true
        }
      },
      body = """{"filters":"color:red<score=3> AND category:shirt","getRankingInfo":true}""",
      expect = listOf(Expect.Hits(setOf("1")), Expect.Scores(mapOf("1" to 4))),
    )

  val scoreOmittedVersusExplicitZeroInOr =
    LiveCase(
      dsl = {
        query {
          filters {
            orFacet {
              facet("genre", "comedy", score = 0)
              facet("provider", "NBC")
            }
          }
          getRankingInfo = true
        }
      },
      body = """{"filters":"(genre:comedy<score=0> OR provider:NBC)","getRankingInfo":true}""",
      expect =
        listOf(
          Expect.Hits(setOf("1", "3", "5")),
          Expect.Scores(mapOf("1" to 1, "3" to 0, "5" to 0)),
        ),
    )

  val andBlockWithSingleFacet =
    LiveCase(
      dsl = { query { filters { and { facet("locale", "en-US") } } } },
      body = """{"filters":"locale:en-US"}""",
      expect = listOf(Expect.Hits(setOf("1", "4"))),
    )

  private val locales: DSLFilters.() -> Unit = {
    orFacet {
      facet("locale", "en-US")
      facet("locale", "fr-FR")
    }
  }
  private val counts: DSLFilters.() -> Unit = { range("count", 0..10) }
  private val featured: DSLFilters.() -> Unit = { tag("featured") }

  val storedFilterLambdas =
    LiveCase(
      dsl = {
        query {
          filters {
            locales()
            counts()
            featured()
          }
        }
      },
      body =
        """{"filters":"(locale:en-US OR locale:fr-FR) AND count:0 TO 10 AND _tags:featured"}""",
      expect = listOf(Expect.Hits(setOf("1"))),
    )

  private val notPinned: DSLFilters.() -> Unit = { facet("isPinned", false) }
  private val boost: DSLFacetFilters.() -> Unit = { or { facet("isFeatured", true, score = 500) } }

  val storedFragmentsAndedWithOptionalBoost =
    LiveCase(
      dsl = {
        query {
          filters {
            locales()
            notPinned()
          }
          optionalFilters(boost)
          getRankingInfo = true
        }
      },
      body =
        """{"filters":"(locale:en-US OR locale:fr-FR) AND isPinned:false","optionalFilters":[["isFeatured:true<score=500>"]],"getRankingInfo":true}""",
      expect = listOf(Expect.Hits(setOf("1", "4")), Expect.Scores(mapOf("1" to 502, "4" to 502))),
    )

  val lookupById =
    LiveCase(
      dsl = { lookupById("e1") },
      body = """{"filters":"entityId:e1","attributesToHighlight":[],"getRankingInfo":false}""",
      expect = listOf(Expect.Hits(setOf("1"))),
    )

  val lookupByIdWithExtraFilterAndQuery =
    LiveCase(
      dsl = {
        lookupById(
          "e1",
          extraFilters = { facet("locale", "en-US") },
          extraQuery = { hitsPerPage = 1 },
        )
      },
      body =
        """{"filters":"entityId:e1 AND locale:en-US","attributesToHighlight":[],"getRankingInfo":false,"hitsPerPage":1}""",
      expect = listOf(Expect.Hits(setOf("1"))),
    )

  val lookupByIdWithNonMatchingLocale =
    LiveCase(
      dsl = { lookupById("e1", extraFilters = { facet("locale", "fr-FR") }) },
      body =
        """{"filters":"entityId:e1 AND locale:fr-FR","attributesToHighlight":[],"getRankingInfo":false}""",
      expect = listOf(Expect.Hits(emptySet())),
    )

  val addFacetNegatedKeepsDefaultScore =
    LiveCase(
      dsl = { query { filters { addFacet("batchId", "b2", isNegated = true) } } },
      body = """{"filters":"NOT batchId:b2<score=0>"}""",
      expect = listOf(Expect.Hits(setOf("1", "2", "5"))),
    )

  val addFacetDefaultScoreZeroInOr =
    LiveCase(
      dsl = {
        query {
          filters {
            orFacet {
              addFacet("genre", "comedy")
              addFacet("genre", "drama", score = 3)
            }
          }
          getRankingInfo = true
        }
      },
      body =
        """{"filters":"(genre:comedy<score=0> OR genre:drama<score=3>)","getRankingInfo":true}""",
      expect =
        listOf(
          Expect.Hits(setOf("1", "2", "3", "5")),
          Expect.Scores(mapOf("2" to 3, "1" to 0, "3" to 0, "5" to 0)),
        ),
    )

  val addFacetStringInsideAnd =
    LiveCase(
      dsl = { query { filters { and { addFacet(SUGGESTION_TYPE, "show") } } } },
      body = """{"filters":"suggestionType:show<score=0>"}""",
      expect = listOf(Expect.Hits(setOf("1", "3", "5"))),
    )

  val addFacetIntInsideAnd =
    LiveCase(
      dsl = { query { filters { and { addFacet(PRIORITY, 2) } } } },
      body = """{"filters":"priority:2<score=0>"}""",
      expect = listOf(Expect.Hits(setOf("2", "3"))),
    )

  val addFacetBooleanInsideAnd =
    LiveCase(
      dsl = { query { filters { and { addFacet(IS_PINNED, IsPinned.FALSE.value) } } } },
      body = """{"filters":"isPinned:false<score=0>"}""",
      expect = listOf(Expect.Hits(setOf("1", "3", "4"))),
    )

  val addFacetsInSeparateAndBlocks =
    LiveCase(
      dsl = {
        query {
          filters {
            and { addFacet(SUGGESTION_TYPE, "show") }
            and { addFacet(PRIORITY, 2) }
            and { addFacet(IS_PINNED, IsPinned.FALSE.value) }
          }
        }
      },
      body =
        """{"filters":"suggestionType:show<score=0> AND priority:2<score=0> AND isPinned:false<score=0>"}""",
      expect = listOf(Expect.Hits(setOf("3"))),
    )
}
