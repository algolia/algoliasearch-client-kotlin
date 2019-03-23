package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ApplicationID
import kotlin.test.Test
import shouldFailWith


internal class TestApplicationID {

    @Test
    fun rawShouldNotBeEmpty() {
        EmptyStringException::class shouldFailWith { ApplicationID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        EmptyStringException::class shouldFailWith { ApplicationID(" ") }
    }
}