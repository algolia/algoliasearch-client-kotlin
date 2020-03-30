package model.filter

import com.algolia.search.model.filter.NumericOperator
import kotlin.test.Test
import shouldEqual

internal class TestNumericOperator {

    @Test
    fun raw() {
        NumericOperator.Less.raw shouldEqual "<"
        NumericOperator.LessOrEquals.raw shouldEqual "<="
        NumericOperator.Equals.raw shouldEqual "="
        NumericOperator.NotEquals.raw shouldEqual "!="
        NumericOperator.GreaterOrEquals.raw shouldEqual ">="
        NumericOperator.Greater.raw shouldEqual ">"
    }
}
