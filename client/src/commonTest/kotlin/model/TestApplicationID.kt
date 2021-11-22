package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ApplicationID
import runFailWith
import kotlin.test.Test

internal class TestApplicationID {

    @Test
    fun rawShouldNotBeEmpty() {
        runFailWith<EmptyStringException> { ApplicationID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        runFailWith<EmptyStringException> { ApplicationID(" ") }
    }
}
