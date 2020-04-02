package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.Attribute
import shouldFailWith
import kotlin.test.Test

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
