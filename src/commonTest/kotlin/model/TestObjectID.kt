package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ObjectID
import kotlin.test.Test
import shouldFailWith


internal class TestObjectID {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { ObjectID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { ObjectID(" ") }
    }
}