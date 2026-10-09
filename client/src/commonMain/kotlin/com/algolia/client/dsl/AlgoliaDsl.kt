package com.algolia.client.dsl

/** Marks types that belong to the Algolia Kotlin DSL. */
@DslMarker public annotation class DSLParameters

/** Marks the Algolia Kotlin DSL as experimental. */
@RequiresOptIn(
  level = RequiresOptIn.Level.ERROR,
  message =
    "This DSL is experimental, optional, additive, and not v2 source compatible. The first 3.x minor may change its shape.",
)
@Retention(AnnotationRetention.BINARY)
@Target(
  AnnotationTarget.CLASS,
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY,
  AnnotationTarget.CONSTRUCTOR,
  AnnotationTarget.TYPEALIAS,
)
public annotation class AlgoliaExperimentalDsl
