package com.algolia.search.dsl.rule

import com.algolia.search.model.rule.Rule

/**
 * Create a [List] of [Rule] with a [DSLRules].
 */
fun rules(block: DSLRules.() -> Unit): List<Rule> {
    return DSLRules(block)
}
