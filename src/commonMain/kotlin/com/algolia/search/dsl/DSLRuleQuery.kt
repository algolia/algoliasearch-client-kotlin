package com.algolia.search.dsl

import com.algolia.search.model.rule.RuleQuery


public fun ruleQuery(query: String? = null, init: RuleQuery.() -> Unit): RuleQuery {
    return RuleQuery(query = query).apply(init)
}