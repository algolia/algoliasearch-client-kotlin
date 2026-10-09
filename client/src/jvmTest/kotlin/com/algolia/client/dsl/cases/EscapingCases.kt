@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.cases

import com.algolia.client.dsl.*
import com.algolia.client.dsl.filter.NumericOperator
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

internal object EscapingCases {

  val parenthesesQuoted = facetCase("Books(Kids)", """v:"Books(Kids)"""", "1")

  val colonQuoted = facetCase("a:b", """v:"a:b"""", "2")

  val lessThanQuoted = facetCase("a<b", """v:"a<b"""", "3")

  val equalsQuoted = facetCase("a=b", """v:"a=b"""", "6")

  val exclamationQuoted = facetCase("a!b", """v:"a!b"""", "7")

  val greaterThanQuoted = facetCase("a>b", """v:"a>b"""", "8")

  val embeddedQuoteEscaped = facetCase("q\"uote", """v:"q\"uote"""", "11")

  val innerBackslashEscaped = facetCase("back\\slash", """v:"back\\slash"""", "5")

  val trailingBackslashEscaped = facetCase("trail\\", """v:"trail\\"""", "9")

  val backslashBeforeSpaceEscaped = facetCase("C:\\ dir\\", """v:"C:\\ dir\\"""", "4")

  val dottedValueBare = facetCase("a.b", "v:a.b", "10")

  val whitespaceValueQuoted = facetCase("John Doe", """v:"John Doe"""", "12")

  val keywordValuesQuoted =
    LiveCase(
      dsl = {
        query {
          filters {
            orFacet {
              facet("v", "foo AND bar")
              facet("v", "foo OR bar")
              facet("v", "NOT bar")
            }
          }
        }
      },
      body = filtersBody("""(v:"foo AND bar" OR v:"foo OR bar" OR v:"NOT bar")"""),
      expect = listOf(Expect.Hits(setOf("13", "14", "15"))),
    )

  val toKeywordQuoted = facetCase("TO", """v:"TO"""", "16")

  val nonAsciiQuoted = facetCase("café", """v:"café"""", "17")

  val safeCharactersBare = facetCase("a.b-c_1", "v:a.b-c_1", "18")

  val tagWithQuotesEscaped =
    LiveCase(
      dsl = { query { filters { tag("45\"-50\" tv's") } } },
      body = filtersBody("""_tags:"45\"-50\" tv's""""),
      expect = listOf(Expect.Hits(setOf("19"))),
    )

  val attributeWithColonQuoted =
    LiveCase(
      dsl = { query { filters { facet("my:attr", "z") } } },
      body = filtersBody(""""my:attr":z"""),
      expect = listOf(Expect.Hits(setOf("10"))),
    )

  val attributeWithSpaceQuoted =
    LiveCase(
      dsl = { query { filters { facet("my attr", "red") } } },
      body = filtersBody(""""my attr":red"""),
      expect = listOf(Expect.Hits(setOf("20"))),
    )

  val numericAttributesQuoted =
    LiveCase(
      dsl = {
        query {
          filters {
            comparison("my num", NumericOperator.Equals, 15)
            comparison("a:b", NumericOperator.Greater, 1)
            range("a(b)", 0..1)
          }
        }
      },
      body = filtersBody(""""my num" = 15 AND "a:b" > 1 AND "a(b)":0 TO 1"""),
      expect = listOf(Expect.Hits(setOf("20"))),
    )

  val tagsQuoted =
    LiveCase(
      dsl = {
        query {
          filters {
            orTag {
              tag("a(b)")
              tag("x:y")
            }
          }
        }
      },
      body = filtersBody("""(_tags:"a(b)" OR _tags:"x:y")"""),
      expect = listOf(Expect.Hits(setOf("1", "2"))),
    )

  val deleteByQuotedValues =
    DeleteCase(
      dsl = {
        deleteBy {
          filters {
            orFacet {
              facet("v", "Books(Kids)")
              facet("v", "trail\\")
            }
          }
        }
      },
      body = filtersBody("""(v:"Books(Kids)" OR v:"trail\\")"""),
      remaining =
        setOf(
          "2",
          "3",
          "4",
          "5",
          "6",
          "7",
          "8",
          "10",
          "11",
          "12",
          "13",
          "14",
          "15",
          "16",
          "17",
          "18",
          "19",
          "20",
        ),
    )

  private fun facetCase(value: String, sql: String, objectID: String): LiveCase =
    LiveCase(
      dsl = { query { filters { facet("v", value) } } },
      body = filtersBody(sql),
      expect = listOf(Expect.Hits(setOf(objectID))),
    )

  private fun filtersBody(sql: String): String = buildJsonObject { put("filters", sql) }.toString()
}
