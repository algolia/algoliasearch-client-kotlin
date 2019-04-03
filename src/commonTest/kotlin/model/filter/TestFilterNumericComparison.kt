package model.filter

import attributeA
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterConverter
import com.algolia.search.model.filter.NumericOperator
import shouldEqual
import kotlin.test.Test


internal class TestFilterNumericComparison {

    private val lesser = Filter.Numeric(attributeA, NumericOperator.Lesser, 5.0)
    private val lesserOrEquals = Filter.Numeric(attributeA, NumericOperator.LesserOrEquals, 5.0)
    private val equals = Filter.Numeric(attributeA, NumericOperator.Equals, 5.0)
    private val notEquals = Filter.Numeric(attributeA, NumericOperator.NotEquals, 5.0)
    private val greater = Filter.Numeric(attributeA, NumericOperator.Greater, 5.0)
    private val greaterOrEquals = Filter.Numeric(attributeA, NumericOperator.GreaterOrEquals, 5.0)

    @Test
    fun sql() {
        FilterConverter.SQL(lesser) shouldEqual  "\"attributeA\" < 5.0"
        FilterConverter.SQL(lesserOrEquals) shouldEqual  "\"attributeA\" <= 5.0"
        FilterConverter.SQL(equals) shouldEqual  "\"attributeA\" = 5.0"
        FilterConverter.SQL(notEquals) shouldEqual  "\"attributeA\" != 5.0"
        FilterConverter.SQL(greater) shouldEqual  "\"attributeA\" > 5.0"
        FilterConverter.SQL(greaterOrEquals) shouldEqual  "\"attributeA\" >= 5.0"
        FilterConverter.SQL(!lesser) shouldEqual "NOT \"attributeA\" < 5.0"
    }

    @Test
    fun legacy() {
        FilterConverter.Legacy(lesser) shouldEqual  listOf("\"attributeA\" < 5.0")
        FilterConverter.Legacy(lesserOrEquals) shouldEqual  listOf("\"attributeA\" <= 5.0")
        FilterConverter.Legacy(equals) shouldEqual  listOf("\"attributeA\" = 5.0")
        FilterConverter.Legacy(notEquals) shouldEqual  listOf("\"attributeA\" != 5.0")
        FilterConverter.Legacy(greater) shouldEqual  listOf("\"attributeA\" > 5.0")
        FilterConverter.Legacy(greaterOrEquals) shouldEqual  listOf("\"attributeA\" >= 5.0")

        FilterConverter.Legacy(!lesser) shouldEqual  listOf("\"attributeA\" >= 5.0")
        FilterConverter.Legacy(!lesserOrEquals) shouldEqual  listOf("\"attributeA\" > 5.0")
        FilterConverter.Legacy(!equals) shouldEqual  listOf("\"attributeA\" != 5.0")
        FilterConverter.Legacy(!notEquals) shouldEqual  listOf("\"attributeA\" = 5.0")
        FilterConverter.Legacy(!greater) shouldEqual  listOf("\"attributeA\" <= 5.0")
        FilterConverter.Legacy(!greaterOrEquals) shouldEqual  listOf("\"attributeA\" < 5.0")
    }
}