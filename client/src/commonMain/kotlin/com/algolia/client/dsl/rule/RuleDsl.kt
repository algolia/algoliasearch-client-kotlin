@file:OptIn(AlgoliaExperimentalDsl::class)

package com.algolia.client.dsl.rule

import com.algolia.client.dsl.AlgoliaExperimentalDsl
import com.algolia.client.dsl.DSLList
import com.algolia.client.dsl.DSLParameters
import com.algolia.client.dsl.DSLValues
import com.algolia.client.dsl.generated.DSLCondition
import com.algolia.client.dsl.generated.DSLConsequence
import com.algolia.client.dsl.generated.DSLConsequenceParams
import com.algolia.client.dsl.generated.DSLRule
import com.algolia.client.model.search.Condition
import com.algolia.client.model.search.ConsequenceHide
import com.algolia.client.model.search.ConsequenceQuery
import com.algolia.client.model.search.ConsequenceRedirect
import com.algolia.client.model.search.Promote
import com.algolia.client.model.search.PromoteObjectID
import com.algolia.client.model.search.PromoteObjectIDs
import com.algolia.client.model.search.Rule

/**
 * Constructs a [Rule] from a [DSLRule] block. Throws [IllegalArgumentException] when `objectID` or
 * `consequence` is missing. Last write wins.
 */
@AlgoliaExperimentalDsl
public fun rule(block: DSLRule.() -> Unit): Rule = DSLRule().apply(block).build()

/** Constructs a [Rule] with [objectID] set before [block] runs. Last write wins. */
@AlgoliaExperimentalDsl
public fun rule(objectID: String, block: DSLRule.() -> Unit = {}): Rule = rule {
  this.objectID = objectID
  block()
}

/** Sets [DSLRule.conditions] from [block]. Last write wins. */
@AlgoliaExperimentalDsl
public fun DSLRule.conditions(block: DSLConditions.() -> Unit) {
  conditions = DSLConditions().apply(block).build()
}

/** Builds a [List] of [Condition] values: `condition { }` or `+condition`. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLConditions internal constructor() : DSLValues<Condition>() {
  /** Adds a [Condition] from [block]. */
  public fun condition(block: DSLCondition.() -> Unit) {
    values += DSLCondition().apply(block).build()
  }
}

/** Sets [DSLCondition.pattern] to `{facet:ATTRIBUTE}` for [attribute]. */
@AlgoliaExperimentalDsl
public fun DSLCondition.facetPattern(attribute: String) {
  pattern = "{facet:$attribute}"
}

/** Builds a list of [Promote] values. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLPromotions internal constructor() : DSLList<Promote>() {
  /** Adds a single-record promotion at [position]. */
  public fun objectID(objectID: String, position: Int) {
    values += Promote.of(PromoteObjectID(objectID, position))
  }

  /** Adds a group promotion of [objectIDs] at [position]. */
  public fun objectIDs(objectIDs: List<String>, position: Int) {
    values += Promote.of(PromoteObjectIDs(objectIDs, position))
  }
}

/** Builds a list of hidden records: `+objectID`. */
@DSLParameters
@AlgoliaExperimentalDsl
public class DSLObjectIDs internal constructor() : DSLList<ConsequenceHide>() {
  /** Adds this object ID to the hide list. */
  public operator fun String.unaryPlus() {
    values += ConsequenceHide(this)
  }
}

/** Sets [DSLConsequence.promote] from [block]. Last write wins. */
@AlgoliaExperimentalDsl
public fun DSLConsequence.promote(block: DSLPromotions.() -> Unit) {
  promote = DSLPromotions().apply(block).build()
}

/** Sets [DSLConsequence.hide] from [block]. Last write wins. */
@AlgoliaExperimentalDsl
public fun DSLConsequence.hide(block: DSLObjectIDs.() -> Unit) {
  hide = DSLObjectIDs().apply(block).build()
}

/** Sets [DSLConsequence.redirect] to [indexName]. Last write wins. */
@AlgoliaExperimentalDsl
public fun DSLConsequence.redirect(indexName: String) {
  redirect = ConsequenceRedirect(indexName)
}

/** Sets [DSLConsequenceParams.query] to a replacement query string. Last write wins. */
@AlgoliaExperimentalDsl
public fun DSLConsequenceParams.query(value: String) {
  query = ConsequenceQuery.of(value)
}
