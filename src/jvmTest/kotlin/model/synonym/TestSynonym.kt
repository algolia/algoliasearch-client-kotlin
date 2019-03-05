package model.synonym

import com.algolia.search.exception.EmptyListException
import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import objectIDA
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSynonym {


    @Test
    fun tokenShouldNotBeEmpty() {
        var hasThrown = false
        try {
            Synonym.Placeholder.Token("")
        } catch (exception: EmptyStringException) {
            hasThrown = true
        }
        hasThrown.shouldBeTrue()
    }

    @Test
    fun tokenFormat() {
        Synonym.Placeholder.Token("token").raw shouldEqual "<token>"
    }

    @Test
    fun oneWayInputShouldNotBeEmpty() {
        var hasThrown = false
        try {
            Synonym.OneWay(objectIDA, "", listOf())
        } catch (exception: EmptyStringException) {
            hasThrown = true
        }
        hasThrown.shouldBeTrue()
    }

    @Test
    fun oneWaySynonymsShouldNotBeEmpty() {
        var hasThrown = false
        try {
            Synonym.OneWay(objectIDA, "input", listOf())
        } catch (exception: EmptyListException) {
            hasThrown = true
        }
        hasThrown.shouldBeTrue()
    }

    @Test
    fun multiWaySynonymsShouldNotBeEmpty() {
        var hasThrown = false
        try {
            Synonym.MultiWay(objectIDA, listOf())
        } catch (exception: EmptyListException) {
            hasThrown = true
        }
        hasThrown.shouldBeTrue()
    }

    @Test
    fun alternativeWordShouldNotBeEmpty() {
        var hasThrown = false
        try {
            Synonym.AlternativeCorrections(objectIDA, "", listOf(), SynonymType.Typo.One)
        } catch (exception: EmptyStringException) {
            hasThrown = true
        }
        hasThrown.shouldBeTrue()
    }

    @Test
    fun alternativeCorrectionsShouldNotBeEmpty() {
        var hasThrown = false
        try {
            Synonym.AlternativeCorrections(objectIDA, "word", listOf(), SynonymType.Typo.One)
        } catch (exception: EmptyListException) {
            hasThrown = true
        }
        hasThrown.shouldBeTrue()
    }

    @Test
    fun placeholderReplacementsShouldNotBeEmpty() {
        val token = Synonym.Placeholder.Token("token")
        var hasThrown = false

        try {
            Synonym.Placeholder(objectIDA, token, listOf())
        } catch (exception: EmptyListException) {
            hasThrown = true
        }
        hasThrown.shouldBeTrue()
    }
}