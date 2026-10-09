@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.live

import com.algolia.client.dsl.AlgoliaExperimentalDsl
import com.algolia.client.dsl.cases.QueryParamCases
import com.algolia.client.dsl.live.testkit.LiveIndex
import com.algolia.client.dsl.live.testkit.assertCase
import com.algolia.client.dsl.live.testkit.live
import com.algolia.client.dsl.searchSingleIndex
import kotlin.test.Test
import kotlin.test.assertEquals
import org.junit.AfterClass
import org.junit.BeforeClass

internal class QueryParamsLiveTest {

  companion object {
    private lateinit var fx: LiveIndex

    @JvmStatic
    @BeforeClass
    fun createFixture() {
      fx = LiveIndex.create("params")
    }

    @JvmStatic
    @AfterClass
    fun deleteFixture() {
      if (::fx.isInitialized) fx.close()
    }
  }

  @Test fun queryScalars() = live { fx.assertCase(QueryParamCases.queryScalars) }

  @Test fun typedCompanions() = live { fx.assertCase(QueryParamCases.typedCompanions) }

  @Test
  fun fullSnippetWithSearchableRestriction() = live {
    fx.assertCase(QueryParamCases.fullSnippetWithSearchableRestriction)
    val response = fx.client.searchSingleIndex(fx.name, block = QueryParamCases.fullSnippetBlock)
    assertEquals(
      listOf("1"),
      response.hits.map { it.objectID },
      "hits of the typed searchSingleIndex path",
    )
  }

  @Test
  fun restrictSearchableAttributesWithForEach() = live {
    fx.assertCase(QueryParamCases.restrictSearchableAttributesWithForEach)
  }

  @Test
  fun restrictSearchableAttributesWithEmptyForEach() = live {
    fx.assertCase(QueryParamCases.restrictSearchableAttributesWithEmptyForEach)
  }

  @Test fun attributesToHighlight() = live { fx.assertCase(QueryParamCases.attributesToHighlight) }

  @Test
  fun attributesToRetrieveWithForEach() = live {
    fx.assertCase(QueryParamCases.attributesToRetrieveWithForEach)
  }

  @Test
  fun emptyListBlockSendsEmptyList() = live {
    fx.assertCase(QueryParamCases.emptyListBlockSendsEmptyList)
  }

  @Test
  fun queryLanguagesWithoutSecondary() = live {
    fx.assertCase(QueryParamCases.queryLanguagesWithoutSecondary)
  }

  @Test
  fun queryLanguagesFrenchOnly() = live { fx.assertCase(QueryParamCases.queryLanguagesFrenchOnly) }

  @Test fun responseFields() = live { fx.assertCase(QueryParamCases.responseFields) }

  @Test fun ruleContexts() = live { fx.assertCase(QueryParamCases.ruleContexts) }
}
