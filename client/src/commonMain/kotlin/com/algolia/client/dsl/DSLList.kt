@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl

import com.algolia.client.model.search.SupportedLanguage

/** Base of the DSL list receivers. */
@DSLParameters
@AlgoliaExperimentalDsl
public open class DSLList<T> internal constructor() {
  internal val values: MutableList<T> = mutableListOf()

  internal fun build(): List<T> = values.toList()
}

/** Builds a list parameter: `+value`, `+listOf(a, b)`. */
@DSLParameters
@AlgoliaExperimentalDsl
public open class DSLValues<T> internal constructor() : DSLList<T>() {
  public operator fun T.unaryPlus() {
    values.add(this)
  }

  public operator fun Iterable<T>.unaryPlus() {
    values.addAll(this)
  }
}

@AlgoliaExperimentalDsl
public typealias DSLStrings = DSLValues<String>

@AlgoliaExperimentalDsl
public typealias DSLAttributes = DSLStrings

@AlgoliaExperimentalDsl
public typealias DSLLanguage = DSLValues<SupportedLanguage>
