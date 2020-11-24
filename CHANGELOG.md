# Unreleased

### Added
- _Experimental_: Algolia Answers API

### Changed
- Update Ktor to `1.4.2`, transitively Kotlin serialization to `1.0.1`

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
this due the [breaking changes](https://github.com/Kotlin/kotlinx.serialization/blob/master/CHANGELOG.md#0200--2020-03-04) of kotlin serialization library.

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

- Fixed a bug when using advanced search: Disjunctive filters where flattened and lost proper AND / OR operator from the group #151

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
- Added `ClientPlaces` to access Algolia Places endpoints. See this [file](docs/Places.md) for getting starting with Places.
- `QueryLanguage` is renamed to `Language`
- Fixed a bug in `browseAllABTests` methods
