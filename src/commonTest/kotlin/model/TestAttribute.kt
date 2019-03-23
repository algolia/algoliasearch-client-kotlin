package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.Attribute
import kotlin.test.Test
import shouldFailWith


internal class TestAttribute {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { Attribute("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { Attribute(" ") }
    }
}