package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.QueryID
import kotlin.test.Test
import shouldFailWith


internal class TestQueryID {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { QueryID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { QueryID(" ") }
    }
}