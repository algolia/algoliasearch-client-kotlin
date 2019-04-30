package com.algolia.search.model.filter

import com.algolia.search.model.Attribute


public sealed class Filter {

    public abstract val attribute: Attribute

    public abstract val isNegated: Boolean

    public data class Facet internal constructor(
        override val attribute: Attribute,
        override val isNegated: Boolean,
        val value: Value,
        val score: Int? = null
    ) : Filter() {

        sealed class Value {

            data class String(val raw: kotlin.String) : Value()

            data class Boolean(val raw: kotlin.Boolean) : Value()

            data class Number(val raw: kotlin.Number) : Value()
        }

        public constructor(
            attribute: Attribute,
            value: String,
            score: Int? = null,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.String(value), score)

        public constructor(
            attribute: Attribute,
            value: Boolean,
            score: Int? = null,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Boolean(value), score)

        public constructor(
            attribute: Attribute,
            value: Number,
            score: Int? = null,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Number(value), score)

        public operator fun not(): Facet {
            return Facet(attribute, true, value, score)
        }
    }

    public data class Tag internal constructor(
        override val attribute: Attribute,
        override val isNegated: Boolean,
        val value: String
    ) : Filter() {

        public constructor(
            value: String,
            isNegated: Boolean = false
        ) : this(Attribute("_tags"), isNegated, value.escape())

        public operator fun not(): Tag {
            return Tag(attribute, true, value)
        }
    }

    public data class Numeric(
        override val attribute: Attribute,
        override val isNegated: Boolean,
        val value: Value
    ) : Filter() {

        sealed class Value {

            data class Comparison(val operator: NumericOperator, val number: Number) : Value()

            data class Range(val lowerBound: Number, val upperBound: Number) : Value()
        }

        public constructor(
            attribute: Attribute,
            operator: NumericOperator,
            value: Number,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Comparison(operator, value))

        public constructor(
            attribute: Attribute,
            range: IntRange,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Range(range.start, range.endInclusive))

        public constructor(
            attribute: Attribute,
            range: LongRange,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Range(range.start, range.endInclusive))

        public constructor(
            attribute: Attribute,
            lowerBound: Float,
            upperBound: Float,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Range(lowerBound, upperBound))

        public constructor(
            attribute: Attribute,
            lowerBound: Double,
            upperBound: Double,
            isNegated: Boolean = false
        ) : this(attribute, isNegated, Value.Range(lowerBound, upperBound))


        public operator fun not(): Numeric {
            return Numeric(attribute, true, value)
        }
    }
}