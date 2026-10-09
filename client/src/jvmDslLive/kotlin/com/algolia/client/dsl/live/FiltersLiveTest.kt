package com.algolia.client.dsl.live

import com.algolia.client.dsl.cases.FilterCases
import com.algolia.client.dsl.live.testkit.LiveIndex
import com.algolia.client.dsl.live.testkit.assertCase
import com.algolia.client.dsl.live.testkit.live
import kotlin.test.Test
import org.junit.AfterClass
import org.junit.BeforeClass

internal class FiltersLiveTest {

  companion object {
    private lateinit var fx: LiveIndex

    @JvmStatic
    @BeforeClass
    fun createFixture() {
      fx = LiveIndex.create("filters")
    }

    @JvmStatic
    @AfterClass
    fun deleteFixture() {
      if (::fx.isInitialized) fx.close()
    }
  }

  @Test fun rootAndNoParens() = live { fx.assertCase(FilterCases.rootAndNoParens) }

  @Test fun orOfNegatedLeaves() = live { fx.assertCase(FilterCases.orOfNegatedLeaves) }

  @Test fun negatedLeafInsideOr() = live { fx.assertCase(FilterCases.negatedLeafInsideOr) }

  @Test fun negatedFacetsAnded() = live { fx.assertCase(FilterCases.negatedFacetsAnded) }

  @Test
  fun andBlockFlattenedBeforeNumericOr() = live {
    fx.assertCase(FilterCases.andBlockFlattenedBeforeNumericOr)
  }

  @Test fun facetAndOrGroupFlat() = live { fx.assertCase(FilterCases.facetAndOrGroupFlat) }

  @Test fun negatedRangeLeaf() = live { fx.assertCase(FilterCases.negatedRangeLeaf) }

  @Test fun orGroupsPerFamily() = live { fx.assertCase(FilterCases.orGroupsPerFamily) }

  @Test fun orFacetOfTwoValues() = live { fx.assertCase(FilterCases.orFacetOfTwoValues) }

  @Test fun entityIdsExcludingBatch() = live { fx.assertCase(FilterCases.entityIdsExcludingBatch) }

  @Test
  fun filtersBlockOverwritesString() = live {
    fx.assertCase(FilterCases.filtersBlockOverwritesString)
  }

  @Test
  fun secondFiltersBlockReplacesFirst() = live {
    fx.assertCase(FilterCases.secondFiltersBlockReplacesFirst)
  }

  @Test fun emptyGroupsOmitFields() = live { fx.assertCase(FilterCases.emptyGroupsOmitFields) }

  @Test
  fun emptyGroupsBesideLeavesDropped() = live {
    fx.assertCase(FilterCases.emptyGroupsBesideLeavesDropped)
  }

  @Test
  fun leadingDashValueIsLiteral() = live { fx.assertCase(FilterCases.leadingDashValueIsLiteral) }

  @Test
  fun negativeNumberValueIsLiteral() = live {
    fx.assertCase(FilterCases.negativeNumberValueIsLiteral)
  }

  @Test fun typedFacetValuesAnded() = live { fx.assertCase(FilterCases.typedFacetValuesAnded) }

  @Test
  fun isNegatedOnEveryValueType() = live { fx.assertCase(FilterCases.isNegatedOnEveryValueType) }

  @Test fun isNegatedOnSingleFacet() = live { fx.assertCase(FilterCases.isNegatedOnSingleFacet) }

  @Test
  fun isNegatedOnNumericAndTagLeaves() = live {
    fx.assertCase(FilterCases.isNegatedOnNumericAndTagLeaves)
  }

  @Test
  fun positionalScoreAndNegation() = live { fx.assertCase(FilterCases.positionalScoreAndNegation) }

  @Test fun orScoresSummed() = live { fx.assertCase(FilterCases.orScoresSummed) }

  @Test fun orScoresIncludingZero() = live { fx.assertCase(FilterCases.orScoresIncludingZero) }

  @Test fun andScoresSummedFlat() = live { fx.assertCase(FilterCases.andScoresSummedFlat) }

  @Test
  fun scoreOmittedVersusExplicitZeroInOr() = live {
    fx.assertCase(FilterCases.scoreOmittedVersusExplicitZeroInOr)
  }

  @Test fun andBlockWithSingleFacet() = live { fx.assertCase(FilterCases.andBlockWithSingleFacet) }

  @Test fun storedFilterLambdas() = live { fx.assertCase(FilterCases.storedFilterLambdas) }

  @Test
  fun storedFragmentsAndedWithOptionalBoost() = live {
    fx.assertCase(FilterCases.storedFragmentsAndedWithOptionalBoost)
  }

  @Test fun lookupById() = live { fx.assertCase(FilterCases.lookupById) }

  @Test
  fun lookupByIdWithExtraFilterAndQuery() = live {
    fx.assertCase(FilterCases.lookupByIdWithExtraFilterAndQuery)
  }

  @Test
  fun lookupByIdWithNonMatchingLocale() = live {
    fx.assertCase(FilterCases.lookupByIdWithNonMatchingLocale)
  }

  @Test
  fun addFacetNegatedKeepsDefaultScore() = live {
    fx.assertCase(FilterCases.addFacetNegatedKeepsDefaultScore)
  }

  @Test
  fun addFacetDefaultScoreZeroInOr() = live {
    fx.assertCase(FilterCases.addFacetDefaultScoreZeroInOr)
  }

  @Test fun addFacetStringInsideAnd() = live { fx.assertCase(FilterCases.addFacetStringInsideAnd) }

  @Test fun addFacetIntInsideAnd() = live { fx.assertCase(FilterCases.addFacetIntInsideAnd) }

  @Test
  fun addFacetBooleanInsideAnd() = live { fx.assertCase(FilterCases.addFacetBooleanInsideAnd) }

  @Test
  fun addFacetsInSeparateAndBlocks() = live {
    fx.assertCase(FilterCases.addFacetsInSeparateAndBlocks)
  }
}
