package com.algolia.search.dsl

import com.algolia.search.model.rule.RuleQuery


public fun ruleQuery(query: String? = null, block: RuleQuery.() -> Unit): RuleQuery {
    return RuleQuery(query = query).apply(block)
}