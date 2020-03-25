package com.algolia.search.model.filter

import com.algolia.search.model.Attribute

/**
 * [Documentation][https://www.algolia.com/doc/guides/managing-results/refine-results/filtering/]
 */
sealed class Filter {

    /**
     * The [Attribute] this filter applies on.
     */
    abstract val attribute: Attribute

    /**
     * Whether or not the filter is negated.
     */
    abstract val isNegated: Boolean

    /**
     * A [Filter.Facet] matches exactly an [attribute] with a [value].
     * An optional [score] allows to assign a priority between several [Filter.Facet] that are evaluated in the same
     * [FilterGroup].
     * [Read further on scoring][https://www.algolia.com/doc/guides/managing-results/refine-results/filtering/in-depth/filter-scoring/#filters-scoring]
     */
    data class Facet internal constructor(
        override val attribute: Attribute,
        override val isNegated: Boolean,
        val value: Value,
        val score: Int? = null
    ) : Filter() {

        sealed class Value {

            /**
             * Filter on a [kotlin.String] value.
             */
            data class String(val raw: kotlin.String) : Value()

            /**
             * Filter on a [kotlin.Boolean] value.
             */
            data class Boolean(val raw: kotlin.Boolean) : Value()

            /**
             * Filter on a [kotlin.Number] value.
             */
            data class Number(val raw: kotlin.Number) : Value()
        }

        constructor(
            attribute: Attribute,
            value: String,
            score: Int? = null,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.String(value), score)

        constructor(
            attribute: Attribute,
            value: Boolean,
            score: Int? = null,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Boolean(value), score)

        constructor(
            attribute: Attribute,
            value: Number,
            score: Int? = null,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Number(value), score)

        /**
         * Operator to negates a [Filter.Facet].
         */
        operator fun not(): Facet {
            return Facet(attribute, true, value, score)
        }
    }

    /**
     * A [Filter.Tag] filters on a specific [value]. It uses a reserved keywords "_tags" as [attribute].
     */
    data class Tag internal constructor(
        override val attribute: Attribute,
        override val isNegated: Boolean,
        val value: String
    ) : Filter() {

        constructor(
            value: String,
            isNegated: Boolean = false
        ) : this(Attribute("_tags"), isNegated, value)

        /**
         * Operator to negates a [Filter.Tag].
         */
        operator fun not(): Tag {
            return Tag(attribute, true, value)
        }
    }

    /**
     * A [Filter.Numeric] filters on a numeric [value].
     */
    data class Numeric(
        override val attribute: Attribute,
        override val isNegated: Boolean,
        val value: Value
    ) : Filter() {

        sealed class Value {

            /**
             * Numeric comparison of a [number] using a [NumericOperator].
             */
            data class Comparison(val operator: NumericOperator, val number: Number) : Value()

            /**
             * A numeric range comprised within a [lowerBound] and an [upperBound].
             */
            data class Range(val lowerBound: Number, val upperBound: Number) : Value()
        }

        constructor(
            attribute: Attribute,
            operator: NumericOperator,
            value: Number,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Comparison(operator, value))

        constructor(
            attribute: Attribute,
            range: IntRange,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Range(range.start, range.endInclusive))

        constructor(
            attribute: Attribute,
            range: LongRange,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Range(range.start, range.endInclusive))

        constructor(
            attribute: Attribute,
            lowerBound: Float,
            upperBound: Float,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Range(lowerBound, upperBound))

        constructor(
            attribute: Attribute,
            lowerBound: Double,
            upperBound: Double,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Range(lowerBound, upperBound))

        /**
         * Operator to negates a [Filter.Numeric].
         */
        operator fun not(): Numeric {
            return Numeric(attribute, true, value)
        }
    }
}
