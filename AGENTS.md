# KOTLIN CLIENT - AI AGENT INSTRUCTIONS

## ⚠️ CRITICAL: CHECK YOUR REPOSITORY FIRST

Before making ANY changes, verify you're in the correct repository:

```bash
git remote -v
```

- ✅ **CORRECT**: `origin .../algolia/api-clients-automation.git` → You may proceed
- ❌ **WRONG**: `origin .../algolia/algoliasearch-client-kotlin.git` → STOP! This is the PUBLIC repository

**If you're in `algoliasearch-client-kotlin`**: Do NOT make changes here. All changes must go through `api-clients-automation`. PRs and commits made directly to the public repo will be discarded on next release.

## ⚠️ BEFORE ANY EDIT: Check If File Is Generated

Before editing ANY file, verify it's hand-written by checking `config/generation.config.mjs`:

```javascript
// In generation.config.mjs - patterns WITHOUT '!' are GENERATED (do not edit)
'clients/algoliasearch-client-kotlin/client/src/commonMain/kotlin/com/algolia/client/api/**',   // Generated
'clients/algoliasearch-client-kotlin/client/src/commonMain/kotlin/com/algolia/client/model/**', // Generated
// Most directories are hand-written by default (note the '!' pattern)
```

**Hand-written (safe to edit):**

- `client/src/commonMain/kotlin/com/algolia/client/configuration/**` - Configuration
- `client/src/commonMain/kotlin/com/algolia/client/exception/**` - Exceptions
- `client/src/commonMain/kotlin/com/algolia/client/extensions/**` - Kotlin extensions
- `client/src/commonMain/kotlin/com/algolia/client/transport/**` - HTTP transport

**Generated (DO NOT EDIT):**

- `client/src/commonMain/kotlin/com/algolia/client/api/**` - API clients
- `client/src/commonMain/kotlin/com/algolia/client/model/**` - API models
- `client/src/commonMain/kotlin/com/algolia/client/BuildConfig.kt`
- `gradle.properties`

## Language Conventions

### Naming

- **Files**: `PascalCase.kt` matching class name
- **Classes/Interfaces**: `PascalCase`
- **Functions/Properties**: `camelCase`
- **Constants**: `UPPER_SNAKE_CASE` or `camelCase` for object properties

### Formatting

- Spotless with ktlint
- Run: `yarn cli format kotlin clients/algoliasearch-client-kotlin`

### Kotlin Idioms

- Use `data class` for DTOs
- Prefer `val` over `var` (immutability)
- Use `?.` safe calls and `?:` elvis operator
- Extension functions for utilities
- Coroutines for async operations

### Dependencies

- **HTTP**: Ktor client (multiplatform)
- **JSON**: Kotlinx.serialization
- **Build**: Gradle with Kotlin DSL
- **Coroutines**: kotlinx.coroutines

## Client Patterns

### Multiplatform Architecture

```kotlin
// commonMain - shared code
// jvmMain - JVM-specific
// jsMain - JavaScript-specific (Node/Browser)
// Platform-specific transport implementations
```

### Transport Layer

```kotlin
// client/src/commonMain/kotlin/com/algolia/client/transport/
internal class Requester(
    private val httpClient: HttpClient,
    private val hosts: List<RetryableHost>,
)

// Retry logic in extensions/internal/Retry.kt
internal suspend fun <T> Requester.retry(...)
```

### Configuration

```kotlin
// ClientOptions for client configuration
data class ClientOptions(
    val hosts: List<Host>? = null,
    val readTimeout: Duration = 5.seconds,
    val writeTimeout: Duration = 30.seconds,
    val headers: Map<String, String> = emptyMap(),
)
```

### Coroutines

```kotlin
// All API methods are suspend functions
suspend fun search(params: SearchParams): SearchResponse

// Use from coroutine scope
runBlocking {
    val response = client.search(params)
}

// Or with custom scope
scope.launch {
    val response = client.search(params)
}
```

## Common Gotchas

### Suspend Functions

```kotlin
// WRONG - can't call suspend outside coroutine
val response = client.search(params)

// CORRECT - use runBlocking or coroutine scope
val response = runBlocking { client.search(params) }

// Or in existing coroutine
suspend fun myFunction() {
    val response = client.search(params)
}
```

### Nullability

```kotlin
// Kotlin null safety
val hits = response.hits ?: emptyList()
val first = response.hits?.firstOrNull()

// Smart cast after null check
if (response.hits != null) {
    response.hits.forEach { /* ... */ }
}
```

### Data Classes

```kotlin
// Use copy for immutable updates
val updated = params.copy(hitsPerPage = 20)
```

### Platform Differences

```kotlin
// Some APIs differ by platform
// Check expect/actual declarations
expect fun platformSpecificFunction()

// JVM implementation
actual fun platformSpecificFunction() { /* JVM code */ }
```

### Serialization

```kotlin
// Uses Kotlinx.serialization
@Serializable
data class MyModel(
    @SerialName("objectID") val objectId: String,
)
```

## Build & Test Commands

```bash
# From repo root (api-clients-automation)
yarn cli build clients kotlin                  # Build Kotlin client
yarn cli cts generate kotlin                   # Generate CTS tests
yarn cli cts run kotlin                        # Run CTS tests
yarn cli playground kotlin search              # Interactive playground
yarn cli format kotlin clients/algoliasearch-client-kotlin

# From client directory
cd clients/algoliasearch-client-kotlin
./gradlew build                                # Build all targets
./gradlew jvmTest                              # Run JVM tests
./gradlew spotlessApply                        # Apply formatting
```
