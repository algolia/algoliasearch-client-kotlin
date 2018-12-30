package query

import client.query.helper.NumericOperator
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
class TestNumericOperator {

    @Test
    fun raw() {
        "<" shouldEqual NumericOperator.Lesser.raw
        "<=" shouldEqual NumericOperator.LesserOrEqual.raw
        "=" shouldEqual NumericOperator.Equals.raw
        "!=" shouldEqual NumericOperator.NotEquals.raw
        ">=" shouldEqual NumericOperator.GreaterOrEqual.raw
        ">" shouldEqual NumericOperator.Greater.raw
    }
}