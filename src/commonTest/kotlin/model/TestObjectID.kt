package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ObjectID
import kotlin.test.Test
import shouldFailWith


internal class TestObjectID {

    @Test
    fun rawShouldNotBeEmpty() {
        EmptyStringException::class shouldFailWith { ObjectID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        EmptyStringException::class shouldFailWith { ObjectID(" ") }
    }
}