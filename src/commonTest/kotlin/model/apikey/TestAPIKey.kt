package model.apikey

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.APIKey
import kotlin.test.Test
import shouldFailWith


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