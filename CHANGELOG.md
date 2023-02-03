# 2.1.3

### Fixed
- Insights: `sendEvent` ignoring request options (#391)
- Algolia agent as url param (#391)

### Changed
- Update Kotlin to `1.8.10` (#390)
- Update ktor to `2.2.2` (#390)

# 2.1.2

### Fixed

- Rules: `browseRules` deserialization (#389)
- Error handling: propagate coroutine cancellation (#380)

### Changed
- Update Kotlin to `1.8.0` (#361)
- Update ktor to `2.2.2` (#374)
- Update Kotlin serialization json `1.4.0` (#372)

### Deprecated
- Algolia places API (#381)

# 2.1.1

### Changed

- Update Kotlin to `1.7.10` (#361)
- Update ktor to `2.1.1` (#374)
- Update Kotlin serialization json `1.4.0` (#372)

# 2.1.0

### Added

- Http Client `Logger` (#341)
- BOM artifact (#346)
- Enable metadata variant compatibility (#345)

### Changed

- Update Kotlin to `1.7.0` (#348)
- Update ktor to `2.0.2` (#343)

### Fix

- Filters dsl defaults (#342)
- `AlgoliaClientException` to include exception cause (#344)

# 2.0.0

### Changed

- Update Kotlin to `1.6.21`
- Update Ktor client to [2.0](https://blog.jetbrains.com/ktor/2022/04/11/ktor-2-0-released/)
- Library's own `LogLevel` to specify the logging level

### Removed

- Public string constants (e.g. `KeyQuery`, `RouteIndexesV1`...)
- Ktor's plugins from the library binary interface
- Deprecated `ClientRecommendation`
- `ResponseException` extensions

# 1.13.0

### Changed

- **Breaking**: the client throws `AlgoliaRuntimeException` instead of
  ktor's `ResponseException` ([guide](/docs/ExceptionHandling.md)) (#327)
- Update Kotlin to 1.6.10
- Update Ktor 1.6.8

### Fix

- **Breaking**: facet stats `average` and `sum` as nullable (#325)

# 1.12.2

### Fix

- use `POST` for subsequent browse (#321)
- default value for `FacetOrdering.values` (#323)

# 1.12.1

### Changed

- Update Kotlin to 1.5.32
- Update Ktor 1.6.5

### Fix

- Rules: `Promotion` deserialization (#317)
- Randomize fallback hosts (#319)

# 1.12.0

### Added

- Add custom request (#302)

### Fix

- Add `facetQuery` parameter to `FacetIndexQuery` (#297)
- Optimize `SimpleDateFormat` instantiations (#301)
- `ResponseMultiSearch` generic `results` type (#303)

# 1.11.0

### Added

- Search: client-level search method (#291)

### Fix

- Include `_operation` for partialUpdateObjects (#295)
- Shared `SimpleDateFormat` instances (#296)

### Changed

- Update Kotlin to 1.5.30
- Update Ktor 1.6.4

# 1.10.0

### Added

- Recommend API client (#281)

### Fix

- Add `abTestID` to `ResponseSearch` (#285)

### Changed

- Non-null fields from `ResponseSearch` throws `IllegalStateException` instead of NPE (#286)

# 1.9.2

### Fix

- Facets ordering: `emptyList()` as default for `FacetValuesOrder`'s field `order` (#278)

### Changed

- Update Kotlin to 1.5.21
- Update Ktor 1.6.2

# 1.9.1

### Fix

- Search: add `enableReRanking` field to Query (#275)

# 1.9.0

### Fixed

- Answers: use UTF8 encoded index name (#269)

### Changed

- `ClientRecommendation` renamed to `ClientPersonalization` (#267)
- Remove defaults from `FacetsOrder` and `FacetsValuesOrder` (#273)
- Update Kotlin to 1.5.20
- Update Ktor to 1.6.1

# 1.8.0

### Added

- Facets ordering (#245)

### Fixed

- Analytics: fields nullability in response variant (#262)

### Changed

- Update Kotlin to 1.5.10
- Update Ktor 1.6.0

# 1.7.0

### Added

- Query/Settings: `relevancyStrictness` and `decompoundQuery` parameters (#248)
- ResponseSearch: `appliedRelevancyStrictness` and `nbSortedHits` parameters
- Settings: `attributesToTransliterate` parameter (#249)
- Custom dictionaries: stop words, plurals and compounds
- Rules condition: `filters` parameter (#250)

### Changed

- Update Kotlin to 1.4.30
- Update Ktor 1.5.1

# 1.6.2

### Added

- Settings: `attributeCriteriaComputedByMinProximity` parameter

### Changed

- Update Kotlin to 1.4.20
- Update Ktor 1.5.0

### Experimental

- AnswersQuery: `queryLanguages` required (non-nullable)
- AnswersQuery: implements `SearchParameters` for convenience (delegated to `params`)

# 1.6.1

### Fixed

- Experimental annotation KDoc
- RankingInfo: personalization fields deserialization

# 1.6.0

### Changed

- Update Ktor to `1.4.2`, transitively Kotlin serialization to `1.0.1`

### Experimental

- _Experimental_: Algolia Answers API

# 1.5.2

### Fixed

- Serialization Proguard rules (#231)

# 1.5.1

### Fixed

- AB testing average click pos type from `Int` to `Float`
- Transport layer timeouts

### Changed

- Update Ktor to 1.4.1 transitively Kotlin serialization to `1.0.0-RC2`

# 1.5.0

### Added

- Multi-condition rules (#201)
- `IncrementFrom` and `IncrementSet` built-in operations (#202)
- Include ProGuard rules into `aar` and `jar` artifacts
- `alternative` param in the `condition` function within `DSLConditions`

### Changed

- Update Kotlin to 1.4.10
- Update Ktor to 1.4.0 and transitively Kotlin serialization to `1.0.0-RC`
- `indexName` required in `InnerQuery`
- Base64 implementation (#216)

### Removed

- `android` module and use [gradle module metadata](https://blog.gradle.org/gradle-metadata-1.0)
- Long term deprecated functions and constructors

# 1.4.0

Starting from this version, the library is compatible only with kotlin version `1.3.70` or higher,
this due
the [breaking changes](https://github.com/Kotlin/kotlinx.serialization/blob/master/CHANGELOG.md#0200--2020-03-04) of
kotlin serialization library.

### Added

- Recommendation client (#148)
- Analytics regions (#170)
- Natural languages parameter to search query (#173)
- Logs inner queries (#174)
- Personalization as part of the `RankingInfo` of the `ResponseSearch` (#185)
- Unquoted variant of legacy `FilterConverter` (#188)
- Accept `enablePersonalization` boolean as a valid setting parameter (#183)
- `appliedRules` as part of the `ResponseSearch` (#184)

### Changed

- Updated Kotlin to `1.3.72`
- Updated Ktor client to `1.3.2`
- Updated Kotlin serialization `0.20.0`
- Split `Promotion` into two variants: `Single` and `Multiple` (#191)

### Fixed

- Ensure correctly typing of API key's `restrictIndices` and `restrictSources` parameters (#167, #168)
- Search response serialization after enabling personalization (#185)
- Insights event serialization causing errors in old Android JVMs (#189)
- Omit all line terminators in the base64 encoder (instantsearch-android#191)

### Removed

- Remove deprecated personalization endpoint in favor of the recommendation client (#185)

# 1.3.1

- Updated Ktor to `1.3.0`

# 1.2.2

- Fixed a bug for geoDistance when we receive a MAX_ULONG value of 18446744073709551615 from the server #156

# 1.2.1

- Fixed a bug when using advanced search: Disjunctive filters where flattened and lost proper AND / OR operator from the
  group #151

# 1.2.0

- Updated Kotlin `1.3.60`
- Ktor `1.2.6`
- Add `hasPendingMapping` method to multi cluster management #149
- Add `assignUserIDs` method to multi cluster management #144
- Add `explain decompouding` feature #147
- Add `filterPromotes` parameter to `Rule.Consequence` #142
- Add Android multiplatform artifact (`com.algolia:algoliasearch-client-kotlin-android`), targeting SDK 29 #132

# 1.1.4

- Updated Kotlin to `1.3.50`
- Updated Ktor to `1.2.4` (Including serialization `0.12.0`)
- Renamed `findFirstObject` to `findObject`
- Renamed `getObjectIDPosition` to `getObjectPosition`
- Added `indexLanguages` DSL
- Added DSL for `Language`
- Fix serialization error in `PartialUpdateObject.from` method

# 1.1.3

- Removed `Index.searchDisjunctiveFacets` method
- New `Index.advancedSearch` for both disjunctive and hierarchical search
- New `FilterGroup.And.Hierarchical` class
- New `getSecuredApiKeyRemainingValidity` method on `APIKey`
- New `indexLanguages` field in `Settings`
- New `alternative` field in `Rule.condition`
- New `ResponseSearch.Hit.getObjectIDPosition` method
- New `findFirstObject` method

# 1.1.2

- Ktor version 1.2.3
- Minimum version of Kotlin is now 1.3.41

# 1.1.1

- `Compression` is `None` by default
- Added new `searchHierarchical method`

# 1.1.0

- Kotlin version 1.3.41
- Ktor version 1.2.3-rc
- Added `enableABTest` as `Query` parameter
- Added `similarQuery` as `Query` parameter
- Added `advancedSyntaxFeatures` as `Query` parameter
- Added `index.exists()` method
- New `AlgoliaSearchClient` object exposes library version constant
- Added `Compression` feature. `Gzip` compression is enabled by default.
- Default `readTimeout` has been increased to 5 seconds
- It is now possible to configure `HttpClientConfig` in `Configuration`
- Added `ClientPlaces` to access Algolia Places endpoints. See this [file](docs/Places.md) for getting starting with
  Places.
- `QueryLanguage` is renamed to `Language`
- Fixed a bug in `browseAllABTests` methods
