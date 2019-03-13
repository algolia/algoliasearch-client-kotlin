package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ObjectID
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldFailWith


@RunWith(JUnit4::class)
internal class TestObjectID {

    @Test
    fun rawShouldNotBeEmpty() {
        EmptyStringException::class shouldFailWith { ObjectID("") }
    }

    @Test
    fun rawShouldNotBeBlank() {
        EmptyStringException::class shouldFailWith { ObjectID(" ") }
    }
}