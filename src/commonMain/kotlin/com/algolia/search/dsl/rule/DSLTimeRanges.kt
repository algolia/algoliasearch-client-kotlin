package com.algolia.search.dsl.rule

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.rule.TimeRange

/**
 * DSL for building a [List] of [TimeRange].
 */
@DSLParameters
class DSLTimeRanges(
    private val timeRanges: MutableList<TimeRange> = mutableListOf()
) {

    /**
     * Convenience method.
     */
    operator fun LongRange.unaryPlus() {
        +TimeRange(start, endInclusive)
    }

    /**
     * Add [this] to [timeRanges]
     */
    operator fun TimeRange.unaryPlus() {
        timeRanges += this
    }

    companion object : DSL<DSLTimeRanges, List<TimeRange>> {

        override operator fun invoke(block: DSLTimeRanges.() -> Unit): List<TimeRange> {
            return DSLTimeRanges().apply(block).timeRanges
        }
    }
}
