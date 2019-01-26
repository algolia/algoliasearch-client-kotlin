package data

import com.algolia.search.saas.model.IndexName
import com.algolia.search.saas.exception.EmptyStringException
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldEqual


@RunWith(JUnit4::class)
internal class TestIndexName {

    @Test
    fun empty() {
        var isThrown = false
        try {
            IndexName("")
        } catch (exception: EmptyStringException) {
            isThrown = true
        }
        isThrown.shouldBeTrue()
    }

    @Test
    fun pathIndexes() {
        val index = IndexName("somePath")

        index.pathIndexes() shouldEqual "/1/indexes/somePath"
        index.pathIndexes("/someSuffix") shouldEqual "/1/indexes/somePath/someSuffix"
    }
}