package model.synonym

import com.algolia.search.exception.EmptyListException
import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import objectIDA
import runFailWith
import shouldEqual
import kotlin.test.Test

internal class TestSynonym {

    @Test
    fun tokenShouldNotBeEmpty() {
        runFailWith<EmptyStringException> { Synonym.Placeholder.Token("") }
        runFailWith<EmptyStringException> { Synonym.Placeholder.Token(" ") }
    }

    @Test
    fun tokenFormat() {
        Synonym.Placeholder.Token("token").raw shouldEqual "<token>"
    }

    @Test
    fun oneWayInputShouldNotBeEmpty() {
        runFailWith<EmptyStringException> { Synonym.OneWay(objectIDA, "", listOf()) }
        runFailWith<EmptyStringException> { Synonym.OneWay(objectIDA, " ", listOf()) }
    }

    @Test
    fun oneWaySynonymsShouldNotBeEmpty() {
        runFailWith<EmptyListException> { Synonym.OneWay(objectIDA, "input", listOf()) }
    }

    @Test
    fun oneWaySynonymsShouldNotBeMoreThan100() {
        runFailWith<IllegalArgumentException> { Synonym.OneWay(objectIDA, "input", (0..101).map { "" }) }
    }

    @Test
    fun multiWaySynonymsShouldNotBeEmpty() {
        runFailWith<EmptyListException> { Synonym.MultiWay(objectIDA, listOf()) }
    }

    @Test
    fun multiWaySynonymsShouldNotBeMoreThan100() {
        runFailWith<IllegalArgumentException> { Synonym.MultiWay(objectIDA, (0..101).map { "" }) }
    }

    @Test
    fun alternativeWordShouldNotBeEmpty() {
        runFailWith<EmptyStringException> {
            Synonym.AlternativeCorrections(objectIDA, "", listOf(), SynonymType.Typo.One)
            Synonym.AlternativeCorrections(objectIDA, " ", listOf(), SynonymType.Typo.One)
        }
    }

    @Test
    fun alternativeCorrectionsShouldNotBeEmpty() {
        runFailWith<EmptyListException> {
            Synonym.AlternativeCorrections(objectIDA, "word", listOf(), SynonymType.Typo.One)
        }
    }

    @Test
    fun placeholderReplacementsShouldNotBeEmpty() {
        val token = Synonym.Placeholder.Token("token")

        runFailWith<EmptyListException> {
            Synonym.Placeholder(objectIDA, token, listOf())
        }
    }
}
