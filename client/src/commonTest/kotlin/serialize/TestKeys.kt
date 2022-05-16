package serialize

import com.algolia.search.serialize.internal.KeyABTest
import com.algolia.search.serialize.internal.KeyABTestID
import com.algolia.search.serialize.internal.KeyABTests
import com.algolia.search.serialize.internal.KeyAbTestVariantID
import com.algolia.search.serialize.internal.KeyAcl
import com.algolia.search.serialize.internal.KeyAction
import com.algolia.search.serialize.internal.KeyActive
import com.algolia.search.serialize.internal.KeyAdd
import com.algolia.search.serialize.internal.KeyAddObject
import com.algolia.search.serialize.internal.KeyAddUnique
import com.algolia.search.serialize.internal.KeyAddress
import com.algolia.search.serialize.internal.KeyAdmin_Level
import com.algolia.search.serialize.internal.KeyAdministrative
import com.algolia.search.serialize.internal.KeyAdvancedSyntax
import com.algolia.search.serialize.internal.KeyAdvancedSyntaxFeatures
import com.algolia.search.serialize.internal.KeyAirport
import com.algolia.search.serialize.internal.KeyAlgoliaAPIKey
import com.algolia.search.serialize.internal.KeyAlgoliaApplicationID
import com.algolia.search.serialize.internal.KeyAlgoliaUserID
import com.algolia.search.serialize.internal.KeyAll
import com.algolia.search.serialize.internal.KeyAllOptional
import com.algolia.search.serialize.internal.KeyAllowCompressionOfIntegerArray
import com.algolia.search.serialize.internal.KeyAllowTyposOnNumericTokens
import com.algolia.search.serialize.internal.KeyAlpha
import com.algolia.search.serialize.internal.KeyAltcorrection
import com.algolia.search.serialize.internal.KeyAlternativeCorrection1
import com.algolia.search.serialize.internal.KeyAlternativeCorrection2
import com.algolia.search.serialize.internal.KeyAlternatives
import com.algolia.search.serialize.internal.KeyAlternativesAsExact
import com.algolia.search.serialize.internal.KeyAnalytics
import com.algolia.search.serialize.internal.KeyAnalyticsTags
import com.algolia.search.serialize.internal.KeyAnchoring
import com.algolia.search.serialize.internal.KeyAnswer
import com.algolia.search.serialize.internal.KeyAnswer_Code
import com.algolia.search.serialize.internal.KeyAroundLatLng
import com.algolia.search.serialize.internal.KeyAroundLatLngViaIP
import com.algolia.search.serialize.internal.KeyAroundPrecision
import com.algolia.search.serialize.internal.KeyAroundRadius
import com.algolia.search.serialize.internal.KeyAsc
import com.algolia.search.serialize.internal.KeyAttribute
import com.algolia.search.serialize.internal.KeyAttributeForDistinct
import com.algolia.search.serialize.internal.KeyAttributesForFaceting
import com.algolia.search.serialize.internal.KeyAttributesToHighlight
import com.algolia.search.serialize.internal.KeyAttributesToIndex
import com.algolia.search.serialize.internal.KeyAttributesToRetrieve
import com.algolia.search.serialize.internal.KeyAttributesToSnippet
import com.algolia.search.serialize.internal.KeyAutomaticFacetFilters
import com.algolia.search.serialize.internal.KeyAutomaticOptionalFacetFilters
import com.algolia.search.serialize.internal.KeyAutomaticRadius
import com.algolia.search.serialize.internal.KeyAvg
import com.algolia.search.serialize.internal.KeyBody
import com.algolia.search.serialize.internal.KeyBrowse
import com.algolia.search.serialize.internal.KeyBusStop
import com.algolia.search.serialize.internal.KeyCamelCaseAttributes
import com.algolia.search.serialize.internal.KeyCity
import com.algolia.search.serialize.internal.KeyClear
import com.algolia.search.serialize.internal.KeyClearExistingRules
import com.algolia.search.serialize.internal.KeyClick
import com.algolia.search.serialize.internal.KeyClickAnalytics
import com.algolia.search.serialize.internal.KeyCluster
import com.algolia.search.serialize.internal.KeyClusterName
import com.algolia.search.serialize.internal.KeyClusters
import com.algolia.search.serialize.internal.KeyCompound
import com.algolia.search.serialize.internal.KeyConcat
import com.algolia.search.serialize.internal.KeyCondition
import com.algolia.search.serialize.internal.KeyConsequence
import com.algolia.search.serialize.internal.KeyContains
import com.algolia.search.serialize.internal.KeyContext
import com.algolia.search.serialize.internal.KeyConversion
import com.algolia.search.serialize.internal.KeyCopy
import com.algolia.search.serialize.internal.KeyCorrections
import com.algolia.search.serialize.internal.KeyCount
import com.algolia.search.serialize.internal.KeyCountry
import com.algolia.search.serialize.internal.KeyCountryCode
import com.algolia.search.serialize.internal.KeyCounty
import com.algolia.search.serialize.internal.KeyCreateIfNotExists
import com.algolia.search.serialize.internal.KeyCreatedAt
import com.algolia.search.serialize.internal.KeyCursor
import com.algolia.search.serialize.internal.KeyCustom
import com.algolia.search.serialize.internal.KeyCustomNormalization
import com.algolia.search.serialize.internal.KeyCustomRanking
import com.algolia.search.serialize.internal.KeyCustomSearchParameters
import com.algolia.search.serialize.internal.KeyDataSize
import com.algolia.search.serialize.internal.KeyDecompoundedAttributes
import com.algolia.search.serialize.internal.KeyDecrement
import com.algolia.search.serialize.internal.KeyDegradedQuery
import com.algolia.search.serialize.internal.KeyDelete
import com.algolia.search.serialize.internal.KeyDeleteIndex
import com.algolia.search.serialize.internal.KeyDeleteObject
import com.algolia.search.serialize.internal.KeyDeletedAt
import com.algolia.search.serialize.internal.KeyDesc
import com.algolia.search.serialize.internal.KeyDescription
import com.algolia.search.serialize.internal.KeyDestination
import com.algolia.search.serialize.internal.KeyDisableExactOnAttributes
import com.algolia.search.serialize.internal.KeyDisablePrefixOnAttributes
import com.algolia.search.serialize.internal.KeyDisableTypoToleranceOnAttributes
import com.algolia.search.serialize.internal.KeyDisableTypoToleranceOnWords
import com.algolia.search.serialize.internal.KeyDisjunctive
import com.algolia.search.serialize.internal.KeyDisjunctiveFacets
import com.algolia.search.serialize.internal.KeyDistance
import com.algolia.search.serialize.internal.KeyDistinct
import com.algolia.search.serialize.internal.KeyDistrict
import com.algolia.search.serialize.internal.KeyEU
import com.algolia.search.serialize.internal.KeyEditSettings
import com.algolia.search.serialize.internal.KeyEdits
import com.algolia.search.serialize.internal.KeyEnableABTest
import com.algolia.search.serialize.internal.KeyEnablePersonalization
import com.algolia.search.serialize.internal.KeyEnableRules
import com.algolia.search.serialize.internal.KeyEnabled
import com.algolia.search.serialize.internal.KeyEndAt
import com.algolia.search.serialize.internal.KeyEndsWith
import com.algolia.search.serialize.internal.KeyEntries
import com.algolia.search.serialize.internal.KeyEqualOnly
import com.algolia.search.serialize.internal.KeyEventName
import com.algolia.search.serialize.internal.KeyEventType
import com.algolia.search.serialize.internal.KeyEvents
import com.algolia.search.serialize.internal.KeyEventsScoring
import com.algolia.search.serialize.internal.KeyExact
import com.algolia.search.serialize.internal.KeyExactOnSingleWordQuery
import com.algolia.search.serialize.internal.KeyExactPhrase
import com.algolia.search.serialize.internal.KeyExcludeWords
import com.algolia.search.serialize.internal.KeyExcluded
import com.algolia.search.serialize.internal.KeyExhaustiveFacetsCount
import com.algolia.search.serialize.internal.KeyExhaustiveNbHits
import com.algolia.search.serialize.internal.KeyExpired
import com.algolia.search.serialize.internal.KeyExplain
import com.algolia.search.serialize.internal.KeyFacet
import com.algolia.search.serialize.internal.KeyFacetFilters
import com.algolia.search.serialize.internal.KeyFacetHits
import com.algolia.search.serialize.internal.KeyFacetQuery
import com.algolia.search.serialize.internal.KeyFacetingAfterDistinct
import com.algolia.search.serialize.internal.KeyFacets
import com.algolia.search.serialize.internal.KeyFacetsScoring
import com.algolia.search.serialize.internal.KeyFacets_Stats
import com.algolia.search.serialize.internal.KeyFailed
import com.algolia.search.serialize.internal.KeyFileSize
import com.algolia.search.serialize.internal.KeyFilterOnly
import com.algolia.search.serialize.internal.KeyFilterPromotes
import com.algolia.search.serialize.internal.KeyFilters
import com.algolia.search.serialize.internal.KeyFirstMatchedWord
import com.algolia.search.serialize.internal.KeyFirstWords
import com.algolia.search.serialize.internal.KeyForwardedFor
import com.algolia.search.serialize.internal.KeyFrom
import com.algolia.search.serialize.internal.KeyFull
import com.algolia.search.serialize.internal.KeyFullyHighlighted
import com.algolia.search.serialize.internal.KeyGeo
import com.algolia.search.serialize.internal.KeyGeoDistance
import com.algolia.search.serialize.internal.KeyGeoPoint
import com.algolia.search.serialize.internal.KeyGeoPrecision
import com.algolia.search.serialize.internal.KeyGetClusters
import com.algolia.search.serialize.internal.KeyGetRankingInfo
import com.algolia.search.serialize.internal.KeyHide
import com.algolia.search.serialize.internal.KeyHighlightPostTag
import com.algolia.search.serialize.internal.KeyHighlightPreTag
import com.algolia.search.serialize.internal.KeyHighlighted
import com.algolia.search.serialize.internal.KeyHits
import com.algolia.search.serialize.internal.KeyHitsPerPage
import com.algolia.search.serialize.internal.KeyId
import com.algolia.search.serialize.internal.KeyIgnorePlurals
import com.algolia.search.serialize.internal.KeyImportance
import com.algolia.search.serialize.internal.KeyIncrement
import com.algolia.search.serialize.internal.KeyIndex
import com.algolia.search.serialize.internal.KeyIndexLanguages
import com.algolia.search.serialize.internal.KeyIndexName
import com.algolia.search.serialize.internal.KeyIndexUsed
import com.algolia.search.serialize.internal.KeyIndexes
import com.algolia.search.serialize.internal.KeyInput
import com.algolia.search.serialize.internal.KeyInsert
import com.algolia.search.serialize.internal.KeyInsideBoundingBox
import com.algolia.search.serialize.internal.KeyInsidePolygon
import com.algolia.search.serialize.internal.KeyIp
import com.algolia.search.serialize.internal.KeyIs
import com.algolia.search.serialize.internal.KeyIs_City
import com.algolia.search.serialize.internal.KeyIs_Country
import com.algolia.search.serialize.internal.KeyIs_Highway
import com.algolia.search.serialize.internal.KeyIs_Popular
import com.algolia.search.serialize.internal.KeyIs_Suburb
import com.algolia.search.serialize.internal.KeyItems
import com.algolia.search.serialize.internal.KeyKeepDiacriticsOnCharacters
import com.algolia.search.serialize.internal.KeyKey
import com.algolia.search.serialize.internal.KeyKeys
import com.algolia.search.serialize.internal.KeyLanguage
import com.algolia.search.serialize.internal.KeyLastBuildTimeS
import com.algolia.search.serialize.internal.KeyLastWords
import com.algolia.search.serialize.internal.KeyLat
import com.algolia.search.serialize.internal.KeyLength
import com.algolia.search.serialize.internal.KeyLimit
import com.algolia.search.serialize.internal.KeyListIndexes
import com.algolia.search.serialize.internal.KeyLng
import com.algolia.search.serialize.internal.KeyLocaleNames
import com.algolia.search.serialize.internal.KeyLogs
import com.algolia.search.serialize.internal.KeyMatch
import com.algolia.search.serialize.internal.KeyMatchAlternatives
import com.algolia.search.serialize.internal.KeyMatchLevel
import com.algolia.search.serialize.internal.KeyMatchedGeoLocation
import com.algolia.search.serialize.internal.KeyMatchedWords
import com.algolia.search.serialize.internal.KeyMax
import com.algolia.search.serialize.internal.KeyMaxFacetHits
import com.algolia.search.serialize.internal.KeyMaxHitsPerQuery
import com.algolia.search.serialize.internal.KeyMaxQueriesPerIPPerHour
import com.algolia.search.serialize.internal.KeyMaxValuesPerFacet
import com.algolia.search.serialize.internal.KeyMessage
import com.algolia.search.serialize.internal.KeyMethod
import com.algolia.search.serialize.internal.KeyMin
import com.algolia.search.serialize.internal.KeyMinProximity
import com.algolia.search.serialize.internal.KeyMinWordSizeFor1Typo
import com.algolia.search.serialize.internal.KeyMinWordSizeFor2Typos
import com.algolia.search.serialize.internal.KeyMinimumAroundRadius
import com.algolia.search.serialize.internal.KeyMove
import com.algolia.search.serialize.internal.KeyMultiWordsSynonym
import com.algolia.search.serialize.internal.KeyName
import com.algolia.search.serialize.internal.KeyNaturalLanguages
import com.algolia.search.serialize.internal.KeyNbExactWords
import com.algolia.search.serialize.internal.KeyNbHits
import com.algolia.search.serialize.internal.KeyNbPages
import com.algolia.search.serialize.internal.KeyNbRecords
import com.algolia.search.serialize.internal.KeyNbTypos
import com.algolia.search.serialize.internal.KeyNbUserIDs
import com.algolia.search.serialize.internal.KeyNb_Api_Calls
import com.algolia.search.serialize.internal.KeyNone
import com.algolia.search.serialize.internal.KeyNotPublished
import com.algolia.search.serialize.internal.KeyNumberOfPendingTasks
import com.algolia.search.serialize.internal.KeyNumericAttributesForFiltering
import com.algolia.search.serialize.internal.KeyNumericAttributesToIndex
import com.algolia.search.serialize.internal.KeyNumericFilters
import com.algolia.search.serialize.internal.KeyObjectID
import com.algolia.search.serialize.internal.KeyObjectIDs
import com.algolia.search.serialize.internal.KeyOffset
import com.algolia.search.serialize.internal.KeyOneWaySynonym
import com.algolia.search.serialize.internal.KeyOperation
import com.algolia.search.serialize.internal.KeyOptional
import com.algolia.search.serialize.internal.KeyOptionalFilters
import com.algolia.search.serialize.internal.KeyOptionalWords
import com.algolia.search.serialize.internal.KeyOrdered
import com.algolia.search.serialize.internal.KeyOriginal
import com.algolia.search.serialize.internal.KeyPage
import com.algolia.search.serialize.internal.KeyPaginationLimitedTo
import com.algolia.search.serialize.internal.KeyParams
import com.algolia.search.serialize.internal.KeyParsedQuery
import com.algolia.search.serialize.internal.KeyPartial
import com.algolia.search.serialize.internal.KeyPartialUpdateObject
import com.algolia.search.serialize.internal.KeyPartialUpdateObjectNoCreate
import com.algolia.search.serialize.internal.KeyPattern
import com.algolia.search.serialize.internal.KeyPending
import com.algolia.search.serialize.internal.KeyPendingTask
import com.algolia.search.serialize.internal.KeyPercentage
import com.algolia.search.serialize.internal.KeyPercentileComputation
import com.algolia.search.serialize.internal.KeyPersonalizationImpact
import com.algolia.search.serialize.internal.KeyPlaceholder
import com.algolia.search.serialize.internal.KeyPlural
import com.algolia.search.serialize.internal.KeyPopulation
import com.algolia.search.serialize.internal.KeyPosition
import com.algolia.search.serialize.internal.KeyPositions
import com.algolia.search.serialize.internal.KeyPostCode
import com.algolia.search.serialize.internal.KeyPrefixAll
import com.algolia.search.serialize.internal.KeyPrefixLast
import com.algolia.search.serialize.internal.KeyPrefixNone
import com.algolia.search.serialize.internal.KeyPrimary
import com.algolia.search.serialize.internal.KeyProcessed
import com.algolia.search.serialize.internal.KeyProcessingTimeMS
import com.algolia.search.serialize.internal.KeyProcessing_Time_Ms
import com.algolia.search.serialize.internal.KeyPromote
import com.algolia.search.serialize.internal.KeyPromoted
import com.algolia.search.serialize.internal.KeyProximity
import com.algolia.search.serialize.internal.KeyProximityDistance
import com.algolia.search.serialize.internal.KeyPublished
import com.algolia.search.serialize.internal.KeyQuery
import com.algolia.search.serialize.internal.KeyQueryAfterRemoval
import com.algolia.search.serialize.internal.KeyQueryID
import com.algolia.search.serialize.internal.KeyQueryLanguages
import com.algolia.search.serialize.internal.KeyQueryParameters
import com.algolia.search.serialize.internal.KeyQueryType
import com.algolia.search.serialize.internal.KeyQuery_Body
import com.algolia.search.serialize.internal.KeyQuery_Headers
import com.algolia.search.serialize.internal.KeyQuery_Nb_Hits
import com.algolia.search.serialize.internal.KeyQuery_Params
import com.algolia.search.serialize.internal.KeyRanking
import com.algolia.search.serialize.internal.KeyReferers
import com.algolia.search.serialize.internal.KeyRemove
import com.algolia.search.serialize.internal.KeyRemoveLowercase
import com.algolia.search.serialize.internal.KeyRemoveStopWords
import com.algolia.search.serialize.internal.KeyRemoveWordsIfNoResults
import com.algolia.search.serialize.internal.KeyReplace
import com.algolia.search.serialize.internal.KeyReplaceExistingSynonyms
import com.algolia.search.serialize.internal.KeyReplaceSynonymsInHighlight
import com.algolia.search.serialize.internal.KeyReplacements
import com.algolia.search.serialize.internal.KeyReplicas
import com.algolia.search.serialize.internal.KeyRequests
import com.algolia.search.serialize.internal.KeyResponseFields
import com.algolia.search.serialize.internal.KeyRestrictHighlightAndSnippetArrays
import com.algolia.search.serialize.internal.KeyRestrictSearchableAttributes
import com.algolia.search.serialize.internal.KeyRestrictSources
import com.algolia.search.serialize.internal.KeyResults
import com.algolia.search.serialize.internal.KeyRule
import com.algolia.search.serialize.internal.KeyRuleContexts
import com.algolia.search.serialize.internal.KeyRules
import com.algolia.search.serialize.internal.KeyScope
import com.algolia.search.serialize.internal.KeyScore
import com.algolia.search.serialize.internal.KeySearch
import com.algolia.search.serialize.internal.KeySearchable
import com.algolia.search.serialize.internal.KeySearchableAttributes
import com.algolia.search.serialize.internal.KeySeeUnretrievableAttributes
import com.algolia.search.serialize.internal.KeySeparatorsToIndex
import com.algolia.search.serialize.internal.KeyServerUsed
import com.algolia.search.serialize.internal.KeySettings
import com.algolia.search.serialize.internal.KeySha1
import com.algolia.search.serialize.internal.KeySimilarQuery
import com.algolia.search.serialize.internal.KeySingleWordSynonym
import com.algolia.search.serialize.internal.KeySlaves
import com.algolia.search.serialize.internal.KeySnippetEllipsisText
import com.algolia.search.serialize.internal.KeySortFacetValuesBy
import com.algolia.search.serialize.internal.KeySourceABTest
import com.algolia.search.serialize.internal.KeySplit
import com.algolia.search.serialize.internal.KeyStar
import com.algolia.search.serialize.internal.KeyStartsWith
import com.algolia.search.serialize.internal.KeyStatus
import com.algolia.search.serialize.internal.KeyStopIfEnoughMatches
import com.algolia.search.serialize.internal.KeyStopWord
import com.algolia.search.serialize.internal.KeyStopped
import com.algolia.search.serialize.internal.KeyStrategy
import com.algolia.search.serialize.internal.KeyStrict
import com.algolia.search.serialize.internal.KeySum
import com.algolia.search.serialize.internal.KeySumOrFiltersScores
import com.algolia.search.serialize.internal.KeySynonym
import com.algolia.search.serialize.internal.KeySynonyms
import com.algolia.search.serialize.internal.KeyTagFilters
import com.algolia.search.serialize.internal.KeyTaskID
import com.algolia.search.serialize.internal.KeyTimestamp
import com.algolia.search.serialize.internal.KeyTopUsers
import com.algolia.search.serialize.internal.KeyTotal
import com.algolia.search.serialize.internal.KeyTownhall
import com.algolia.search.serialize.internal.KeyTrackedSearchCount
import com.algolia.search.serialize.internal.KeyTrafficPercentage
import com.algolia.search.serialize.internal.KeyTrainStation
import com.algolia.search.serialize.internal.KeyType
import com.algolia.search.serialize.internal.KeyTypes
import com.algolia.search.serialize.internal.KeyTypo
import com.algolia.search.serialize.internal.KeyTypoTolerance
import com.algolia.search.serialize.internal.KeyTypos
import com.algolia.search.serialize.internal.KeyUS
import com.algolia.search.serialize.internal.KeyUnordered
import com.algolia.search.serialize.internal.KeyUnretrievableAttributes
import com.algolia.search.serialize.internal.KeyUntil
import com.algolia.search.serialize.internal.KeyUpdateObject
import com.algolia.search.serialize.internal.KeyUpdatedAt
import com.algolia.search.serialize.internal.KeyUrl
import com.algolia.search.serialize.internal.KeyUserData
import com.algolia.search.serialize.internal.KeyUserID
import com.algolia.search.serialize.internal.KeyUserIDs
import com.algolia.search.serialize.internal.KeyUserScore
import com.algolia.search.serialize.internal.KeyUserToken
import com.algolia.search.serialize.internal.KeyUsers
import com.algolia.search.serialize.internal.KeyValidity
import com.algolia.search.serialize.internal.KeyValue
import com.algolia.search.serialize.internal.KeyVariants
import com.algolia.search.serialize.internal.KeyVersion
import com.algolia.search.serialize.internal.KeyView
import com.algolia.search.serialize.internal.KeyVillage
import com.algolia.search.serialize.internal.KeyWord
import com.algolia.search.serialize.internal.KeyWords
import com.algolia.search.serialize.internal.Key_DistinctSeqID
import com.algolia.search.serialize.internal.Key_Exhaustive_Faceting
import com.algolia.search.serialize.internal.Key_Geoloc
import com.algolia.search.serialize.internal.Key_HighlightResult
import com.algolia.search.serialize.internal.Key_Operation
import com.algolia.search.serialize.internal.Key_RankingInfo
import com.algolia.search.serialize.internal.Key_SnippetResult
import com.algolia.search.serialize.internal.Key_Tags
import shouldEqual
import kotlin.test.Test

