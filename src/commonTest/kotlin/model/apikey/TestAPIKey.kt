package model.apikey

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.APIKey
import kotlin.test.Test
import shouldFailWith


internal class TestAPIKey {

    @Test
    fun rawShouldNotBeEmpty() {
        EmptyStringException::class shouldFailWith { APIKey("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        EmptyStringException::class shouldFailWith { APIKey(" ") }
    }
}