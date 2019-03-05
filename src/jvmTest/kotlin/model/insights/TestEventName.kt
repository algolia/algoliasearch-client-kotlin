package model.insights

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.insights.EventName
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import shouldFailWith


@RunWith(JUnit4::class)
internal class TestEventName {

    @Test
    fun rawShouldNotBeEmpty() {
        EmptyStringException::class shouldFailWith { EventName("") }
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
        IllegalArgumentException::class shouldFailWith { EventName(stringLongerThan64) }

        EventName(stringEqualTo64).raw.length shouldEqual 64
        EventName(stringShorterThan64).raw.length shouldEqual 63
    }
}