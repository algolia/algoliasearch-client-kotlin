package serialize

import attributeA
import attributeAll
import attributeB
import attributes
import boolean
import client.data.BooleanOrQueryLanguages
import client.data.QueryLanguage
import client.data.Snippet
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
        testAttributes(KeySearchableAttributes, Settings(searchableAttributes = attributes))
    }

    @Test
    fun attributesForFaceting() {
        testAttributes(KeyAttributesForFaceting, Settings(attributesForFaceting = attributes))
    }

    @Test
    fun unretrievableAttributes() {
        testAttributes(KeyUnretrievableAttributes, Settings(unretrievableAttributes = attributes))
    }

    @Test
    fun attributesToRetrieve() {
        testAttributes(KeyAttributesToRetrieve, Settings(attributesToRetrieve = attributes))
    }

    @Test
    fun ranking() {
        val string = json {
            KeyRanking to jsonArray {
                +KeyGeo
                +KeyTypo
                +KeyWords
                +KeyFilters
                +KeyProximity
                +KeyAttribute
                +KeyExact
                +KeyCustom
                +"$KeyAsc($attributeA)"
                +"$KeyDesc($attributeB)"
                +unknown
            }
        }.toString()

        assertEquals(
            Settings(
                ranking = listOf(
                    client.data.Ranking.Geo,
                    client.data.Ranking.Typo,
                    client.data.Ranking.Words,
                    client.data.Ranking.Filters,
                    client.data.Ranking.Proximity,
                    client.data.Ranking.Attribute,
                    client.data.Ranking.Exact,
                    client.data.Ranking.Custom,
                    client.data.Ranking.Asc(attributeA),
                    client.data.Ranking.Desc(attributeB),
                    client.data.Ranking.Unknown(unknown)
                )
            ),
            string.toSettings()
        )
    }

    @Test
    fun customRanking() {
        val string = json {
            KeyCustomRanking to jsonArray {
                +"$KeyAsc($attributeA)"
                +"$KeyDesc($attributeB)"
                +unknown
            }
        }.toString()

        assertEquals(
            Settings(
                customRanking = listOf(
                    client.data.CustomRanking.Asc(attributeA),
                    client.data.CustomRanking.Desc(attributeB),
                    client.data.CustomRanking.Unknown(unknown)
                )
            ), string.toSettings()
        )
    }

    @Test
    fun replicas() {
        val string = json {
            KeyReplicas to jsonArray {
                +indexA.name
                +indexB.name
            }
        }.toString()

        assertEquals(Settings(replicas = listOf(indexA, indexB)), string.toSettings())
    }

    @Test
    fun maxValuesPerFacet() {
        testInt(KeyMaxValuesPerFacet, Settings(maxValuesPerFacet = int))
    }

    @Test
    fun facetingAfterDistinct() {
        testBoolean(KeyFacetingAfterDistinct, Settings(facetingAfterDistinct = boolean))
    }

    @Test
    fun hitsPerPage() {
        testInt(KeyHitsPerPage, Settings(hitsPerPage = int))
    }

    @Test
    fun paginationLimitedTo() {
        testInt(KeyPaginationLimitedTo, Settings(paginationLimitedTo = int))
    }

    @Test
    fun minWordSizefor1Typo() {
        testInt(KeyMinWordSizefor1Typo, Settings(minWordSizefor1Typo = int))
    }

    @Test
    fun minWordSizefor2Typos() {
        testInt(KeyMinWordSizefor2Typos, Settings(minWordSizefor2Typos = int))
    }

    @Test
    fun distinct() {
        testInt(KeyDistinct, Settings(distinct = int))
    }

    @Test
    fun minProximity() {
        testInt(KeyMinProximity, Settings(minProximity = int))
    }

    @Test
    fun maxFacetHits() {
        testInt(KeyMaxFacetHits, Settings(maxFacetHits = int))
    }

    @Test
    fun restrictHighlightAndSnippetArrays() {
        testBoolean(KeyRestrictHighlightAndSnippetArrays, Settings(restrictHighlightAndSnippetArrays = boolean))
    }

    @Test
    fun allowTyposOnNumericTokens() {
        testBoolean(KeyAllowTyposOnNumericTokens, Settings(allowTyposOnNumericTokens = boolean))
    }

    @Test
    fun enableRules() {
        testBoolean(KeyEnableRules, Settings(enableRules = boolean))
    }

    @Test
    fun advancedSyntax() {
        testBoolean(KeyAdvancedSyntax, Settings(advancedSyntax = boolean))
    }

    @Test
    fun allowCompressionOfIntegerArray() {
        testBoolean(KeyAllowCompressionOfIntegerArray, Settings(allowCompressionOfIntegerArray = boolean))
    }

    @Test
    fun synonyms() {
        testBoolean(KeySynonyms, Settings(synonyms = boolean))
    }

    @Test
    fun replaceSynonymsInHighlight() {
        testBoolean(KeyReplaceSynonymsInHighlight, Settings(replaceSynonymsInHighlight = boolean))
    }

    @Test
    fun highlightPreTag() {
        testString(KeyHighlightPreTag, Settings(highlightPreTag = string))
    }

    @Test
    fun highlightPostTag() {
        testString(KeyHighlightPostTag, Settings(highlightPostTag = string))
    }

    @Test
    fun snippetEllipsisText() {
        testString(KeySnippetEllipsisText, Settings(snippetEllipsisText = string))
    }

    @Test
    fun separatorsToIndex() {
        testString(KeySeparatorsToIndex, Settings(separatorsToIndex = string))
    }

    @Test
    fun keepDiacriticsOnCharacters() {
        testString(KeyKeepDiacriticsOnCharacters, Settings(keepDiacriticsOnCharacters = string))
    }

    @Test
    fun attributesToHighlight() {
        testAttributes(KeyAttributesToHighlight, Settings(attributesToHighlight = attributes))
    }

    @Test
    fun disableTypoToleranceOnAttributes() {
        testAttributes(KeyDisableTypoToleranceOnAttributes, Settings(disableTypoToleranceOnAttributes = attributes))
    }

    @Test
    fun camelCaseAttributes() {
        testAttributes(KeyCamelCaseAttributes, Settings(camelCaseAttributes = attributes))
    }

    @Test
    fun disablePrefixOnAttributes() {
        testAttributes(KeyDisablePrefixOnAttributes, Settings(disablePrefixOnAttributes = attributes))
    }

    @Test
    fun disableExactOnAttributes() {
        testAttributes(KeyDisableExactOnAttributes, Settings(disableExactOnAttributes = attributes))
    }

    @Test
    fun attributesToSnippet() {
        val string = json {
            KeyAttributesToSnippet to jsonArray {
                +attributeA.name
                +"*"
                +"*:20"
                +"$attributeB:10"
            }
        }.toString()

        assertEquals(
            Settings(
                attributesToSnippet = listOf(
                    Snippet(attributeA),
                    Snippet(attributeAll),
                    Snippet(attributeAll, 20),
                    Snippet(attributeB, 10)
                )
            ),
            string.toSettings()
        )
    }

    @Test
    fun typoTolerance() {
        val typoToleranceBoolean = json { KeyTypoTolerance to boolean }.toString()
        val typoToleranceStrict = json { KeyTypoTolerance to KeyStrict }.toString()
        val typoToleranceMin = json { KeyTypoTolerance to KeyMin }.toString()
        val typoToleranceUnknown = json { KeyTypoTolerance to unknown }.toString()
        val typoToleranceNull = json { KeyTypoTolerance to JsonNull }.toString()

        assertEquals(
            Settings(typoTolerance = client.data.TypoTolerance.Boolean(boolean)),
            typoToleranceBoolean.toSettings()
        )
        assertEquals(Settings(typoTolerance = client.data.TypoTolerance.Strict), typoToleranceStrict.toSettings())
        assertEquals(Settings(typoTolerance = client.data.TypoTolerance.Min), typoToleranceMin.toSettings())
        assertEquals(
            Settings(typoTolerance = client.data.TypoTolerance.Unknown(unknown)),
            typoToleranceUnknown.toSettings()
        )
        assertEquals(Settings(), typoToleranceNull.toSettings())
    }

    @Test
    fun ignorePlurals() {
        val ignorePluralsBoolean = json { KeyIgnorePlurals to boolean }.toString()
        val ignorePluralsLanguages = json {
            KeyIgnorePlurals to jsonArray {
                +KeyAfrikaans
                +KeyAlbanian
            }
        }.toString()

        assertEquals(
            Settings(ignorePlurals = BooleanOrQueryLanguages.Boolean(boolean)),
            ignorePluralsBoolean.toSettings()
        )
        assertEquals(
            Settings(
                ignorePlurals = BooleanOrQueryLanguages.QueryLanguages(
                    listOf(
                        QueryLanguage.Afrikaans,
                        QueryLanguage.Albanian
                    )
                )
            ),
            ignorePluralsLanguages.toSettings()
        )
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