internal class TestKeys {

    @Test
    fun keys() {
        KeyQuery shouldEqual "query"
        KeySearchableAttributes shouldEqual "searchableAttributes"
        KeyAttributesForFaceting shouldEqual "attributesForFaceting"
        KeyUnretrievableAttributes shouldEqual "unretrievableAttributes"
        KeyAttributesToRetrieve shouldEqual "attributesToRetrieve"
        KeyRestrictSearchableAttributes shouldEqual "restrictSearchableAttributes"
        KeyRanking shouldEqual "ranking"
        KeyCustomRanking shouldEqual "customRanking"
        KeyReplicas shouldEqual "replicas"
        KeyFilters shouldEqual "filters"
        KeyFacetFilters shouldEqual "facetFilters"
        KeyOptionalFilters shouldEqual "optionalFilters"
        KeyNumericFilters shouldEqual "numericFilters"
        KeyTagFilters shouldEqual "tagFilters"
        KeySumOrFiltersScores shouldEqual "sumOrFiltersScores"
        KeyFacets shouldEqual "facets"
        KeyMaxValuesPerFacet shouldEqual "maxValuesPerFacet"
        KeyFacetingAfterDistinct shouldEqual "facetingAfterDistinct"
        KeySortFacetValuesBy shouldEqual "sortFacetValuesBy"
        KeyAttributesToHighlight shouldEqual "attributesToHighlight"
        KeyAttributesToSnippet shouldEqual "attributesToSnippet"
        KeyHighlightPreTag shouldEqual "highlightPreTag"
        KeyHighlightPostTag shouldEqual "highlightPostTag"
        KeySnippetEllipsisText shouldEqual "snippetEllipsisText"
        KeyRestrictHighlightAndSnippetArrays shouldEqual "restrictHighlightAndSnippetArrays"
        KeyPage shouldEqual "page"
        KeyHitsPerPage shouldEqual "hitsPerPage"
        KeyOffset shouldEqual "offset"
        KeyLength shouldEqual "length"
        KeyPaginationLimitedTo shouldEqual "paginationLimitedTo"
        KeyMinWordSizeFor1Typo shouldEqual "minWordSizefor1Typo"
        KeyMinWordSizeFor2Typos shouldEqual "minWordSizefor2Typos"
        KeyTypoTolerance shouldEqual "typoTolerance"
        KeyAllowTyposOnNumericTokens shouldEqual "allowTyposOnNumericTokens"
        KeyDisableTypoToleranceOnAttributes shouldEqual "disableTypoToleranceOnAttributes"
        KeyDisableTypoToleranceOnWords shouldEqual "disableTypoToleranceOnWords"
        KeySeparatorsToIndex shouldEqual "separatorsToIndex"
        KeyAroundLatLng shouldEqual "aroundLatLng"
        KeyAroundLatLngViaIP shouldEqual "aroundLatLngViaIP"
        KeyAroundRadius shouldEqual "aroundRadius"
        KeyAroundPrecision shouldEqual "aroundPrecision"
        KeyMinimumAroundRadius shouldEqual "minimumAroundRadius"
        KeyInsideBoundingBox shouldEqual "insideBoundingBox"
        KeyInsidePolygon shouldEqual "insidePolygon"
        KeyIgnorePlurals shouldEqual "ignorePlurals"
        KeyRemoveStopWords shouldEqual "removeStopWords"
        KeyCamelCaseAttributes shouldEqual "camelCaseAttributes"
        KeyDecompoundedAttributes shouldEqual "decompoundedAttributes"
        KeyKeepDiacriticsOnCharacters shouldEqual "keepDiacriticsOnCharacters"
        KeyQueryLanguages shouldEqual "queryLanguages"
        KeyEnableRules shouldEqual "enableRules"
        KeyRuleContexts shouldEqual "ruleContexts"
        KeyEnablePersonalization shouldEqual "enablePersonalization"
        KeyQueryType shouldEqual "queryType"
        KeyRemoveWordsIfNoResults shouldEqual "removeWordsIfNoResults"
        KeyAdvancedSyntax shouldEqual "advancedSyntax"
        KeyOptionalWords shouldEqual "optionalWords"
        KeyDisablePrefixOnAttributes shouldEqual "disablePrefixOnAttributes"
        KeyDisableExactOnAttributes shouldEqual "disableExactOnAttributes"
        KeyExactOnSingleWordQuery shouldEqual "exactOnSingleWordQuery"
        KeyAlternativesAsExact shouldEqual "alternativesAsExact"
        KeyNumericAttributesForFiltering shouldEqual "numericAttributesForFiltering"
        KeyAllowCompressionOfIntegerArray shouldEqual "allowCompressionOfIntegerArray"
        KeyAttributeForDistinct shouldEqual "attributeForDistinct"
        KeyDistinct shouldEqual "distinct"
        KeyGetRankingInfo shouldEqual "getRankingInfo"
        KeyClickAnalytics shouldEqual "clickAnalytics"
        KeyAnalytics shouldEqual "analytics"
        KeyAnalyticsTags shouldEqual "analyticsTags"
        KeySynonyms shouldEqual "synonyms"
        KeyReplaceSynonymsInHighlight shouldEqual "replaceSynonymsInHighlight"
        KeyMinProximity shouldEqual "minProximity"
        KeyResponseFields shouldEqual "responseFields"
        KeyMaxFacetHits shouldEqual "maxFacetHits"
        KeyPercentileComputation shouldEqual "percentileComputation"
        KeyGeo shouldEqual "geo"
        KeyTypo shouldEqual "typo"
        KeyWords shouldEqual "words"
        KeyProximity shouldEqual "proximity"
        KeyAttribute shouldEqual "attribute"
        KeyExact shouldEqual "exact"
        KeyCustom shouldEqual "custom"
        KeyAsc shouldEqual "asc"
        KeyDesc shouldEqual "desc"
        KeyStrict shouldEqual "strict"
        KeyMin shouldEqual "min"
        KeySingleWordSynonym shouldEqual "singleWordSynonym"
        KeyMultiWordsSynonym shouldEqual "multiWordsSynonym"
        KeyAll shouldEqual "all"
        KeyWord shouldEqual "word"
        KeyNone shouldEqual "none"
        KeyStopIfEnoughMatches shouldEqual "stopIfEnoughMatches"
        KeyPrefixLast shouldEqual "prefixLast"
        KeyPrefixAll shouldEqual "prefixAll"
        KeyPrefixNone shouldEqual "prefixNone"
        KeyLastWords shouldEqual "lastWords"
        KeyFirstWords shouldEqual "firstWords"
        KeyAllOptional shouldEqual "allOptional"
        KeyStar shouldEqual "*"
        KeyAutomaticRadius shouldEqual "automaticRadius"
        KeyExhaustiveFacetsCount shouldEqual "exhaustiveFacetsCount"
        KeyFacets_Stats shouldEqual "facets_stats"
        KeyHits shouldEqual "hits"
        KeyIndex shouldEqual "index"
        KeyNbHits shouldEqual "nbHits"
        KeyNbPages shouldEqual "nbPages"
        KeyParams shouldEqual "params"
        KeyProcessingTimeMS shouldEqual "processingTimeMS"
        KeyQueryAfterRemoval shouldEqual "queryAfterRemoval"
        KeyUserData shouldEqual "userData"
        KeyCount shouldEqual "count"
        KeyAlpha shouldEqual "alpha"
        KeyEqualOnly shouldEqual "equalOnly"
        KeyFacetQuery shouldEqual "facetQuery"
        KeyStrategy shouldEqual "strategy"
        KeyRequests shouldEqual "requests"
        KeyIndexName shouldEqual "indexName"
        KeyPublished shouldEqual "published"
        KeyNotPublished shouldEqual "notPublished"
        KeyStatus shouldEqual "status"
        KeyOperation shouldEqual "operation"
        KeyDestination shouldEqual "destination"
        KeyCopy shouldEqual "copy"
        KeyMove shouldEqual "move"
        KeyRules shouldEqual "rules"
        KeySettings shouldEqual "settings"
        KeyScope shouldEqual "scope"
        KeyCursor shouldEqual "cursor"
        KeyPartial shouldEqual "partial"
        KeyFull shouldEqual "full"
        KeyCreateIfNotExists shouldEqual "createIfNotExists"
        KeyIncrement shouldEqual "Increment"
        KeyDecrement shouldEqual "Decrement"
        KeyAdd shouldEqual "Add"
        KeyRemove shouldEqual "Remove"
        KeyRemoveLowercase shouldEqual "remove"
        KeyAddUnique shouldEqual "AddUnique"
        Key_Operation shouldEqual "_operation"
        KeyValue shouldEqual "value"
        KeyObjectID shouldEqual "objectID"
        KeyResults shouldEqual "results"
        KeyAddObject shouldEqual "addObject"
        KeyUpdateObject shouldEqual "updateObject"
        KeyPartialUpdateObject shouldEqual "partialUpdateObject"
        KeyPartialUpdateObjectNoCreate shouldEqual "partialUpdateObjectNoCreate"
        KeyDeleteObject shouldEqual "deleteObject"
        KeyDelete shouldEqual "delete"
        KeyClear shouldEqual "clear"
        KeyAction shouldEqual "action"
        KeyBody shouldEqual "body"
        KeyObjectIDs shouldEqual "objectIDs"
        KeyTaskID shouldEqual "taskID"
        KeySearch shouldEqual "search"
        KeyBrowse shouldEqual "browse"
        KeyDeleteIndex shouldEqual "deleteIndex"
        KeyEditSettings shouldEqual "editSettings"
        KeyListIndexes shouldEqual "listIndexes"
        KeyLogs shouldEqual "logs"
        KeySeeUnretrievableAttributes shouldEqual "seeUnretrievableAttributes"
        KeyType shouldEqual "type"
        KeySynonym shouldEqual "synonym"
        KeyOneWaySynonym shouldEqual "onewaysynonym"
        KeyInput shouldEqual "input"
        KeyCorrections shouldEqual "corrections"
        KeyReplacements shouldEqual "replacements"
        KeyPlaceholder shouldEqual "placeholder"
        KeyAlternativeCorrection1 shouldEqual "altcorrection1"
        KeyAlternativeCorrection2 shouldEqual "altcorrection2"
        KeyReplaceExistingSynonyms shouldEqual "replaceExistingSynonyms"
        KeyIs shouldEqual "is"
        KeyStartsWith shouldEqual "startsWith"
        KeyEndsWith shouldEqual "endsWith"
        KeyContains shouldEqual "contains"
        KeyContext shouldEqual "context"
        KeyRule shouldEqual "rule"
        KeyAnchoring shouldEqual "anchoring"
        KeyPattern shouldEqual "pattern"
        KeyReplace shouldEqual "replace"
        KeyFacet shouldEqual "facet"
        KeyDisjunctive shouldEqual "disjunctive"
        KeyScore shouldEqual "score"
        KeyInsert shouldEqual "insert"
        KeyEdits shouldEqual "edits"
        KeyAutomaticFacetFilters shouldEqual "automaticFacetFilters"
        KeyAutomaticOptionalFacetFilters shouldEqual "automaticOptionalFacetFilters"
        KeyPromote shouldEqual "promote"
        KeyHide shouldEqual "hide"
        KeyClearExistingRules shouldEqual "clearExistingRules"
        KeyCluster shouldEqual "cluster"
        KeyAlgoliaUserID shouldEqual "X-Algolia-User-ID"
        KeyForwardedFor shouldEqual "X-Forwarded-For"
        KeyDeletedAt shouldEqual "deletedAt"
        KeyCreatedAt shouldEqual "createdAt"
        KeyUpdatedAt shouldEqual "updatedAt"
        KeyKey shouldEqual "key"
        KeyUserIDs shouldEqual "userIDs"
        KeyTopUsers shouldEqual "topUsers"
        KeyKeys shouldEqual "keys"
        Key_HighlightResult shouldEqual "_highlightResult"
        Key_SnippetResult shouldEqual "_snippetResult"
        Key_RankingInfo shouldEqual "_rankingInfo"
        KeyPromoted shouldEqual "promoted"
        KeyNbTypos shouldEqual "nbTypos"
        KeyFirstMatchedWord shouldEqual "firstMatchedWord"
        KeyProximityDistance shouldEqual "proximityDistance"
        KeyUserScore shouldEqual "userScore"
        KeyGeoDistance shouldEqual "geoDistance"
        KeyGeoPrecision shouldEqual "geoPrecision"
        KeyNbExactWords shouldEqual "nbExactWords"
        KeyMatchedGeoLocation shouldEqual "matchedGeoLocation"
        KeyLat shouldEqual "lat"
        KeyLng shouldEqual "lng"
        KeyDistance shouldEqual "distance"
        Key_DistinctSeqID shouldEqual "_distinctSeqID"
        KeyExhaustiveNbHits shouldEqual "exhaustiveNbHits"
        KeyMessage shouldEqual "message"
        KeyServerUsed shouldEqual "serverUsed"
        KeyIndexUsed shouldEqual "indexUsed"
        KeyAbTestVariantID shouldEqual "abTestVariantID"
        KeyParsedQuery shouldEqual "parsedQuery"
        KeyProcessed shouldEqual "processed"
        KeyMatchLevel shouldEqual "matchLevel"
        KeyFullyHighlighted shouldEqual "fullyHighlighted"
        KeyMatchedWords shouldEqual "matchedWords"
        KeyMax shouldEqual "max"
        KeyAvg shouldEqual "avg"
        KeySum shouldEqual "sum"
        KeyName shouldEqual "name"
        KeyAcl shouldEqual "acl"
        KeyIndexes shouldEqual "indexes"
        KeyDescription shouldEqual "description"
        KeyMaxHitsPerQuery shouldEqual "maxHitsPerQuery"
        KeyMaxQueriesPerIPPerHour shouldEqual "maxQueriesPerIPPerHour"
        KeyValidity shouldEqual "validity"
        KeyQueryParameters shouldEqual "queryParameters"
        KeyReferers shouldEqual "referers"
        KeyClusterName shouldEqual "clusterName"
        KeyUserID shouldEqual "userID"
        KeyNbRecords shouldEqual "nbRecords"
        KeyDataSize shouldEqual "dataSize"
        KeyNbUserIDs shouldEqual "nbUserIDs"
        KeyClusters shouldEqual "clusters"
        KeyItems shouldEqual "items"
        KeyEntries shouldEqual "entries"
        KeyFileSize shouldEqual "fileSize"
        KeyLastBuildTimeS shouldEqual "lastBuildTimeS"
        KeyNumberOfPendingTasks shouldEqual "numberOfPendingTasks"
        KeyPendingTask shouldEqual "pendingTask"
        KeyCondition shouldEqual "condition"
        KeyConsequence shouldEqual "consequence"
        KeyEnabled shouldEqual "enabled"
        KeyFacetHits shouldEqual "facetHits"
        KeyId shouldEqual "id"
        KeyHighlighted shouldEqual "highlighted"
        KeyAlgoliaApplicationID shouldEqual "X-Algolia-Application-Id"
        KeyAlgoliaAPIKey shouldEqual "X-Algolia-API-Key"
        KeyPrimary shouldEqual "primary"
        KeySourceABTest shouldEqual "sourceABTest"
        KeyABTest shouldEqual "abTest"
        KeyOrdered shouldEqual "ordered"
        KeyUnordered shouldEqual "unordered"
        KeyFilterOnly shouldEqual "filterOnly"
        KeySearchable shouldEqual "searchable"
        KeyQueryID shouldEqual "queryID"
        KeyVersion shouldEqual "version"
        KeyPosition shouldEqual "position"
        KeyFrom shouldEqual "from"
        KeyUntil shouldEqual "until"
        KeyAttributesToIndex shouldEqual "attributesToIndex"
        KeyNumericAttributesToIndex shouldEqual "numericAttributesToIndex"
        KeySlaves shouldEqual "slaves"
        KeyRestrictSources shouldEqual "restrictSources"
        KeyTimestamp shouldEqual "timestamp"
        KeyMethod shouldEqual "method"
        KeyAnswer_Code shouldEqual "answer_code"
        KeyQuery_Body shouldEqual "query_body"
        KeyAnswer shouldEqual "answer"
        KeyUrl shouldEqual "url"
        KeyIp shouldEqual "ip"
        KeyQuery_Headers shouldEqual "query_headers"
        KeySha1 shouldEqual "sha1"
        KeyProcessing_Time_Ms shouldEqual "processing_time_ms"
        KeyNb_Api_Calls shouldEqual "nb_api_calls"
        KeyQuery_Params shouldEqual "query_params"
        KeyQuery_Nb_Hits shouldEqual "query_nb_hits"
        KeyEndAt shouldEqual "endAt"
        KeyTrafficPercentage shouldEqual "trafficPercentage"
        KeyVariants shouldEqual "variants"
        KeyABTestID shouldEqual "abTestID"
        KeyTrackedSearchCount shouldEqual "trackedSearchCount"
        KeyABTests shouldEqual "abtests"
        KeyLimit shouldEqual "limit"
        KeyTotal shouldEqual "total"
        KeyCustomSearchParameters shouldEqual "customSearchParameters"
        KeyActive shouldEqual "active"
        KeyStopped shouldEqual "stopped"
        KeyExpired shouldEqual "expired"
        KeyFailed shouldEqual "failed"
        KeyPercentage shouldEqual "percentage"
        KeyEventName shouldEqual "eventName"
        KeyUserToken shouldEqual "userToken"
        KeyPositions shouldEqual "positions"
        KeyEventType shouldEqual "eventType"
        KeyClick shouldEqual "click"
        KeyView shouldEqual "view"
        KeyConversion shouldEqual "conversion"
        KeyEvents shouldEqual "events"
        KeyDisjunctiveFacets shouldEqual "disjunctiveFacets"
        KeyEventsScoring shouldEqual "eventsScoring"
        KeyFacetsScoring shouldEqual "facetsScoring"
        Key_Exhaustive_Faceting shouldEqual "exhaustive_faceting"
        KeyExactPhrase shouldEqual "exactPhrase"
        KeyExcludeWords shouldEqual "excludeWords"
        KeyAdvancedSyntaxFeatures shouldEqual "advancedSyntaxFeatures"
        KeyPersonalizationImpact shouldEqual "personalizationImpact"
        KeyLanguage shouldEqual "language"
        KeyCity shouldEqual "city"
        KeyCountry shouldEqual "country"
        KeyAddress shouldEqual "address"
        KeyBusStop shouldEqual "busStop"
        KeyTrainStation shouldEqual "trainStation"
        KeyTownhall shouldEqual "townhall"
        KeyAirport shouldEqual "airport"
        KeyLocaleNames shouldEqual "locale_names"
        KeyCounty shouldEqual "county"
        KeyAdministrative shouldEqual "administrative"
        KeyCountryCode shouldEqual "country_code"
        KeyPostCode shouldEqual "postcode"
        KeyPopulation shouldEqual "population"
        Key_Geoloc shouldEqual "_geoloc"
        KeyIs_Country shouldEqual "is_country"
        KeyIs_City shouldEqual "is_city"
        KeyIs_Popular shouldEqual "is_popular"
        KeyIs_Highway shouldEqual "is_highway"
        KeyIs_Suburb shouldEqual "is_suburb"
        KeyImportance shouldEqual "importance"
        Key_Tags shouldEqual "_tags"
        KeyAdmin_Level shouldEqual "admin_level"
        KeyDistrict shouldEqual "district"
        KeyDegradedQuery shouldEqual "degradedQuery"
        KeyGeoPoint shouldEqual "geoPoint"
        KeyVillage shouldEqual "village"
        KeySimilarQuery shouldEqual "similarQuery"
        KeyEnableABTest shouldEqual "enableABTest"
        KeyAlternatives shouldEqual "alternatives"
        KeyIndexLanguages shouldEqual "indexLanguages"
        KeyCustomNormalization shouldEqual "customNormalization"
        KeyFilterPromotes shouldEqual "filterPromotes"
        KeyUsers shouldEqual "users"
        KeyExplain shouldEqual "explain"
        KeyOriginal shouldEqual "original"
        KeyExcluded shouldEqual "excluded"
        KeyOptional shouldEqual "optional"
        KeyStopWord shouldEqual "stopword"
        KeySplit shouldEqual "split"
        KeyConcat shouldEqual "concat"
        KeyAltcorrection shouldEqual "altcorrection"
        KeyPlural shouldEqual "plural"
        KeyCompound shouldEqual "compound"
        KeyMatch shouldEqual "match"
        KeyTypos shouldEqual "typos"
        KeyMatchAlternatives shouldEqual "match.alternatives"
        KeyTypes shouldEqual "types"
        KeyPending shouldEqual "pending"
        KeyGetClusters shouldEqual "getClusters"
        KeyEU shouldEqual "eu"
        KeyUS shouldEqual "us"
        KeyNaturalLanguages shouldEqual "naturalLanguages"
    }
}
