@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.cases

import com.algolia.client.dsl.*
import com.algolia.client.dsl.filter.*
import com.algolia.client.model.search.*

internal object QueryParamCases {

  val queryScalars =
    LiveCase(
      dsl = {
        query {
          query = "office"
          hitsPerPage = 2
          distinct = Distinct.of(1)
          getRankingInfo = true
          restrictHighlightAndSnippetArrays = true
          clickAnalytics = true
        }
      },
      body =
        """{"query":"office","hitsPerPage":2,"distinct":1,"getRankingInfo":true,"restrictHighlightAndSnippetArrays":true,"clickAnalytics":true}""",
      expect =
        listOf(
          Expect.NbHits(4),
          Expect.HitCount(2),
          Expect.HasKey("queryID"),
          Expect.EveryHitHasKey("_rankingInfo"),
        ),
    )

  val typedCompanions =
    LiveCase(
      dsl = {
        query {
          distinct = Distinct.of(1)
          typoTolerance = TypoTolerance.of(TypoToleranceEnum.Min)
          ignorePlurals = IgnorePlurals.of(listOf(SupportedLanguage.En))
          removeStopWords = RemoveStopWords.of(true)
          userToken = "user-1"
          enablePersonalization = false
          synonyms = true
          enableReRanking = false
          analytics = false
        }
      },
      body =
        """{"distinct":1,"typoTolerance":"min","ignorePlurals":["en"],"removeStopWords":true,"userToken":"user-1","enablePersonalization":false,"synonyms":true,"enableReRanking":false,"analytics":false}""",
      expect = listOf(Expect.NbHits(5)),
    )

  val fullSnippetBlock: DSLQuery.() -> Unit = {
    query = "office"
    hitsPerPage = 10
    distinct = Distinct.of(1)
    getRankingInfo = true
    restrictHighlightAndSnippetArrays = true
    clickAnalytics = true

    filters { and { facet("locale", "en-US") } }

    restrictSearchableAttributes {
      +"title"
      +"alternateTitles"
    }
  }

  val fullSnippetWithSearchableRestriction =
    LiveCase(
      dsl = { query(block = fullSnippetBlock) },
      body =
        """{"query":"office","hitsPerPage":10,"distinct":1,"getRankingInfo":true,"restrictHighlightAndSnippetArrays":true,"clickAnalytics":true,"filters":"locale:en-US","restrictSearchableAttributes":["title","alternateTitles"]}""",
      expect = listOf(Expect.Hits(setOf("1"))),
    )

  private fun restrictSearchable(extraFields: List<String>): SearchParamsObject = query {
    query = "office"
    restrictSearchableAttributes {
      +"title"
      +"tags"
      extraFields.forEach { +it }
    }
  }

  val restrictSearchableAttributesWithForEach =
    LiveCase(
      dsl = { restrictSearchable(listOf("alternateTitles")) },
      body =
        """{"query":"office","restrictSearchableAttributes":["title","tags","alternateTitles"]}""",
      expect = listOf(Expect.Hits(setOf("1", "2", "3"))),
    )

  val restrictSearchableAttributesWithEmptyForEach =
    LiveCase(
      dsl = { restrictSearchable(emptyList()) },
      body = """{"query":"office","restrictSearchableAttributes":["title","tags"]}""",
      expect = listOf(Expect.Hits(setOf("1", "3"))),
    )

  val attributesToHighlight =
    LiveCase(
      dsl = {
        query {
          query = "office"
          attributesToHighlight { +"title" }
        }
      },
      body = """{"query":"office","attributesToHighlight":["title"]}""",
      expect = listOf(Expect.Hits(setOf("1", "2", "3", "4")), Expect.HighlightKeys(setOf("title"))),
    )

  val attributesToRetrieveWithForEach =
    LiveCase(
      dsl = {
        val mapperRequiredFields = listOf("locale")
        query {
          attributesToRetrieve {
            +"objectID"
            +"title"
            mapperRequiredFields.forEach { +it }
          }
        }
      },
      body = """{"attributesToRetrieve":["objectID","title","locale"]}""",
      expect = listOf(Expect.HitCount(5), Expect.HitKeys(setOf("objectID", "title", "locale"))),
    )

  val emptyListBlockSendsEmptyList =
    LiveCase(
      dsl = { query { attributesToRetrieve {} } },
      body = """{"attributesToRetrieve":[]}""",
      expect = listOf(Expect.HitCount(5), Expect.HitKeys(setOf("objectID"))),
    )

  private fun stopWordsQuery(secondary: SupportedLanguage?): SearchParamsObject = query {
    query = "the office"
    removeStopWords = RemoveStopWords.of(true)
    queryLanguages {
      +SupportedLanguage.En
      secondary?.let { +it }
    }
  }

  val queryLanguagesWithoutSecondary =
    LiveCase(
      dsl = { stopWordsQuery(null) },
      body = """{"query":"the office","removeStopWords":true,"queryLanguages":["en"]}""",
      expect = listOf(Expect.Hits(setOf("1", "2", "3", "4"))),
    )

  val queryLanguagesFrenchOnly =
    LiveCase(
      dsl = {
        query {
          query = "the office"
          removeStopWords = RemoveStopWords.of(true)
          queryLanguages { +SupportedLanguage.Fr }
        }
      },
      body = """{"query":"the office","removeStopWords":true,"queryLanguages":["fr"]}""",
      expect = listOf(Expect.Hits(setOf("2", "3"))),
    )

  val responseFields =
    LiveCase(
      dsl = {
        query {
          responseFields {
            +"hits"
            +"exhaustive"
            +"processingTimeMS"
            +"processingTimingsMS"
          }
        }
      },
      body =
        """{"responseFields":["hits","exhaustive","processingTimeMS","processingTimingsMS"]}""",
      expect =
        listOf(Expect.Keys(setOf("hits", "exhaustive", "processingTimeMS", "processingTimingsMS"))),
    )

  val ruleContexts =
    LiveCase(
      dsl = {
        query {
          ruleContexts {
            +"desktop"
            +"ab-variant-b"
          }
        }
      },
      body = """{"ruleContexts":["desktop","ab-variant-b"]}""",
      expect = listOf(Expect.UserData(jsonArray("""[{"ctx":"desktop"}]"""))),
    )
}
