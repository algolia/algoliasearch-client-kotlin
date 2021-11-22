package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ObjectID
import runFailWith
import kotlin.test.Test

internal class TestObjectID {

    @Test
    fun rawShouldNotBeEmpty() {
        runFailWith<EmptyStringException> { ObjectID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        runFailWith<EmptyStringException> { ObjectID(" ") }
    }
}
