package client.serialize

import client.data.AlternativesAsExact
import client.response.Settings
import kotlinx.serialization.json.*


internal fun String.toSettings(): Settings {
    val json = JsonTreeParser.parse(this)
    val settings = Settings()

    json.forEach { (key, element) ->
        when (key) {
            KeySearchableAttributes -> settings.searchableAttributes = element.notNull { toAttributes() }
            KeyAttributesForFaceting -> settings.attributesForFaceting = element.notNull { toAttributes() }
            KeyUnretrievableAttributes -> settings.unretrievableAttributes = element.notNull { toAttributes() }
            KeyAttributesToRetrieve -> settings.attributesToRetrieve = element.notNull { toAttributes() }
            KeyRanking -> settings.ranking = element.notNull { toRankings() }
            KeyCustomRanking -> settings.customRanking = element.notNull { toCustomRankings() }
            KeyReplicas -> settings.replicas = element.notNull { toIndexes() }
            KeyMaxValuesPerFacet -> settings.maxValuesPerFacet = element.intOrNull
            KeyFacetingAfterDistinct -> settings.facetingAfterDistinct = element.booleanOrNull
            KeyHitsPerPage -> settings.hitsPerPage = element.intOrNull
            KeyPaginationLimitedTo -> settings.paginationLimitedTo = element.intOrNull
            KeyMinWordSizefor1Typo -> settings.minWordSizefor1Typo = element.intOrNull
            KeyMinWordSizefor2Typos -> settings.minWordSizefor2Typos = element.intOrNull
            KeyDistinct -> settings.distinct = element.intOrNull
            KeyMinProximity -> settings.minProximity = element.intOrNull
            KeyMaxFacetHits -> settings.maxFacetHits = element.intOrNull
            KeyRestrictHighlightAndSnippetArrays -> settings.restrictHighlightAndSnippetArrays = element.booleanOrNull
            KeyAllowTyposOnNumericTokens -> settings.allowTyposOnNumericTokens = element.booleanOrNull
            KeyEnableRules -> settings.enableRules = element.booleanOrNull
            KeyAdvancedSyntax -> settings.advancedSyntax = element.booleanOrNull
            KeyAllowCompressionOfIntegerArray -> settings.allowCompressionOfIntegerArray = element.booleanOrNull
            KeySynonyms -> settings.synonyms = element.booleanOrNull
            KeyReplaceSynonymsInHighlight -> settings.replaceSynonymsInHighlight = element.booleanOrNull
            KeyHighlightPreTag -> settings.highlightPreTag = element.contentOrNull
            KeyHighlightPostTag -> settings.highlightPostTag = element.contentOrNull
            KeySnippetEllipsisText -> settings.snippetEllipsisText = element.contentOrNull
            KeySeparatorsToIndex -> settings.separatorsToIndex = element.contentOrNull
            KeyKeepDiacriticsOnCharacters -> settings.keepDiacriticsOnCharacters = element.contentOrNull
            KeyAttributesToHighlight -> settings.attributesToHighlight = element.notNull { toAttributes() }
            KeyDisableTypoToleranceOnAttributes -> settings.disableTypoToleranceOnAttributes =
                element.notNull { toAttributes() }
            KeyCamelCaseAttributes -> settings.camelCaseAttributes = element.notNull { toAttributes() }
            KeyDisablePrefixOnAttributes -> settings.disablePrefixOnAttributes = element.notNull { toAttributes() }
            KeyDisableExactOnAttributes -> settings.disableExactOnAttributes = element.notNull { toAttributes() }
            KeyAttributesToSnippet -> settings.attributesToSnippet = element.notNull { toSnippets() }
            KeyTypoTolerance -> settings.typoTolerance = element.notNull { toTypoTolerance() }
            KeyIgnorePlurals -> settings.ignorePlurals = element.notNull { toBooleanOrQueryLanguages() }
            KeyAlternativesAsExact -> settings.alternativesAsExact = AlternativesAsExact.deserialize(element)
        }
    }
    return settings
}

internal fun <T> JsonElement.notNull(block: JsonElement.() -> T): T? {
    return if (isNull) null else block(this)
}