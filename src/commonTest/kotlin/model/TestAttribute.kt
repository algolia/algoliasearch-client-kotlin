package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.Attribute
import kotlin.test.Test
import shouldFailWith


internal class TestAttribute {

    @Test
    fun rawShouldNotBeEmpty() {
        EmptyStringException::class shouldFailWith { Attribute("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        EmptyStringException::class shouldFailWith { Attribute(" ") }
    }
}