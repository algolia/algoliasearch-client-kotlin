package model.synonym

import com.algolia.search.exception.EmptyListException
import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import objectIDA
import shouldEqual
import shouldFailWith
import kotlin.test.Test

internal class TestSynonym {

    @Test
    fun tokenShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { Synonym.Placeholder.Token("") }
        shouldFailWith<EmptyStringException> { Synonym.Placeholder.Token(" ") }
    }

    @Test
    fun tokenFormat() {
        Synonym.Placeholder.Token("token").raw shouldEqual "<token>"
    }

    @Test
    fun oneWayInputShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { Synonym.OneWay(objectIDA, "", listOf()) }
        shouldFailWith<EmptyStringException> { Synonym.OneWay(objectIDA, " ", listOf()) }
    }

    @Test
    fun oneWaySynonymsShouldNotBeEmpty() {
        shouldFailWith<EmptyListException> { Synonym.OneWay(objectIDA, "input", listOf()) }
    }

    @Test
    fun oneWaySynonymsShouldNotBeMoreThan100() {
        shouldFailWith<IllegalArgumentException> { Synonym.OneWay(objectIDA, "input", (0..101).map { "" }) }
    }

    @Test
    fun multiWaySynonymsShouldNotBeEmpty() {
        shouldFailWith<EmptyListException> { Synonym.MultiWay(objectIDA, listOf()) }
    }

    @Test
    fun multiWaySynonymsShouldNotBeMoreThan100() {
        shouldFailWith<IllegalArgumentException> { Synonym.MultiWay(objectIDA, (0..101).map { "" }) }
    }

    @Test
    fun alternativeWordShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> {
            Synonym.AlternativeCorrections(objectIDA, "", listOf(), SynonymType.Typo.One)
            Synonym.AlternativeCorrections(objectIDA, " ", listOf(), SynonymType.Typo.One)
        }
    }

    @Test
    fun alternativeCorrectionsShouldNotBeEmpty() {
        shouldFailWith<EmptyListException> {
            Synonym.AlternativeCorrections(objectIDA, "word", listOf(), SynonymType.Typo.One)
        }
    }

    @Test
    fun placeholderReplacementsShouldNotBeEmpty() {
        val token = Synonym.Placeholder.Token("token")

        shouldFailWith<EmptyListException> {
            Synonym.Placeholder(objectIDA, token, listOf())
        }
    }
}
