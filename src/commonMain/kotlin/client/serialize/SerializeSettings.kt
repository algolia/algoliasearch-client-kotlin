package client.serialize

import client.response.Settings
import kotlinx.serialization.json.*


internal fun String.toSettings(): Settings {
    val json = JsonTreeParser.parse(this)
    val settings = Settings()

    json.forEach { (key, element) ->
        when (key) {
            searchableAttributes -> settings.searchableAttributes = element.notNull { toAttributes() }
            attributesForFaceting -> settings.attributesForFaceting = element.notNull { toAttributes() }
            unretrievableAttributes -> settings.unretrievableAttributes = element.notNull { toAttributes() }
            attributesToRetrieve -> settings.attributesToRetrieve = element.notNull { toAttributes() }
            ranking -> settings.ranking = element.notNull { toRankings() }
            customRanking -> settings.customRanking = element.notNull { toCustomRankings() }
            replicas -> settings.replicas = element.notNull { toIndexes() }
            maxValuesPerFacet -> settings.maxValuesPerFacet = element.intOrNull
            facetingAfterDistinct -> settings.facetingAfterDistinct = element.booleanOrNull
            hitsPerPage -> settings.hitsPerPage = element.intOrNull
            paginationLimitedTo -> settings.paginationLimitedTo = element.intOrNull
            minWordSizefor1Typo -> settings.minWordSizefor1Typo = element.intOrNull
            minWordSizefor2Typos -> settings.minWordSizefor2Typos = element.intOrNull
            distinct -> settings.distinct = element.intOrNull
            minProximity -> settings.minProximity = element.intOrNull
            maxFacetHits -> settings.maxFacetHits = element.intOrNull
            restrictHighlightAndSnippetArrays -> settings.restrictHighlightAndSnippetArrays = element.booleanOrNull
            allowTyposOnNumericTokens -> settings.allowTyposOnNumericTokens = element.booleanOrNull
            enableRules -> settings.enableRules = element.booleanOrNull
            advancedSyntax -> settings.advancedSyntax = element.booleanOrNull
            allowCompressionOfIntegerArray -> settings.allowCompressionOfIntegerArray = element.booleanOrNull
            synonyms -> settings.synonyms = element.booleanOrNull
            replaceSynonymsInHighlight -> settings.replaceSynonymsInHighlight = element.booleanOrNull
            highlightPreTag -> settings.highlightPreTag = element.contentOrNull
            highlightPostTag -> settings.highlightPostTag = element.contentOrNull
            snippetEllipsisText -> settings.snippetEllipsisText = element.contentOrNull
            separatorsToIndex -> settings.separatorsToIndex = element.contentOrNull
            keepDiacriticsOnCharacters -> settings.keepDiacriticsOnCharacters = element.contentOrNull
            attributesToHighlight -> settings.attributesToHighlight = element.notNull { toAttributes() }
            disableTypoToleranceOnAttributes -> settings.disableTypoToleranceOnAttributes =
                element.notNull { toAttributes() }
            camelCaseAttributes -> settings.camelCaseAttributes = element.notNull { toAttributes() }
            disablePrefixOnAttributes -> settings.disablePrefixOnAttributes = element.notNull { toAttributes() }
            disableExactOnAttributes -> settings.disableExactOnAttributes = element.notNull { toAttributes() }
        }
    }
    return settings
}

internal fun <T> JsonElement.notNull(block: JsonElement.() -> T): T? {
    return if (isNull) null else block(this)
}