@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.dsl.generated.DSLBrowseParamsObject
import com.algolia.client.dsl.generated.DSLDeleteByParams
import com.algolia.client.dsl.generated.DSLDeleteByParamsAdditions
import com.algolia.client.dsl.generated.DSLIndexSettings
import com.algolia.client.dsl.generated.DSLSearchParamsObject
import com.algolia.client.dsl.generated.DSLSearchParamsObjectAdditions

/** Receiver of [query] and [searchSingleIndex]; Kotlin source alias of [DSLSearchParamsObject]. */
@AlgoliaExperimentalDsl
public typealias DSLQuery = DSLSearchParamsObject

/** Receiver of [browse]; Kotlin source alias of [DSLBrowseParamsObject]. */
@AlgoliaExperimentalDsl
public typealias DSLBrowse = DSLBrowseParamsObject

/** Receiver of [deleteBy]; Kotlin source alias of [DSLDeleteByParams]. */
@AlgoliaExperimentalDsl
public typealias DSLDeleteBy = DSLDeleteByParams

/** Receiver of [settings] and [setSettings]; Kotlin source alias of [DSLIndexSettings]. */
@AlgoliaExperimentalDsl
public typealias DSLSettings = DSLIndexSettings

/** Receiver of [DSLQueryComposer.add]; Kotlin source alias of [DSLSearchParamsObjectAdditions]. */
@AlgoliaExperimentalDsl
public typealias DSLQueryAdditions = DSLSearchParamsObjectAdditions

/** Receiver of [DSLDeleteByComposer.add]; Kotlin source alias of [DSLDeleteByParamsAdditions]. */
@AlgoliaExperimentalDsl
public typealias DSLDeleteByAdditions = DSLDeleteByParamsAdditions
