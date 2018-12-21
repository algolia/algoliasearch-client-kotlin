package client.serialize

import client.data.AroundRadius
import client.data.BooleanOrQueryLanguages
import client.data.TypoTolerance
import client.query.Query
import client.query.helper.names
import io.ktor.http.Parameters
import io.ktor.http.formUrlEncode
import kotlinx.serialization.json.*

internal fun Map<String, Any>.serialize(): JsonObject {
    return json {
        entries.forEach { entry ->
            val value = entry.value
            when (value) {
                is String -> entry.key to value
                is Number -> entry.key to value
                is Boolean -> entry.key to value
                is JsonElement -> entry.key to value
                else -> throw Exception("Unsupported serialization type.")
            }
        }
    }
}

internal fun Map<String, Any>.urlEncode(): String {
    return Parameters.build {
        entries.forEach { entry ->
            val value = entry.value
            when (value) {
                is String -> append(entry.key, value)
                is Number -> append(entry.key, value.toString())
                is Boolean -> append(entry.key, value.toString())
                is JsonArray -> appendAll(entry.key, value.content.map { it.content })
                else -> throw Exception("Unsupported serialization type.")
            }
        }
    }.formUrlEncode()
}

internal fun List<Float>.toJsonArrayFromFloat() = jsonArray { forEach { (it as Number).unaryPlus() } }
internal fun List<String>.toJsonArrayFromString() = jsonArray { forEach { +it } }
internal fun List<List<String>>.toJsonArrayFromList() = jsonArray { forEach { +jsonArray { it.forEach { +it } } } }

internal fun BooleanOrQueryLanguages.toPrimitive(): Any = when (this) {
    is BooleanOrQueryLanguages.Boolean -> boolean
    is BooleanOrQueryLanguages.QueryLanguages -> queryLanguages.map { it.raw }.toJsonArrayFromString()
}

internal fun Query.toMap(): MutableMap<String, Any> {
    val map = mutableMapOf<String, Any>()

    // Query
    query?.let { map["query"] = it }
    //Attributes
    attributesToRetrieve?.let { map["attributesToRetrieve"] = it.names.toJsonArrayFromString() }
    restrictSearchableAttributes?.let { map["restrictSearchableAttributes"] = it.names.toJsonArrayFromString() }
    // Filters
    filters?.let { map["filters"] = it }
    facetFilters?.let { map["facetFilters"] = it.toJsonArrayFromList() }
    optionalFilters?.let { map["optionalFilters"] = it.toJsonArrayFromList() }
    numericFilters?.let { map["numericFilters"] = it.toJsonArrayFromList() }
    tagFilters?.let { map["tagFilters"] = it.toJsonArrayFromList() }
    sumOrFiltersScores?.let { map["sumOrFiltersScores"] = it }
    // Facets
    facets?.let { map["facets"] = it.names.toJsonArrayFromString() }
    maxValuesPerFacet?.let { map["maxValuesPerFacet"] = it }
    facetingAfterDistinct?.let { map["facetingAfterDistinct"] = it }
    sortFacetValuesBy?.let { map["sortFacetValuesBy"] = it.raw }
    // Highlighting
    attributesToHighlight?.let { map["attributesToHighlight"] = it.names.toJsonArrayFromString() }
    attributesToSnippet?.let { map["attributesToSnippet"] = it.toJsonArrayFromString() }
    highlightPreTag?.let { map["highlightPreTag"] = it }
    highlightPostTag?.let { map["highlightPostTag"] = it }
    snippetEllipsisText?.let { map["snippetEllipsisText"] = it }
    restrictHighlightAndSnippetArrays?.let { map["restrictHighlightAndSnippetArrays"] = it }
    // Pagination
    page?.let { map["page"] = it }
    hitsPerPage?.let { map["hitsPerPage"] = it }
    offset?.let { map["offset"] = it }
    length?.let { map["length"] = it }
    //Typos
    minWordSizefor1Typo?.let { map["minWordSizefor1Typo"] = it }
    minWordSizefor2Typos?.let { map["minWordSizefor2Typos"] = it }
    typoTolerance?.let {
        map["typoTolerance"] = when (it) {
            is TypoTolerance.Boolean -> it.boolean
            is TypoTolerance.Min -> it.raw
            is TypoTolerance.Strict -> it.raw
            is TypoTolerance.Unknown -> it.raw
        }
    }
    allowTyposOnNumericTokens?.let { map["allowTyposOnNumericTokens"] = it }
    disableTypoToleranceOnAttributes?.let { map["disableTypoToleranceOnAttributes"] = it.names.toJsonArrayFromString() }
    // Geo-Search
    aroundLatLng?.let { map["aroundLatLng"] = it }
    aroundLatLngViaIP?.let { map["aroundLatLngViaIP"] = it }
    aroundRadius?.let {
        map["aroundRadius"] = when (it) {
            is AroundRadius.All -> it.raw
            is AroundRadius.InMeters -> it.int
            is AroundRadius.Unknown -> it.raw
        }
    }
    aroundPrecision?.let { map["aroundPrecision"] = it }
    minimumAroundRadius?.let { map["minimumAroundRadius"] = it }
    insideBoundingBox?.let { map["insideBoundingBox"] = it.flatMap { it.floats }.toJsonArrayFromFloat() }
    insidePolygon?.let { map["insidePolygon"] = it.toJsonArrayFromFloat() }
    // Languages
    ignorePlurals?.let { map["ignorePlurals"] = it.toPrimitive() }
    removeStopWords?.let { map["removeStopWords"] = it.toPrimitive() }
    queryLanguages?.let { map["queryLanguages"] = it.map { it.raw }.toJsonArrayFromString() }
    // Query-rules
    enableRules?.let { map["enableRules"] = it }
    ruleContexts?.let { map["ruleContexts"] = it.toJsonArrayFromString() }
    // Query-strategy
    queryType?.let { map["queryType"] = it.raw }
    removeWordsIfNoResults?.let { map["removeWordsIfNoResults"] = it.raw }
    advancedSyntax?.let { map["advancedSyntax"] = it }
    optionalWords?.let { map["optionalWords"] = it.toJsonArrayFromString() }
    disableExactOnAttributes?.let { map["disableExactOnAttributes"] = it.names.toJsonArrayFromString() }
    exactOnSingleWordQuery?.let { map["exactOnSingleWordQuery"] = it.raw }
    alternativesAsExact?.let { map["alternativesAsExact"] = it.map { it.raw }.toJsonArrayFromString() }
    // Advanced
    distinct?.let { map["distinct"] = it }
    getRankingInfo?.let { map["getRankingInfo"] = it }
    clickAnalytics?.let { map["clickAnalytics"] = it }
    analytics?.let { map["analytics"] = it }
    analyticsTags?.let { map["analyticsTags"] = it.toJsonArrayFromString() }
    synonyms?.let { map["synonyms"] = it }
    replaceSynonymsInHighlight?.let { map["replaceSynonymsInHighlight"] = it }
    minProximity?.let { map["minProximity"] = it }
    responseFields?.let { map["responseFields"] = it.map { it.raw }.toJsonArrayFromString() }
    maxFacetHits?.let { map["maxFacetHits"] = it }
    percentileComputation?.let { map["percentileComputation"] = it }
    return map
}