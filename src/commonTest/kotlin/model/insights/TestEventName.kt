package model.insights

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.insights.EventName
import shouldEqual
import shouldFailWith
import kotlin.test.Test

internal class TestEventName {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { EventName("") }
    }

    @Test
    fun rawShouldNotBeLongerThan64() {
        val stringLongerThan64 = buildString {
            repeat(65) { append("a") }
        }
        val stringEqualTo64 = buildString {
            repeat(64) { append("a") }
        }
        val stringShorterThan64 = buildString {
            repeat(63) { append("a") }
        }
        shouldFailWith<IllegalArgumentException> { EventName(stringLongerThan64) }

        EventName(stringEqualTo64).raw.length shouldEqual 64
        EventName(stringShorterThan64).raw.length shouldEqual 63
    }
}
