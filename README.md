<p align="center">
  <a href="https://www.algolia.com">
    <img alt="Algolia for Kotlin" src="https://raw.githubusercontent.com/algolia/algoliasearch-client-common/master/banners/kotlin.png" >
  </a>

<h4 align="center">The perfect starting point to integrate <a href="https://algolia.com" target="_blank">Algolia</a> within your Kotlin project</h4>

  <p align="center">
    <a href="https://search.maven.org/search?q=a:algoliasearch-client-kotlin"><img src="https://img.shields.io/maven-central/v/com.algolia/algoliasearch-client-kotlin?label=Download" alt="Latest version"></img></a>
    <a href="https://opensource.org/licenses/MIT"><img src="https://img.shields.io/badge/License-MIT-yellow.svg" alt="Licence"></img></a>
  </p>
</p>

<p align="center">
  <a href="https://www.algolia.com/doc/libraries/sdk/install#kotlin" target="_blank">Documentation</a>  •
  <a href="https://discourse.algolia.com" target="_blank">Community Forum</a>  •
  <a href="http://stackoverflow.com/questions/tagged/algolia" target="_blank">Stack Overflow</a>  •
  <a href="https://github.com/algolia/algoliasearch-client-kotlin/issues" target="_blank">Report a bug</a>  •
  <a href="https://alg.li/support" target="_blank">Support</a>
</p>

## ✨ Features

