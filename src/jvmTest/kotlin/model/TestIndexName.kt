package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.IndexName
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import shouldFailWith


@RunWith(JUnit4::class)
internal class TestIndexName {

    @Test
    fun empty() {
        EmptyStringException::class shouldFailWith { IndexName("") }
    }

    @Test
    fun pathIndexes() {
        val index = IndexName("somePath")

        index.toPath() shouldEqual "/1/indexes/somePath"
        index.toPath("/someSuffix") shouldEqual "/1/indexes/somePath/someSuffix"
    }
}