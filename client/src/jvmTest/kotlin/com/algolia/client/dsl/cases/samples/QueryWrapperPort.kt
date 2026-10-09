@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.cases.samples

import com.algolia.client.dsl.*
import com.algolia.client.dsl.filter.*
import com.algolia.client.model.search.*

internal class QueryWrapper(private val base: DSLQuery.() -> Unit = {}) {

  interface Additive {
    fun restrictSearchableAttributes(block: DSLAttributes.() -> Unit)

    fun attributesToHighlight(block: DSLAttributes.() -> Unit)

    fun filters(block: DSLFilters.() -> Unit)

    fun optionalFilters(block: DSLFacetFilters.() -> Unit)

    fun ruleContexts(block: DSLStrings.() -> Unit)
  }

  private val restrict = mutableListOf<DSLAttributes.() -> Unit>()
  private val highlight = mutableListOf<DSLAttributes.() -> Unit>()
  private val hardFilters = mutableListOf<DSLFilters.() -> Unit>()
  private val optional = mutableListOf<DSLFacetFilters.() -> Unit>()
  private val contexts = mutableListOf<DSLStrings.() -> Unit>()
  private val overrides = mutableListOf<DSLQuery.() -> Unit>()

  fun add(block: Additive.() -> Unit) {
    block(
      object : Additive {
        override fun restrictSearchableAttributes(block: DSLAttributes.() -> Unit) {
          restrict += block
        }

        override fun attributesToHighlight(block: DSLAttributes.() -> Unit) {
          highlight += block
        }

        override fun filters(block: DSLFilters.() -> Unit) {
          hardFilters += block
        }

        override fun optionalFilters(block: DSLFacetFilters.() -> Unit) {
          optional += block
        }

        override fun ruleContexts(block: DSLStrings.() -> Unit) {
          contexts += block
        }
      }
    )
  }

  fun override(block: DSLQuery.() -> Unit) {
    overrides += block
  }

  fun build(): SearchParamsObject = query {
    base()
    restrictSearchableAttributes { restrict.forEach { it() } }
    attributesToHighlight { highlight.forEach { it() } }
    filters { hardFilters.forEach { it() } }
    optionalFilters { optional.forEach { it() } }
    ruleContexts { contexts.forEach { it() } }
    overrides.forEach { it() }
  }
}
