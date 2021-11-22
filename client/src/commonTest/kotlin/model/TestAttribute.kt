package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.Attribute
import runFailWith
import kotlin.test.Test

internal class TestAttribute {

    @Test
    fun rawShouldNotBeEmpty() {
        runFailWith<EmptyStringException> { Attribute("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        runFailWith<EmptyStringException> { Attribute(" ") }
    }
}
