package com.algolia.search.dsl.rule

import com.algolia.search.model.rule.Rule


public fun rules(block: DSLRules.() -> Unit): List<Rule> {
    return DSLRules(block)
}