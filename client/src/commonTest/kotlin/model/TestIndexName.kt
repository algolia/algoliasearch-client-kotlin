package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.IndexName
import shouldEqual
import shouldFailWith
import kotlin.test.Test

internal class TestIndexName {

    @Test
    fun rawShouldNotBeEmpty() {
        shouldFailWith<EmptyStringException> { IndexName("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        shouldFailWith<EmptyStringException> { IndexName(" ") }
    }

    @Test
    fun pathIndexes() {
        val index = IndexName("somePath")

        index.toPath() shouldEqual "1/indexes/somePath"
        index.toPath("/someSuffix") shouldEqual "1/indexes/somePath/someSuffix"
    }
}
