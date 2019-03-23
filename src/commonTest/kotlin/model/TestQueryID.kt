package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.QueryID
import kotlin.test.Test
import shouldFailWith


internal class TestQueryID {

    @Test
    fun rawShouldNotBeEmpty() {
        EmptyStringException::class shouldFailWith { QueryID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        EmptyStringException::class shouldFailWith { QueryID(" ") }
    }
}