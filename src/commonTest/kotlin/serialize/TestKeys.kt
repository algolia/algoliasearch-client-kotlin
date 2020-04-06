package serialize

import com.algolia.search.serialize.KeyABTest
import com.algolia.search.serialize.KeyABTestID
import com.algolia.search.serialize.KeyABTests
import com.algolia.search.serialize.KeyAbTestVariantID
import com.algolia.search.serialize.KeyAcl
import com.algolia.search.serialize.KeyAction
import com.algolia.search.serialize.KeyActive
import com.algolia.search.serialize.KeyAdd
import com.algolia.search.serialize.KeyAddObject
import com.algolia.search.serialize.KeyAddUnique
import com.algolia.search.serialize.KeyAddress
import com.algolia.search.serialize.KeyAdmin_Level
import com.algolia.search.serialize.KeyAdministrative
import com.algolia.search.serialize.KeyAdvancedSyntax
import com.algolia.search.serialize.KeyAdvancedSyntaxFeatures
import com.algolia.search.serialize.KeyAirport
import com.algolia.search.serialize.KeyAlgoliaAPIKey
import com.algolia.search.serialize.KeyAlgoliaApplicationID
import com.algolia.search.serialize.KeyAlgoliaUserID
import com.algolia.search.serialize.KeyAll
import com.algolia.search.serialize.KeyAllOptional
import com.algolia.search.serialize.KeyAllowCompressionOfIntegerArray
import com.algolia.search.serialize.KeyAllowTyposOnNumericTokens
import com.algolia.search.serialize.KeyAlpha
import com.algolia.search.serialize.KeyAltcorrection
import com.algolia.search.serialize.KeyAlternativeCorrection1
import com.algolia.search.serialize.KeyAlternativeCorrection2
import com.algolia.search.serialize.KeyAlternatives
import com.algolia.search.serialize.KeyAlternativesAsExact
import com.algolia.search.serialize.KeyAnalytics
import com.algolia.search.serialize.KeyAnalyticsTags
import com.algolia.search.serialize.KeyAnchoring
import com.algolia.search.serialize.KeyAnswer
import com.algolia.search.serialize.KeyAnswer_Code
import com.algolia.search.serialize.KeyAroundLatLng
import com.algolia.search.serialize.KeyAroundLatLngViaIP
import com.algolia.search.serialize.KeyAroundPrecision
import com.algolia.search.serialize.KeyAroundRadius
import com.algolia.search.serialize.KeyAsc
import com.algolia.search.serialize.KeyAttribute
import com.algolia.search.serialize.KeyAttributeForDistinct
import com.algolia.search.serialize.KeyAttributesForFaceting
import com.algolia.search.serialize.KeyAttributesToHighlight
import com.algolia.search.serialize.KeyAttributesToIndex
import com.algolia.search.serialize.KeyAttributesToRetrieve
import com.algolia.search.serialize.KeyAttributesToSnippet
import com.algolia.search.serialize.KeyAutomaticFacetFilters
import com.algolia.search.serialize.KeyAutomaticOptionalFacetFilters
import com.algolia.search.serialize.KeyAutomaticRadius
import com.algolia.search.serialize.KeyAvg
import com.algolia.search.serialize.KeyBody
import com.algolia.search.serialize.KeyBrowse
import com.algolia.search.serialize.KeyBusStop
import com.algolia.search.serialize.KeyCamelCaseAttributes
import com.algolia.search.serialize.KeyCity
import com.algolia.search.serialize.KeyClear
import com.algolia.search.serialize.KeyClearExistingRules
import com.algolia.search.serialize.KeyClick
import com.algolia.search.serialize.KeyClickAnalytics
import com.algolia.search.serialize.KeyCluster
import com.algolia.search.serialize.KeyClusterName
import com.algolia.search.serialize.KeyClusters
import com.algolia.search.serialize.KeyCompound
import com.algolia.search.serialize.KeyConcat
import com.algolia.search.serialize.KeyCondition
import com.algolia.search.serialize.KeyConsequence
import com.algolia.search.serialize.KeyContains
import com.algolia.search.serialize.KeyContext
import com.algolia.search.serialize.KeyConversion
import com.algolia.search.serialize.KeyCopy
import com.algolia.search.serialize.KeyCorrections
import com.algolia.search.serialize.KeyCount
import com.algolia.search.serialize.KeyCountry
import com.algolia.search.serialize.KeyCountryCode
import com.algolia.search.serialize.KeyCounty
import com.algolia.search.serialize.KeyCreateIfNotExists
import com.algolia.search.serialize.KeyCreatedAt
import com.algolia.search.serialize.KeyCursor
import com.algolia.search.serialize.KeyCustom
import com.algolia.search.serialize.KeyCustomNormalization
import com.algolia.search.serialize.KeyCustomRanking
import com.algolia.search.serialize.KeyCustomSearchParameters
import com.algolia.search.serialize.KeyDataSize
import com.algolia.search.serialize.KeyDecompoundedAttributes
import com.algolia.search.serialize.KeyDecrement
import com.algolia.search.serialize.KeyDegradedQuery
import com.algolia.search.serialize.KeyDelete
import com.algolia.search.serialize.KeyDeleteIndex
import com.algolia.search.serialize.KeyDeleteObject
import com.algolia.search.serialize.KeyDeletedAt
import com.algolia.search.serialize.KeyDesc
import com.algolia.search.serialize.KeyDescription
import com.algolia.search.serialize.KeyDestination
import com.algolia.search.serialize.KeyDisableExactOnAttributes
import com.algolia.search.serialize.KeyDisablePrefixOnAttributes
import com.algolia.search.serialize.KeyDisableTypoToleranceOnAttributes
import com.algolia.search.serialize.KeyDisableTypoToleranceOnWords
import com.algolia.search.serialize.KeyDisjunctive
import com.algolia.search.serialize.KeyDisjunctiveFacets
import com.algolia.search.serialize.KeyDistance
import com.algolia.search.serialize.KeyDistinct
import com.algolia.search.serialize.KeyDistrict
import com.algolia.search.serialize.KeyEU
import com.algolia.search.serialize.KeyEditSettings
import com.algolia.search.serialize.KeyEdits
import com.algolia.search.serialize.KeyEnableABTest
import com.algolia.search.serialize.KeyEnablePersonalization
import com.algolia.search.serialize.KeyEnableRules
import com.algolia.search.serialize.KeyEnabled
import com.algolia.search.serialize.KeyEndAt
import com.algolia.search.serialize.KeyEndsWith
import com.algolia.search.serialize.KeyEntries
import com.algolia.search.serialize.KeyEqualOnly
import com.algolia.search.serialize.KeyEventName
import com.algolia.search.serialize.KeyEventType
import com.algolia.search.serialize.KeyEvents
import com.algolia.search.serialize.KeyEventsScoring
import com.algolia.search.serialize.KeyExact
import com.algolia.search.serialize.KeyExactOnSingleWordQuery
import com.algolia.search.serialize.KeyExactPhrase
import com.algolia.search.serialize.KeyExcludeWords
import com.algolia.search.serialize.KeyExcluded
import com.algolia.search.serialize.KeyExhaustiveFacetsCount
import com.algolia.search.serialize.KeyExhaustiveNbHits
import com.algolia.search.serialize.KeyExpired
import com.algolia.search.serialize.KeyExplain
import com.algolia.search.serialize.KeyFacet
import com.algolia.search.serialize.KeyFacetFilters
import com.algolia.search.serialize.KeyFacetHits
import com.algolia.search.serialize.KeyFacetQuery
import com.algolia.search.serialize.KeyFacetingAfterDistinct
import com.algolia.search.serialize.KeyFacets
import com.algolia.search.serialize.KeyFacetsScoring
import com.algolia.search.serialize.KeyFacets_Stats
import com.algolia.search.serialize.KeyFailed
import com.algolia.search.serialize.KeyFileSize
import com.algolia.search.serialize.KeyFilterOnly
import com.algolia.search.serialize.KeyFilterPromotes
import com.algolia.search.serialize.KeyFilters
import com.algolia.search.serialize.KeyFirstMatchedWord
import com.algolia.search.serialize.KeyFirstWords
import com.algolia.search.serialize.KeyForwardedFor
import com.algolia.search.serialize.KeyFrom
import com.algolia.search.serialize.KeyFull
import com.algolia.search.serialize.KeyFullyHighlighted
import com.algolia.search.serialize.KeyGeo
import com.algolia.search.serialize.KeyGeoDistance
import com.algolia.search.serialize.KeyGeoPoint
import com.algolia.search.serialize.KeyGeoPrecision
import com.algolia.search.serialize.KeyGetClusters
import com.algolia.search.serialize.KeyGetRankingInfo
import com.algolia.search.serialize.KeyHide
import com.algolia.search.serialize.KeyHighlightPostTag
import com.algolia.search.serialize.KeyHighlightPreTag
import com.algolia.search.serialize.KeyHighlighted
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyId
import com.algolia.search.serialize.KeyIgnorePlurals
import com.algolia.search.serialize.KeyImportance
import com.algolia.search.serialize.KeyIncrement
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyIndexLanguages
import com.algolia.search.serialize.KeyIndexName
import com.algolia.search.serialize.KeyIndexUsed
import com.algolia.search.serialize.KeyIndexes
import com.algolia.search.serialize.KeyInput
import com.algolia.search.serialize.KeyInsert
import com.algolia.search.serialize.KeyInsideBoundingBox
import com.algolia.search.serialize.KeyInsidePolygon
import com.algolia.search.serialize.KeyIp
import com.algolia.search.serialize.KeyIs
import com.algolia.search.serialize.KeyIs_City
import com.algolia.search.serialize.KeyIs_Country
import com.algolia.search.serialize.KeyIs_Highway
import com.algolia.search.serialize.KeyIs_Popular
import com.algolia.search.serialize.KeyIs_Suburb
import com.algolia.search.serialize.KeyItems
import com.algolia.search.serialize.KeyKeepDiacriticsOnCharacters
import com.algolia.search.serialize.KeyKey
import com.algolia.search.serialize.KeyKeys
import com.algolia.search.serialize.KeyLanguage
import com.algolia.search.serialize.KeyLastBuildTimeS
import com.algolia.search.serialize.KeyLastWords
import com.algolia.search.serialize.KeyLat
import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyLimit
import com.algolia.search.serialize.KeyListIndexes
import com.algolia.search.serialize.KeyLng
import com.algolia.search.serialize.KeyLocaleNames
import com.algolia.search.serialize.KeyLogs
import com.algolia.search.serialize.KeyMatch
import com.algolia.search.serialize.KeyMatchAlternatives
import com.algolia.search.serialize.KeyMatchLevel
import com.algolia.search.serialize.KeyMatchedGeoLocation
import com.algolia.search.serialize.KeyMatchedWords
import com.algolia.search.serialize.KeyMax
import com.algolia.search.serialize.KeyMaxFacetHits
import com.algolia.search.serialize.KeyMaxHitsPerQuery
import com.algolia.search.serialize.KeyMaxQueriesPerIPPerHour
import com.algolia.search.serialize.KeyMaxValuesPerFacet
import com.algolia.search.serialize.KeyMessage
import com.algolia.search.serialize.KeyMethod
import com.algolia.search.serialize.KeyMin
import com.algolia.search.serialize.KeyMinProximity
import com.algolia.search.serialize.KeyMinWordSizeFor1Typo
import com.algolia.search.serialize.KeyMinWordSizeFor2Typos
import com.algolia.search.serialize.KeyMinimumAroundRadius
import com.algolia.search.serialize.KeyMove
import com.algolia.search.serialize.KeyMultiWordsSynonym
import com.algolia.search.serialize.KeyName
import com.algolia.search.serialize.KeyNbExactWords
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyNbRecords
import com.algolia.search.serialize.KeyNbTypos
import com.algolia.search.serialize.KeyNbUserIDs
import com.algolia.search.serialize.KeyNb_Api_Calls
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyNotPublished
import com.algolia.search.serialize.KeyNumberOfPendingTasks
import com.algolia.search.serialize.KeyNumericAttributesForFiltering
import com.algolia.search.serialize.KeyNumericAttributesToIndex
import com.algolia.search.serialize.KeyNumericFilters
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyObjectIDs
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyOneWaySynonym
import com.algolia.search.serialize.KeyOperation
import com.algolia.search.serialize.KeyOptional
import com.algolia.search.serialize.KeyOptionalFilters
import com.algolia.search.serialize.KeyOptionalWords
import com.algolia.search.serialize.KeyOrdered
import com.algolia.search.serialize.KeyOriginal
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyPaginationLimitedTo
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyParsedQuery
import com.algolia.search.serialize.KeyPartial
import com.algolia.search.serialize.KeyPartialUpdateObject
import com.algolia.search.serialize.KeyPartialUpdateObjectNoCreate
import com.algolia.search.serialize.KeyPattern
import com.algolia.search.serialize.KeyPending
import com.algolia.search.serialize.KeyPendingTask
import com.algolia.search.serialize.KeyPercentage
import com.algolia.search.serialize.KeyPercentileComputation
import com.algolia.search.serialize.KeyPersonalizationImpact
import com.algolia.search.serialize.KeyPlaceholder
import com.algolia.search.serialize.KeyPlural
import com.algolia.search.serialize.KeyPopulation
import com.algolia.search.serialize.KeyPosition
import com.algolia.search.serialize.KeyPositions
import com.algolia.search.serialize.KeyPostCode
import com.algolia.search.serialize.KeyPrefixAll
import com.algolia.search.serialize.KeyPrefixLast
import com.algolia.search.serialize.KeyPrefixNone
import com.algolia.search.serialize.KeyPrimary
import com.algolia.search.serialize.KeyProcessed
import com.algolia.search.serialize.KeyProcessingTimeMS
import com.algolia.search.serialize.KeyProcessing_Time_Ms
import com.algolia.search.serialize.KeyPromote
import com.algolia.search.serialize.KeyPromoted
import com.algolia.search.serialize.KeyProximity
import com.algolia.search.serialize.KeyProximityDistance
import com.algolia.search.serialize.KeyPublished
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyQueryAfterRemoval
import com.algolia.search.serialize.KeyQueryID
import com.algolia.search.serialize.KeyQueryLanguages
import com.algolia.search.serialize.KeyQueryParameters
import com.algolia.search.serialize.KeyQueryType
import com.algolia.search.serialize.KeyQuery_Body
import com.algolia.search.serialize.KeyQuery_Headers
import com.algolia.search.serialize.KeyQuery_Nb_Hits
import com.algolia.search.serialize.KeyQuery_Params
import com.algolia.search.serialize.KeyRanking
import com.algolia.search.serialize.KeyReferers
import com.algolia.search.serialize.KeyRemove
import com.algolia.search.serialize.KeyRemoveLowercase
import com.algolia.search.serialize.KeyRemoveStopWords
import com.algolia.search.serialize.KeyRemoveWordsIfNoResults
import com.algolia.search.serialize.KeyReplace
import com.algolia.search.serialize.KeyReplaceExistingSynonyms
import com.algolia.search.serialize.KeyReplaceSynonymsInHighlight
import com.algolia.search.serialize.KeyReplacements
import com.algolia.search.serialize.KeyReplicas
import com.algolia.search.serialize.KeyRequests
import com.algolia.search.serialize.KeyResponseFields
import com.algolia.search.serialize.KeyRestrictHighlightAndSnippetArrays
import com.algolia.search.serialize.KeyRestrictSearchableAttributes
import com.algolia.search.serialize.KeyRestrictSources
import com.algolia.search.serialize.KeyResults
import com.algolia.search.serialize.KeyRule
import com.algolia.search.serialize.KeyRuleContexts
import com.algolia.search.serialize.KeyRules
import com.algolia.search.serialize.KeyScope
import com.algolia.search.serialize.KeyScore
import com.algolia.search.serialize.KeySearch
import com.algolia.search.serialize.KeySearchable
import com.algolia.search.serialize.KeySearchableAttributes
import com.algolia.search.serialize.KeySeeUnretrievableAttributes
import com.algolia.search.serialize.KeySeparatorsToIndex
import com.algolia.search.serialize.KeyServerUsed
import com.algolia.search.serialize.KeySettings
import com.algolia.search.serialize.KeySha1
import com.algolia.search.serialize.KeySimilarQuery
import com.algolia.search.serialize.KeySingleWordSynonym
import com.algolia.search.serialize.KeySlaves
import com.algolia.search.serialize.KeySnippetEllipsisText
import com.algolia.search.serialize.KeySortFacetValuesBy
import com.algolia.search.serialize.KeySourceABTest
import com.algolia.search.serialize.KeySplit
import com.algolia.search.serialize.KeyStar
import com.algolia.search.serialize.KeyStartsWith
import com.algolia.search.serialize.KeyStatus
import com.algolia.search.serialize.KeyStopIfEnoughMatches
import com.algolia.search.serialize.KeyStopWord
import com.algolia.search.serialize.KeyStopped
import com.algolia.search.serialize.KeyStrategy
import com.algolia.search.serialize.KeyStrict
import com.algolia.search.serialize.KeySum
import com.algolia.search.serialize.KeySumOrFiltersScores
import com.algolia.search.serialize.KeySynonym
import com.algolia.search.serialize.KeySynonyms
import com.algolia.search.serialize.KeyTagFilters
import com.algolia.search.serialize.KeyTaskID
import com.algolia.search.serialize.KeyTimestamp
import com.algolia.search.serialize.KeyTopUsers
import com.algolia.search.serialize.KeyTotal
import com.algolia.search.serialize.KeyTownhall
import com.algolia.search.serialize.KeyTrackedSearchCount
import com.algolia.search.serialize.KeyTrafficPercentage
import com.algolia.search.serialize.KeyTrainStation
import com.algolia.search.serialize.KeyType
import com.algolia.search.serialize.KeyTypes
import com.algolia.search.serialize.KeyTypo
import com.algolia.search.serialize.KeyTypoTolerance
import com.algolia.search.serialize.KeyTypos
import com.algolia.search.serialize.KeyUS
import com.algolia.search.serialize.KeyUnordered
import com.algolia.search.serialize.KeyUnretrievableAttributes
import com.algolia.search.serialize.KeyUntil
import com.algolia.search.serialize.KeyUpdateObject
import com.algolia.search.serialize.KeyUpdatedAt
import com.algolia.search.serialize.KeyUrl
import com.algolia.search.serialize.KeyUserData
import com.algolia.search.serialize.KeyUserID
import com.algolia.search.serialize.KeyUserIDs
import com.algolia.search.serialize.KeyUserScore
import com.algolia.search.serialize.KeyUserToken
import com.algolia.search.serialize.KeyUsers
import com.algolia.search.serialize.KeyValidity
import com.algolia.search.serialize.KeyValue
import com.algolia.search.serialize.KeyVariants
import com.algolia.search.serialize.KeyVersion
import com.algolia.search.serialize.KeyView
import com.algolia.search.serialize.KeyVillage
import com.algolia.search.serialize.KeyWord
import com.algolia.search.serialize.KeyWords
import com.algolia.search.serialize.Key_DistinctSeqID
import com.algolia.search.serialize.Key_Exhaustive_Faceting
import com.algolia.search.serialize.Key_Geoloc
import com.algolia.search.serialize.Key_HighlightResult
import com.algolia.search.serialize.Key_Operation
import com.algolia.search.serialize.Key_RankingInfo
import com.algolia.search.serialize.Key_SnippetResult
import com.algolia.search.serialize.Key_Tags
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
    }
}
