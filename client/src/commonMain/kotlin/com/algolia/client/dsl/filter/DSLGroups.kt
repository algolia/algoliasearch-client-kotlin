@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.filter

import com.algolia.client.dsl.AlgoliaExperimentalDsl
import com.algolia.client.dsl.DSLParameters

/** `OR` group of facet leaves. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLGroupFacet private constructor(private val leaves: MutableList<Filter.Facet>) :
  DSLFacet by FacetLeafMixin({ leaves.add(it) }) {

  internal constructor() : this(mutableListOf())

  internal fun leaves(): List<Filter.Facet> = leaves.toList()
}

/** `OR` group of tag leaves. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLGroupTag private constructor(private val leaves: MutableList<Filter.Tag>) :
  DSLTag by TagLeafMixin({ leaves.add(it) }) {

  internal constructor() : this(mutableListOf())

  internal fun leaves(): List<Filter.Tag> = leaves.toList()
}

/** `OR` group of numeric leaves. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLGroupNumeric private constructor(private val leaves: MutableList<Filter.Numeric>) :
  DSLNumeric by NumericLeafMixin({ leaves.add(it) }) {

  internal constructor() : this(mutableListOf())

  internal fun leaves(): List<Filter.Numeric> = leaves.toList()
}
