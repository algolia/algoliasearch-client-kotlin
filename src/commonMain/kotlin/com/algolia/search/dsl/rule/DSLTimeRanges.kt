package com.algolia.search.dsl.rule

import com.algolia.search.dsl.DSL
import com.algolia.search.dsl.DSLParameters
import com.algolia.search.model.rule.TimeRange


@DSLParameters
public class DSLTimeRanges(
    private val timeRanges: MutableList<TimeRange> = mutableListOf()
) {

    public operator fun LongRange.unaryPlus() {
        +TimeRange(start, endInclusive)
    }

    public operator fun TimeRange.unaryPlus() {
        timeRanges += this
    }

    public companion object : DSL<DSLTimeRanges, List<TimeRange>> {

        override operator fun invoke(block: DSLTimeRanges.() -> Unit): List<TimeRange> {
            return DSLTimeRanges().apply(block).timeRanges
        }
    }
}