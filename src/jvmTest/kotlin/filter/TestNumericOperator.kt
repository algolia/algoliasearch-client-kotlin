package filter

import com.algolia.search.filter.NumericOperator
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
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