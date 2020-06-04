package model.insights

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.insights.UserToken
import shouldEqual
import shouldFailWith
import kotlin.test.Test

internal class TestUserToken {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { UserToken("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { UserToken(" ") }
    }

    @Test
    fun rawShouldNotBeLongerThan64() {
        val stringShorterThan64 = buildString {
            repeat(63) { append("a") }
        }
        val stringEqualTo64 = buildString {
            repeat(64) { append("a") }
        }
        val stringLongerThan64 = buildString {
            repeat(65) { append("a") }
        }

        UserToken(stringShorterThan64).raw.length shouldEqual 63
        UserToken(stringEqualTo64).raw.length shouldEqual 64
        shouldFailWith<IllegalArgumentException> { UserToken(stringLongerThan64) }
    }

    @Test
    fun rawShouldNotHaveIllegalCharacters() {
        val illegals = listOf(
            "+",
            "a;z",
            "@a",
            "{a}",
            "(a)"
        )
        val legals = listOf(
            "1",
            "a1",
            "a1-",
            "a1-_",
            "a1-_Z"
        )

        illegals.forEach {
            shouldFailWith<IllegalArgumentException> { UserToken(it) }
        }
        legals.forEachIndexed { index, s -> UserToken(s).raw shouldEqual legals[index] }
    }

    @Test
    fun rawCanBeIP() {
        UserToken("1.2.3.4")
        UserToken("2001:db8:3333:4444:5555:6666:7777:8888")
    }
}
