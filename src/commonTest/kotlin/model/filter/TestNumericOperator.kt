package model.filter

import com.algolia.search.model.filter.NumericOperator
import shouldEqual
import kotlin.test.Test


internal class TestNumericOperator {

    @Test
    fun raw() {
        NumericOperator.Lesser.raw shouldEqual "<"
        NumericOperator.LesserOrEquals.raw shouldEqual "<="
        NumericOperator.Equals.raw shouldEqual "="
        NumericOperator.NotEquals.raw shouldEqual "!="
        NumericOperator.GreaterOrEquals.raw shouldEqual ">="
        NumericOperator.Greater.raw shouldEqual ">"
    }
}