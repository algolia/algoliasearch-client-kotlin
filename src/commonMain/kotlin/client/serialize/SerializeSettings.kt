package client.serialize

import client.response.Settings
import kotlinx.serialization.json.*


internal fun String.toSettings(): Settings {
    val json = JsonTreeParser.parse(this)
    val settings = Settings()

    json.forEach { (key, element) ->
        when (key) {
            SearchableAttributes -> settings.searchableAttributes = element.notNull { toAttributes() }
            AttributesForFaceting -> settings.attributesForFaceting = element.notNull { toAttributes() }
            UnretrievableAttributes -> settings.unretrievableAttributes = element.notNull { toAttributes() }
            AttributesToRetrieve -> settings.attributesToRetrieve = element.notNull { toAttributes() }
            Ranking -> settings.ranking = element.notNull { toRankings() }
            CustomRanking -> settings.customRanking = element.notNull { toCustomRankings() }
            Replicas -> settings.replicas = element.notNull { toIndexes() }
            MaxValuesPerFacet -> settings.maxValuesPerFacet = element.intOrNull
            FacetingAfterDistinct -> settings.facetingAfterDistinct = element.booleanOrNull
            HitsPerPage -> settings.hitsPerPage = element.intOrNull
            PaginationLimitedTo -> settings.paginationLimitedTo = element.intOrNull
            MinWordSizefor1Typo -> settings.minWordSizefor1Typo = element.intOrNull
            MinWordSizefor2Typos -> settings.minWordSizefor2Typos = element.intOrNull
            Distinct -> settings.distinct = element.intOrNull
            MinProximity -> settings.minProximity = element.intOrNull
            MaxFacetHits -> settings.maxFacetHits = element.intOrNull
            RestrictHighlightAndSnippetArrays -> settings.restrictHighlightAndSnippetArrays = element.booleanOrNull
            AllowTyposOnNumericTokens -> settings.allowTyposOnNumericTokens = element.booleanOrNull
            EnableRules -> settings.enableRules = element.booleanOrNull
            AdvancedSyntax -> settings.advancedSyntax = element.booleanOrNull
            AllowCompressionOfIntegerArray -> settings.allowCompressionOfIntegerArray = element.booleanOrNull
            Synonyms -> settings.synonyms = element.booleanOrNull
            ReplaceSynonymsInHighlight -> settings.replaceSynonymsInHighlight = element.booleanOrNull
            HighlightPreTag -> settings.highlightPreTag = element.contentOrNull
            HighlightPostTag -> settings.highlightPostTag = element.contentOrNull
            SnippetEllipsisText -> settings.snippetEllipsisText = element.contentOrNull
            SeparatorsToIndex -> settings.separatorsToIndex = element.contentOrNull
            KeepDiacriticsOnCharacters -> settings.keepDiacriticsOnCharacters = element.contentOrNull
            AttributesToHighlight -> settings.attributesToHighlight = element.notNull { toAttributes() }
            DisableTypoToleranceOnAttributes -> settings.disableTypoToleranceOnAttributes =
                element.notNull { toAttributes() }
            CamelCaseAttributes -> settings.camelCaseAttributes = element.notNull { toAttributes() }
            DisablePrefixOnAttributes -> settings.disablePrefixOnAttributes = element.notNull { toAttributes() }
            DisableExactOnAttributes -> settings.disableExactOnAttributes = element.notNull { toAttributes() }
            AttributesToSnippet -> settings.attributesToSnippet = element.notNull { toSnippets() }
            TypoTolerance -> settings.typoTolerance = element.notNull { toTypoTolerance() }
            IgnorePlurals -> settings.ignorePlurals = element.notNull { toBooleanOrQueryLanguages() }
        }
    }
    return settings
}

internal fun <T> JsonElement.notNull(block: JsonElement.() -> T): T? {
    return if (isNull) null else block(this)
}