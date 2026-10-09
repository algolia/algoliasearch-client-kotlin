@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.cases.samples

import com.algolia.client.dsl.*
import com.algolia.client.dsl.filter.*
import com.algolia.client.model.search.*

internal fun languageOf(locale: String): SupportedLanguage =
  when (locale.substringBefore('-')) {
    "en" -> SupportedLanguage.En
    "fr" -> SupportedLanguage.Fr
    else -> error("No SupportedLanguage for $locale")
  }

internal fun applyLocale(wrapper: DSLQueryComposer, primary: String, secondary: String?) {
  wrapper.add {
    filters {
      orFacet {
        facet("locale", primary)
        secondary?.let { facet("locale", it) }
      }
    }
  }
  wrapper.override {
    queryLanguages {
      +languageOf(primary)
      secondary?.let { +languageOf(it) }
    }
  }
}

internal fun applySearchableTitle(wrapper: DSLQueryComposer) {
  wrapper.add {
    restrictSearchableAttributes { +"title" }
    attributesToHighlight { +"title" }
  }
}

internal fun applyBoost(wrapper: DSLQueryComposer) {
  wrapper.add { optionalFilters { or { facet("isFeatured", true, score = 500) } } }
  wrapper.override { sumOrFiltersScores = true }
}

internal fun assemble(contributors: List<(DSLQueryComposer) -> Unit>): SearchParamsObject {
  val wrapper = DSLQueryComposer()
  contributors.forEach { it(wrapper) }
  return wrapper.build()
}

internal class SearchRequest(val filters: Map<String, String>)

internal fun DSLQueryComposer.setSuggestionType(request: SearchRequest) {
  request.filters[SUGGESTION_TYPE]?.let {
    add { filters { and { addFacet(SUGGESTION_TYPE, it) } } }
  }
}

internal fun DSLQueryComposer.setPriority(request: SearchRequest) {
  request.filters[PRIORITY]?.toIntOrNull()?.let {
    add { filters { and { addFacet(PRIORITY, it) } } }
  }
}

internal fun DSLQueryComposer.setPinned() {
  add { filters { and { addFacet(IS_PINNED, IsPinned.FALSE.value) } } }
}
