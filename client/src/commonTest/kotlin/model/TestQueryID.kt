package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.QueryID
import runFailWith
import kotlin.test.Test

internal class TestQueryID {

    @Test
    fun rawShouldNotBeEmpty() {
        runFailWith<EmptyStringException> { QueryID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        runFailWith<EmptyStringException> { QueryID(" ") }
    }
}
