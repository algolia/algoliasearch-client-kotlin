package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ObjectID
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue
import shouldFailWith


@RunWith(JUnit4::class)
internal class TestObjectID {

    @Test
    fun empty() {
        EmptyStringException::class shouldFailWith { ObjectID("") }
    }
}