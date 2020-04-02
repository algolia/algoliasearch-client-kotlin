package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ApplicationID
import shouldFailWith
import kotlin.test.Test

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
