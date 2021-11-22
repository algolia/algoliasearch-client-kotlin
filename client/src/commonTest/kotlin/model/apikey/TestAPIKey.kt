package model.apikey

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.APIKey
import runFailWith
import kotlin.test.Test

internal class TestAPIKey {

    @Test
    fun rawShouldNotBeEmpty() {
        runFailWith<EmptyStringException> { APIKey("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        runFailWith<EmptyStringException> { APIKey(" ") }
    }
}
