package com.algolia.search.dsl.rule

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.Attribute
import com.algolia.search.model.ObjectID
import com.algolia.search.model.rule.*
import com.algolia.search.model.search.Query
import kotlinx.serialization.json.JsonObject


@Suppress("PropertyName", "FunctionName")
@DSLParameters
public class DSLRules(
    private val rules: MutableList<Rule> = mutableListOf()
) {

    public val Is = Anchoring.Is
    public val StartsWith = Anchoring.StartsWith
    public val EndsWith = Anchoring.EndsWith
    public val Contains = Anchoring.Contains

    public fun Facet(attribute: String): Pattern.Facet {
        return Facet(Attribute(attribute))
    }

    public fun Facet(attribute: Attribute): Pattern.Facet {
        return Pattern.Facet(attribute)
    }

    public fun Literal(attribute: String): Pattern.Literal {
        return Pattern.Literal(attribute)
    }

    public fun condition(anchoring: Anchoring, pattern: Pattern, context: String? = null): Condition {
        return Condition(anchoring, pattern, context)
    }

    public fun automaticFacetFilters(block: DSLAutomaticFacetFilters.() -> Unit): List<AutomaticFacetFilters> {
        return DSLAutomaticFacetFilters(block)
    }

    public fun edits(block: DSLEdits.() -> Unit): List<Edit> {
        return DSLEdits(block)
    }

    public fun promotions(block: DSLPromotions.() -> Unit): List<Promotion> {
        return DSLPromotions(block)
    }

    public fun timeRanges(block: DSLTimeRanges.() -> Unit): List<TimeRange> {
        return DSLTimeRanges(block)
    }

    public fun consequence(
        automaticFacetFilters: List<AutomaticFacetFilters>? = null,
        automaticOptionalFacetFilters: List<AutomaticFacetFilters>? = null,
        edits: List<Edit>? = null,
        promote: List<Promotion>? = null,
        userData: JsonObject? = null,
        hide: List<ObjectID>? = null,
        query: Query? = null
    ): Consequence {
        return Consequence(
            automaticFacetFilters = automaticFacetFilters,
            automaticOptionalFacetFilters = automaticOptionalFacetFilters,
            edits = edits,
            promote = promote,
            userData = userData,
            hide = hide,
            query = query
        )
    }

    public operator fun Rule.unaryPlus() {
        rules += this
    }

    public fun rule(
        objectID: String,
        condition: Condition,
        consequence: Consequence,
        enabled: Boolean? = null,
        validity: List<TimeRange>? = null,
        description: String? = null
    ): Rule {
        return Rule(ObjectID(objectID), condition, consequence, enabled, validity, description)
    }

    public fun rule(
        objectID: ObjectID,
        condition: Condition,
        consequence: Consequence,
        enabled: Boolean? = null,
        validity: List<TimeRange>? = null,
        description: String? = null
    ): Rule {
        return Rule(objectID, condition, consequence, enabled, validity, description)
    }

    public companion object : DSL<DSLRules, List<Rule>> {

        override operator fun invoke(block: DSLRules.() -> Unit): List<Rule> {
            return DSLRules().apply(block).rules
        }
    }
}