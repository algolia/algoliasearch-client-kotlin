package model.apikey

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.APIKey
import shouldFailWith
import kotlin.test.Test

internal class TestAPIKey {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { APIKey("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { APIKey(" ") }
    }
}
