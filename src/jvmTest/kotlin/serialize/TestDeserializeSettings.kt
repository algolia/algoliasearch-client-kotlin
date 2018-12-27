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
        testAttributes(SearchableAttributes, Settings(searchableAttributes = attributes))
    }

    @Test
    fun attributesForFaceting() {
        testAttributes(AttributesForFaceting, Settings(attributesForFaceting = attributes))
    }

    @Test
    fun unretrievableAttributes() {
        testAttributes(UnretrievableAttributes, Settings(unretrievableAttributes = attributes))
    }

    @Test
    fun attributesToRetrieve() {
        testAttributes(AttributesToRetrieve, Settings(attributesToRetrieve = attributes))
    }

    @Test
    fun ranking() {
        val string = json {
            Ranking to jsonArray {
                +Geo
                +Typo
                +Words
                +Filters
                +Proximity
                +Attribute
                +Exact
                +Custom
                +"$Asc(${attributeA.name})"
                +"$Desc(${attributeB.name})"
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
            CustomRanking to jsonArray {
                +"$Asc(${attributeA.name})"
                +"$Desc(${attributeB.name})"
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
            Replicas to jsonArray {
                +indexA.name
                +indexB.name
            }
        }.toString()

        assertEquals(Settings(replicas = listOf(indexA, indexB)), string.toSettings())
    }

    @Test
    fun maxValuesPerFacet() {
        testInt(MaxValuesPerFacet, Settings(maxValuesPerFacet = int))
    }

    @Test
    fun facetingAfterDistinct() {
        testBoolean(FacetingAfterDistinct, Settings(facetingAfterDistinct = boolean))
    }

    @Test
    fun hitsPerPage() {
        testInt(HitsPerPage, Settings(hitsPerPage = int))
    }

    @Test
    fun paginationLimitedTo() {
        testInt(PaginationLimitedTo, Settings(paginationLimitedTo = int))
    }

    @Test
    fun minWordSizefor1Typo() {
        testInt(MinWordSizefor1Typo, Settings(minWordSizefor1Typo = int))
    }

    @Test
    fun minWordSizefor2Typos() {
        testInt(MinWordSizefor2Typos, Settings(minWordSizefor2Typos = int))
    }

    @Test
    fun distinct() {
        testInt(Distinct, Settings(distinct = int))
    }

    @Test
    fun minProximity() {
        testInt(MinProximity, Settings(minProximity = int))
    }

    @Test
    fun maxFacetHits() {
        testInt(MaxFacetHits, Settings(maxFacetHits = int))
    }

    @Test
    fun restrictHighlightAndSnippetArrays() {
        testBoolean(RestrictHighlightAndSnippetArrays, Settings(restrictHighlightAndSnippetArrays = boolean))
    }

    @Test
    fun allowTyposOnNumericTokens() {
        testBoolean(AllowTyposOnNumericTokens, Settings(allowTyposOnNumericTokens = boolean))
    }

    @Test
    fun enableRules() {
        testBoolean(EnableRules, Settings(enableRules = boolean))
    }

    @Test
    fun advancedSyntax() {
        testBoolean(AdvancedSyntax, Settings(advancedSyntax = boolean))
    }

    @Test
    fun allowCompressionOfIntegerArray() {
        testBoolean(AllowCompressionOfIntegerArray, Settings(allowCompressionOfIntegerArray = boolean))
    }

    @Test
    fun synonyms() {
        testBoolean(Synonyms, Settings(synonyms = boolean))
    }

    @Test
    fun replaceSynonymsInHighlight() {
        testBoolean(ReplaceSynonymsInHighlight, Settings(replaceSynonymsInHighlight = boolean))
    }

    @Test
    fun highlightPreTag() {
        testString(HighlightPreTag, Settings(highlightPreTag = string))
    }

    @Test
    fun highlightPostTag() {
        testString(HighlightPostTag, Settings(highlightPostTag = string))
    }

    @Test
    fun snippetEllipsisText() {
        testString(SnippetEllipsisText, Settings(snippetEllipsisText = string))
    }

    @Test
    fun separatorsToIndex() {
        testString(SeparatorsToIndex, Settings(separatorsToIndex = string))
    }

    @Test
    fun keepDiacriticsOnCharacters() {
        testString(KeepDiacriticsOnCharacters, Settings(keepDiacriticsOnCharacters = string))
    }

    @Test
    fun attributesToHighlight() {
        testAttributes(AttributesToHighlight, Settings(attributesToHighlight = attributes))
    }

    @Test
    fun disableTypoToleranceOnAttributes() {
        testAttributes(DisableTypoToleranceOnAttributes, Settings(disableTypoToleranceOnAttributes = attributes))
    }

    @Test
    fun camelCaseAttributes() {
        testAttributes(CamelCaseAttributes, Settings(camelCaseAttributes = attributes))
    }

    @Test
    fun disablePrefixOnAttributes() {
        testAttributes(DisablePrefixOnAttributes, Settings(disablePrefixOnAttributes = attributes))
    }

    @Test
    fun disableExactOnAttributes() {
        testAttributes(DisableExactOnAttributes, Settings(disableExactOnAttributes = attributes))
    }

    @Test
    fun attributesToSnippet() {
        val string = json {
            AttributesToSnippet to jsonArray {
                +attributeA.name
                +"*"
                +"*:20"
                +"${attributeB.name}:10"
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
        val typoToleranceBoolean = json { TypoTolerance to boolean }.toString()
        val typoToleranceStrict = json { TypoTolerance to Strict }.toString()
        val typoToleranceMin = json { TypoTolerance to Min }.toString()
        val typoToleranceUnknown = json { TypoTolerance to unknown }.toString()
        val typoToleranceNull = json { TypoTolerance to JsonNull }.toString()

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
        val ignorePluralsBoolean = json { IgnorePlurals to boolean }.toString()
        val ignorePluralsLanguages = json {
            IgnorePlurals to jsonArray {
                +Afrikaans
                +Albanian
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