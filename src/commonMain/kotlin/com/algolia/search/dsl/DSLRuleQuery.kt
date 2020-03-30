package com.algolia.search.dsl

import com.algolia.search.model.rule.RuleQuery

/**
 * Create a [RuleQuery] with [block] and an optional [query].
 */
public fun ruleQuery(query: String? = null, block: RuleQuery.() -> Unit): RuleQuery {
    return RuleQuery(query = query).apply(block)
}
