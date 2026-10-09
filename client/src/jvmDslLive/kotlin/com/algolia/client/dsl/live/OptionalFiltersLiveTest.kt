package com.algolia.client.dsl.live

import com.algolia.client.dsl.cases.OptionalFilterCases
import com.algolia.client.dsl.live.testkit.LiveIndex
import com.algolia.client.dsl.live.testkit.assertCase
import com.algolia.client.dsl.live.testkit.live
import kotlin.test.Test
import org.junit.AfterClass
import org.junit.BeforeClass

internal class OptionalFiltersLiveTest {

  companion object {
    private lateinit var fx: LiveIndex

    @JvmStatic
    @BeforeClass
    fun createFixture() {
      fx = LiveIndex.create("optional")
    }

    @JvmStatic
    @AfterClass
    fun deleteFixture() {
      if (::fx.isInitialized) fx.close()
    }
  }

  @Test fun unquotedFacet() = live { fx.assertCase(OptionalFilterCases.unquotedFacet) }

  @Test fun facetWithScore() = live { fx.assertCase(OptionalFilterCases.facetWithScore) }

  @Test fun facetWithScoreZero() = live { fx.assertCase(OptionalFilterCases.facetWithScoreZero) }

  @Test
  fun negatedFacetWithScore() = live { fx.assertCase(OptionalFilterCases.negatedFacetWithScore) }

  @Test fun leadingDashEscaped() = live { fx.assertCase(OptionalFilterCases.leadingDashEscaped) }

  @Test
  fun negatedLeadingDashPositional() = live {
    fx.assertCase(OptionalFilterCases.negatedLeadingDashPositional)
  }

  @Test fun andRowsScoresSummed() = live { fx.assertCase(OptionalFilterCases.andRowsScoresSummed) }

  @Test fun orRowScoresMax() = live { fx.assertCase(OptionalFilterCases.orRowScoresMax) }

  @Test
  fun orRowWithSumOrFiltersScores() = live {
    fx.assertCase(OptionalFilterCases.orRowWithSumOrFiltersScores)
  }

  @Test
  fun andBlockAndOrRowWithNegation() = live {
    fx.assertCase(OptionalFilterCases.andBlockAndOrRowWithNegation)
  }

  @Test fun rawValueWithSpace() = live { fx.assertCase(OptionalFilterCases.rawValueWithSpace) }

  @Test
  fun rawValueWithColonAndQuotes() = live {
    fx.assertCase(OptionalFilterCases.rawValueWithColonAndQuotes)
  }

  @Test
  fun negatedRawValueWithSpace() = live {
    fx.assertCase(OptionalFilterCases.negatedRawValueWithSpace)
  }

  @Test
  fun addFacetDefaultScoreZero() = live {
    fx.assertCase(OptionalFilterCases.addFacetDefaultScoreZero)
  }

  @Test
  fun explicitZeroScoreBesideUnscored() = live {
    fx.assertCase(OptionalFilterCases.explicitZeroScoreBesideUnscored)
  }
}
