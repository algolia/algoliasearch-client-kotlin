package model

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.ApplicationID
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldFailWith


@RunWith(JUnit4::class)
internal class TestApplicationID {

    @Test
    fun empty() {
        EmptyStringException::class shouldFailWith { ApplicationID("") }
    }
}