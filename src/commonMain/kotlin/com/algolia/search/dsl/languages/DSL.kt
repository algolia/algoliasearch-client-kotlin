package com.algolia.search.dsl.languages

import com.algolia.search.model.search.Language

/**
 * DSL for a list of [Language].
 */
public fun languages(block: DSLLanguage.() -> Unit): List<Language> {
    return DSLLanguage(block)
}
