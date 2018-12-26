package serialize

import attributeA
import attributeB
import attributes
import boolean
import client.data.CustomRanking
import client.data.Ranking
import client.response.Settings
import client.serialize.*
import indexA
import indexB
import int
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import string
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestDeserializeSettings {

    private val jsonAttributes = jsonArray {
        +attributeA.name
        +attributeB.name
    }

    @Test
    fun searchableAttributes() {
        testAttributes(searchableAttributes, Settings(searchableAttributes = attributes))
    }

    @Test
    fun attributesForFaceting() {
        testAttributes(attributesForFaceting, Settings(attributesForFaceting = attributes))
    }

    @Test
    fun unretrievableAttributes() {
        testAttributes(unretrievableAttributes, Settings(unretrievableAttributes = attributes))
    }

    @Test
    fun attributesToRetrieve() {
        testAttributes(attributesToRetrieve, Settings(attributesToRetrieve = attributes))
    }

    @Test
    fun ranking() {
        val string = json {
            ranking to jsonArray {
                +geo
                +typo
                +words
                +filters
                +proximity
                +attribute
                +exact
                +custom
                +"$asc(${attributeA.name})"
                +"$desc(${attributeB.name})"
                +unknown
            }
        }.toString()

        assertEquals(
            Settings(
                ranking = listOf(
                    Ranking.Geo,
                    Ranking.Typo,
                    Ranking.Words,
                    Ranking.Filters,
                    Ranking.Proximity,
                    Ranking.Attribute,
                    Ranking.Exact,
                    Ranking.Custom,
                    Ranking.Asc(attributeA),
                    Ranking.Desc(attributeB),
                    Ranking.Unknown(unknown)
                )
            ),
            string.toSettings()
        )
    }

    @Test
    fun customRanking() {
        val string = json {
            customRanking to jsonArray {
                +"$asc(${attributeA.name})"
                +"$desc(${attributeB.name})"
                +unknown
            }
        }.toString()

        assertEquals(
            Settings(
                customRanking = listOf(
                    CustomRanking.Asc(attributeA),
                    CustomRanking.Desc(attributeB),
                    CustomRanking.Unknown(unknown)
                )
            ), string.toSettings()
        )
    }

    @Test
    fun replicas() {
        val string = json {
            replicas to jsonArray {
                +indexA.name
                +indexB.name
            }
        }.toString()

        assertEquals(Settings(replicas = listOf(indexA, indexB)), string.toSettings())
    }

    @Test
    fun maxValuesPerFacet() {
        testInt(maxValuesPerFacet, Settings(maxValuesPerFacet = int))
    }

    @Test
    fun facetingAfterDistinct() {
        testBoolean(facetingAfterDistinct, Settings(facetingAfterDistinct = boolean))
    }

    @Test
    fun hitsPerPage() {
        testInt(hitsPerPage, Settings(hitsPerPage = int))
    }

    @Test
    fun paginationLimitedTo() {
        testInt(paginationLimitedTo, Settings(paginationLimitedTo = int))
    }

    @Test
    fun minWordSizefor1Typo() {
        testInt(minWordSizefor1Typo, Settings(minWordSizefor1Typo = int))
    }

    @Test
    fun minWordSizefor2Typos() {
        testInt(minWordSizefor2Typos, Settings(minWordSizefor2Typos = int))
    }

    @Test
    fun distinct() {
        testInt(distinct, Settings(distinct = int))
    }

    @Test
    fun minProximity() {
        testInt(minProximity, Settings(minProximity = int))
    }

    @Test
    fun maxFacetHits() {
        testInt(maxFacetHits, Settings(maxFacetHits = int))
    }

    @Test
    fun restrictHighlightAndSnippetArrays() {
        testBoolean(restrictHighlightAndSnippetArrays, Settings(restrictHighlightAndSnippetArrays = boolean))
    }

    @Test
    fun allowTyposOnNumericTokens() {
        testBoolean(allowTyposOnNumericTokens, Settings(allowTyposOnNumericTokens = boolean))
    }

    @Test
    fun enableRules() {
        testBoolean(enableRules, Settings(enableRules = boolean))
    }

    @Test
    fun advancedSyntax() {
        testBoolean(advancedSyntax, Settings(advancedSyntax = boolean))
    }

    @Test
    fun allowCompressionOfIntegerArray() {
        testBoolean(allowCompressionOfIntegerArray, Settings(allowCompressionOfIntegerArray = boolean))
    }

    @Test
    fun synonyms() {
        testBoolean(synonyms, Settings(synonyms = boolean))
    }

    @Test
    fun replaceSynonymsInHighlight() {
        testBoolean(replaceSynonymsInHighlight, Settings(replaceSynonymsInHighlight = boolean))
    }

    @Test
    fun highlightPreTag() {
        testString(highlightPreTag, Settings(highlightPreTag = string))
    }

    @Test
    fun highlightPostTag() {
        testString(highlightPostTag, Settings(highlightPostTag = string))
    }

    @Test
    fun snippetEllipsisText() {
        testString(snippetEllipsisText, Settings(snippetEllipsisText = string))
    }

    @Test
    fun separatorsToIndex() {
        testString(separatorsToIndex, Settings(separatorsToIndex = string))
    }

    @Test
    fun keepDiacriticsOnCharacters() {
        testString(keepDiacriticsOnCharacters, Settings(keepDiacriticsOnCharacters = string))
    }

    @Test
    fun attributesToHighlight() {
        testAttributes(attributesToHighlight, Settings(attributesToHighlight = attributes))
    }

    @Test
    fun disableTypoToleranceOnAttributes() {
        testAttributes(disableTypoToleranceOnAttributes, Settings(disableTypoToleranceOnAttributes = attributes))
    }

    @Test
    fun camelCaseAttributes() {
        testAttributes(camelCaseAttributes, Settings(camelCaseAttributes = attributes))
    }

    @Test
    fun disablePrefixOnAttributes() {
        testAttributes(disablePrefixOnAttributes, Settings(disablePrefixOnAttributes = attributes))
    }

    @Test
    fun disableExactOnAttributes() {
        testAttributes(disableExactOnAttributes, Settings(disableExactOnAttributes = attributes))
    }

    private fun testInt(key: String, settings: Settings) {
        val string = json { key to int }.toString()

        testNull(key)
        assertEquals(settings, string.toSettings())
    }

    private fun testBoolean(key: String, settings: Settings) {
        val string = json { key to boolean }.toString()

        testNull(key)
        assertEquals(settings, string.toSettings())
    }

    private fun testAttributes(key: String, settings: Settings) {
        val string = json { key to jsonAttributes }.toString()

        testNull(key)
        assertEquals(settings, string.toSettings())
    }

    private fun testString(key: String, settings: Settings) {
        val string = json { key to string }.toString()

        testNull(key)
        assertEquals(settings, string.toSettings())
    }

    private fun testNull(key: String) {
        val string = json { key to JsonNull }.toString()

        assertEquals(Settings(), string.toSettings())
    }
}