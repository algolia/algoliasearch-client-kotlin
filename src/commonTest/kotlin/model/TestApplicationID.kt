package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ApplicationID
import kotlin.test.Test
import shouldFailWith


internal class TestApplicationID {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { ApplicationID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { ApplicationID(" ") }
    }
}