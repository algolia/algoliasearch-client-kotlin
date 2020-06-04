package model.filter

import attributeA
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterConverter
import shouldEqual
import kotlin.test.Test

internal class TestFilterNumericRange {

    private val filterNumericInt = Filter.Numeric(attributeA, 0 until 6)
    private val filterNumericLong = Filter.Numeric(attributeA, 0L..6L)
    private val filterFloat = Filter.Numeric(attributeA, 0f, 6f)
    private val filterDouble = Filter.Numeric(attributeA, 0f, 6f)

    @Test
    fun sql() {
        FilterConverter.SQL(filterNumericInt) shouldEqual "\"attributeA\":0 TO 5"
        FilterConverter.SQL(filterNumericLong) shouldEqual "\"attributeA\":0 TO 6"
        FilterConverter.SQL(filterFloat) shouldEqual "\"attributeA\":0.0 TO 6.0"
        FilterConverter.SQL(filterDouble) shouldEqual "\"attributeA\":0.0 TO 6.0"
        FilterConverter.SQL(!filterDouble) shouldEqual "NOT \"attributeA\":0.0 TO 6.0"
    }

    @Test
    fun legacy() {
        FilterConverter.Legacy(filterNumericInt) shouldEqual listOf("\"attributeA\" >= 0", "\"attributeA\" <= 5")
        FilterConverter.Legacy(filterNumericLong) shouldEqual listOf("\"attributeA\" >= 0", "\"attributeA\" <= 6")
        FilterConverter.Legacy(filterFloat) shouldEqual listOf("\"attributeA\" >= 0.0", "\"attributeA\" <= 6.0")
        FilterConverter.Legacy(filterDouble) shouldEqual listOf("\"attributeA\" >= 0.0", "\"attributeA\" <= 6.0")
        FilterConverter.Legacy(!filterDouble) shouldEqual listOf("\"attributeA\" < 0.0", "\"attributeA\" > 6.0")
    }

    @Test
    fun legacyUnquoted() {
        FilterConverter.Legacy.Unquoted(filterNumericInt) shouldEqual listOf("attributeA >= 0", "attributeA <= 5")
        FilterConverter.Legacy.Unquoted(filterNumericLong) shouldEqual listOf("attributeA >= 0", "attributeA <= 6")
        FilterConverter.Legacy.Unquoted(filterFloat) shouldEqual listOf("attributeA >= 0.0", "attributeA <= 6.0")
        FilterConverter.Legacy.Unquoted(filterDouble) shouldEqual listOf("attributeA >= 0.0", "attributeA <= 6.0")
        FilterConverter.Legacy.Unquoted(!filterDouble) shouldEqual listOf("attributeA < 0.0", "attributeA > 6.0")
    }
}