- The Kotlin client is compatible with Kotlin `1.6` and higher.
- It is compatible with Kotlin project on the JVM, such as backend and Android applications.
- It relies on the open source Kotlin libraries for seamless integration into Kotlin projects:
    - [Kotlin multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html).
    - [Kotlinx serialization](https://github.com/Kotlin/kotlinx.serialization) for json parsing.
    - [Kotlinx coroutines](https://github.com/Kotlin/kotlinx.coroutines) for asynchronous operations.
    - [Ktor](https://github.com/ktorio/ktor) HTTP client.
- The Kotlin client integrates the actual Algolia documentation in each source file: Request parameters, response fields, methods and concepts; all are documented and link to the corresponding url of the Algolia doc website.
- The client is thread-safe. You can use `SearchClient`, `AnalyticsClient`, and `InsightsClient` in a multithreaded environment.

## 💡 Getting Started

Install the Kotlin client by adding the following dependency to your `gradle.build` file:

  ```gradle
  repositories {
     mavenCentral()
  }
  
  dependencies {
     implementation "com.algolia:algoliasearch-client-kotlin:$version"
  }
  ```
Also, choose and add to your dependencies one of [Ktor http client engines](https://ktor.io/docs/http-client-engines.html).
Alternatively, you can use [algoliasearch-client-kotlin-bom](/client-bom).  

For full documentation, visit the **[Algolia Kotlin API Client](https://www.algolia.com/doc/libraries/sdk/install#kotlin)**.

## Optional Kotlin DSL

The client includes an optional Kotlin DSL to build search parameters, index settings, rules, and filters. The DSL is experimental: opt in with `@OptIn(AlgoliaExperimentalDsl::class)`. It builds the same models as the data-class constructors, which remain fully supported.

> [!WARNING]
> The DSL isn't source compatible with version 2. Many names are the same, but some behaviors changed. See [Migrating from version 2](#migrating-from-version-2).

### Search parameters and filters

```kotlin
import com.algolia.client.dsl.*

@OptIn(AlgoliaExperimentalDsl::class)
val response = client.searchSingleIndex("products") {
  query = "shoes"
  hitsPerPage = 20
  filters {
    orFacet { facet("brand", "Acme"); facet("brand", "Globex") }
    facet("category", "outlet", isNegated = true)
  }
  optionalFilters {
    or { facet("color", "red", score = 2); facet("color", "blue") }
  }
}
```

- `filters { }` builds the `filters` string. Top-level filters are joined with `AND`. Use `orFacet { }`, `orTag { }`, or `orNumeric { }` for `OR` groups.
- `optionalFilters { }` builds the `optionalFilters` field, with `and { }` and `or { }` groups of facets, each with an optional `score`.
- `isNegated = true` negates a single filter. Pass it by name: the third positional argument of `facet` is `score`.
- An empty block omits the field.

`query { }` builds a `SearchParamsObject` without sending it. `browse { }` does the same for `client.browse`:

```kotlin
@OptIn(AlgoliaExperimentalDsl::class)
client.browse("products", browse { query = "shoes"; filters { facet("brand", "Acme") } })
```

List parameters take `+` entries. A block replaces the list, and an empty block sends `[]`:

```kotlin
import com.algolia.client.dsl.*
import com.algolia.client.model.search.SupportedLanguage

@OptIn(AlgoliaExperimentalDsl::class)
val params = query {
  restrictSearchableAttributes { +"title"; +"description" }
  queryLanguages { +SupportedLanguage.En }
}
```

### Deleting records by filter

`client.deleteBy(indexName) { filters { … } }` deletes the matching records. Unlike search, the delete DSL never widens its scope: it throws `IllegalArgumentException` before sending the request if a filter block or group is empty, or if there's no filter or geo condition at all.

### Index settings and rules

```kotlin
import com.algolia.client.dsl.*
import com.algolia.client.dsl.rule.*

@OptIn(AlgoliaExperimentalDsl::class)
val indexSettings = settings {
  searchableAttributes {
    ordered("name")
    unordered("description")
  }
}

@OptIn(AlgoliaExperimentalDsl::class)
val promo = rule("promo") {
  consequence {
    promote { objectID("featured-1", position = 0) }
    hide { +"discontinued-1" }
  }
}
```

A rule requires `consequence`.

### Reusable fragments

Store fragments as lambdas on the DSL receivers and apply them anywhere:

```kotlin
import com.algolia.client.dsl.*
import com.algolia.client.dsl.filter.*

@OptIn(AlgoliaExperimentalDsl::class)
val locale: DSLFilters.() -> Unit = { orFacet { facet("locale", "en-US") } }

@OptIn(AlgoliaExperimentalDsl::class)
val base: DSLQuery.() -> Unit = { hitsPerPage = 20; filters(locale) }
```

The receivers are `DSLQuery`, `DSLBrowse`, `DSLDeleteBy`, `DSLSettings`, `DSLQueryAdditions`, `DSLDeleteByAdditions`, `DSLFilters`, and `DSLFacetFilters`. Some are typealiases of generated classes, so Java code shows the generated names, such as `DSLSearchParamsObject`.

### Composing queries

`DSLQueryComposer` builds one `SearchParamsObject` from fragments contributed by different parts of your code:

```kotlin
@OptIn(AlgoliaExperimentalDsl::class)
val composer = DSLQueryComposer(base = { hitsPerPage = 20 })
composer.add { filters { facet("brand", "Acme") } }        // merged with other fragments
composer.add { ruleContexts { +"mobile" } }
composer.override { sumOrFiltersScores = true }            // applied last
client.searchSingleIndex(indexName, composer.build())
```

- `add { }` fragments are merged per field: `filters` fragments are joined with `AND`, and list fragments are appended.
- `override { }` blocks run after the merge, in call order. The last write wins.
- Blocks run on every `build()`, not when you add them, so captured variables are read at build time.
- `DSLQueryComposer(from = existingParams)` starts from an existing object without modifying it. Fields with `add { }` fragments replace the object's value instead of merging with it.
- `DSLDeleteByComposer` works the same way for `deleteBy`.
- A composer isn't thread-safe.

### Migrating from version 2

| Version 2 | Version 3 |
| --- | --- |
| `index.search(query)` | `client.searchSingleIndex(indexName) { }` |
| `index.browse(query)` | `client.browse(indexName, browse { })` |
| `index.deleteObjectsBy(DeleteByQuery().apply { … })` | `client.deleteBy(indexName) { filters { } }` |
| `initIndex` | removed: pass the index name to each method |
| `Query(...)`, `query { }` | `query { }` → `SearchParamsObject` |
| `Settings` | `settings { }` → `IndexSettings` |
| `Attribute("x")`, `UserToken("u")` | `"x"`, `"u"` |
| `facetFilters { }`, `numericFilters { }`, `tagFilters { }` | `filters { }` |
| unary `!` on a `Filter` | `isNegated = true` on each filter |
| `Language.English` | `SupportedLanguage.En` |
| `Distinct(1)`, `TypoTolerance.Min` | `Distinct.of(1)`, `TypoTolerance.of(TypoToleranceEnum.Min)` |
| `IgnorePlurals.True`, `RemoveStopWords.True` | `IgnorePlurals.of(true)`, `RemoveStopWords.of(true)` |
| `ResponseFields.Hits` | `+"hits"` |

`DSLFilters`, `DSLFacetFilters`, the filter and group types, and the list and settings receivers keep their version 2 names. `DSLConditions`, `DSLPromotions`, and `DSLObjectIDs` keep their names but have different members.

#### Behavior changes

These compile but can change what's sent to the engine:

- **Filter quoting.** Version 2 always quoted string values in `filters` and didn't escape `\`. Version 3 quotes only when needed and escapes `\` and `"`. Update tests that compare generated filter strings.
- **Grouping.** `and { }` no longer adds parentheses: it's flattened into the top-level `AND`. Groups keep their call order, and duplicates are no longer removed.
- **Optional filters.** `optionalFilters` doesn't use the `filters` syntax: each entry is matched literally as `attribute:value`. Version 3 sends quotes in values as-is (version 2 escaped them as `\"`) and escapes a leading `-` as `\-`, so the engine doesn't read it as a negation. The version 2 `escape` parameter is gone.
- **Delete by.** Version 2 silently dropped empty filter groups. Version 3 throws in `deleteBy` rather than deleting more records than intended.
- **Search method.** `SearchClient.search` is multi-query. Use `searchSingleIndex` for a single index.

See the [Kotlin upgrade guide](https://www.algolia.com/doc/libraries/sdk/upgrade/kotlin).

## ❓ Troubleshooting

Encountering an issue? Before reaching out to support, we recommend heading to our [FAQ](https://support.algolia.com/hc/sections/15061037630609-API-Client-FAQs) where you will find answers for the most common issues and gotchas with the client.

## Use the Dockerfile

If you want to contribute to this project without installing all its dependencies, you can use our Docker image. Please check our [dedicated guide](DOCKER_README.md) to learn more.

## 📄 License

The Algolia Kotlin API Client is an open-sourced software licensed under the [MIT license](LICENSE).
