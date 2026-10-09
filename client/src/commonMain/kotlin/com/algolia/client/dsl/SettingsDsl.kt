package com.algolia.client.dsl

import com.algolia.client.dsl.generated.DSLIndexSettings
import com.algolia.client.model.search.IndexSettings

/** Constructs an [IndexSettings] from a [DSLSettings] block. Last write wins. */
@AlgoliaExperimentalDsl
public fun settings(block: DSLSettings.() -> Unit): IndexSettings =
  DSLIndexSettings().apply(block).build()

/** Sets [DSLIndexSettings.searchableAttributes] from [block]. Last write wins. */
@AlgoliaExperimentalDsl
public fun DSLIndexSettings.searchableAttributes(block: DSLSearchableAttributes.() -> Unit) {
  searchableAttributes = DSLSearchableAttributes().apply(block).build()
}

/** Sets [DSLIndexSettings.attributesForFaceting] from [block]. Last write wins. */
@AlgoliaExperimentalDsl
public fun DSLIndexSettings.attributesForFaceting(block: DSLAttributesForFaceting.() -> Unit) {
  attributesForFaceting = DSLAttributesForFaceting().apply(block).build()
}

/** Sets [DSLIndexSettings.customRanking] from [block]. Last write wins. */
@AlgoliaExperimentalDsl
public fun DSLIndexSettings.customRanking(block: DSLCustomRanking.() -> Unit) {
  customRanking = DSLCustomRanking().apply(block).build()
}

/** Sets [DSLIndexSettings.ranking] from [block]. Last write wins. */
@AlgoliaExperimentalDsl
public fun DSLIndexSettings.ranking(block: DSLRanking.() -> Unit) {
  ranking = DSLRanking().apply(block).build()
}

/** Builds searchable-attribute strings for [IndexSettings.searchableAttributes]. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLSearchableAttributes internal constructor() : DSLValues<String>() {
  /** Adds an ordered searchable attribute; several attributes share one priority as `a, b`. */
  public fun ordered(attribute: String, vararg more: String) {
    values +=
      if (more.isEmpty()) attribute else listOf(attribute, *more).joinToString(separator = ", ")
  }

  /** Adds an unordered searchable attribute as `unordered(attribute)`. */
  public fun unordered(attribute: String) {
    values += "unordered($attribute)"
  }
}

/** Builds attribute-for-faceting strings for [IndexSettings.attributesForFaceting]. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLAttributesForFaceting internal constructor() : DSLValues<String>() {
  /** Adds a facet attribute with no modifier. */
  public fun attribute(attribute: String) {
    values += attribute
  }

  /** Adds a filter-only attribute as `filterOnly(attribute)`. */
  public fun filterOnly(attribute: String) {
    values += "filterOnly($attribute)"
  }

  /** Adds a searchable facet attribute as `searchable(attribute)`. */
  public fun searchable(attribute: String) {
    values += "searchable($attribute)"
  }
}

/** Builds custom-ranking strings for [IndexSettings.customRanking]. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLCustomRanking internal constructor() : DSLList<String>() {
  /** Adds an ascending custom-ranking criterion as `asc(attribute)`. */
  public fun asc(attribute: String) {
    values += "asc($attribute)"
  }

  /** Adds a descending custom-ranking criterion as `desc(attribute)`. */
  public fun desc(attribute: String) {
    values += "desc($attribute)"
  }
}

/** Builds ranking-formula strings for [IndexSettings.ranking]. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLRanking internal constructor() : DSLList<String>() {
  /** Adds the `typo` ranking criterion. */
  public fun typo() {
    values += "typo"
  }

  /** Adds the `geo` ranking criterion. */
  public fun geo() {
    values += "geo"
  }

  /** Adds the `words` ranking criterion. */
  public fun words() {
    values += "words"
  }

  /** Adds the `filters` ranking criterion. */
  public fun filters() {
    values += "filters"
  }

  /** Adds the `proximity` ranking criterion. */
  public fun proximity() {
    values += "proximity"
  }

  /** Adds the `attribute` ranking criterion. */
  public fun attribute() {
    values += "attribute"
  }

  /** Adds the `exact` ranking criterion. */
  public fun exact() {
    values += "exact"
  }

  /** Adds the `custom` ranking criterion. */
  public fun custom() {
    values += "custom"
  }

  /** Adds an ascending sort criterion as `asc(attribute)`. */
  public fun asc(attribute: String) {
    values += "asc($attribute)"
  }

  /** Adds a descending sort criterion as `desc(attribute)`. */
  public fun desc(attribute: String) {
    values += "desc($attribute)"
  }
}
