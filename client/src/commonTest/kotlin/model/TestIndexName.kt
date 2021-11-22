package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.IndexName
import shouldEqual
import runFailWith
import kotlin.test.Test

internal class TestIndexName {

    @Test
    fun rawShouldNotBeEmpty() {
        runFailWith<EmptyStringException> { IndexName("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        runFailWith<EmptyStringException> { IndexName(" ") }
    }

    @Test
    fun pathIndexes() {
        val index = IndexName("somePath")

        index.toPath() shouldEqual "1/indexes/somePath"
        index.toPath("/someSuffix") shouldEqual "1/indexes/somePath/someSuffix"
    }
}
