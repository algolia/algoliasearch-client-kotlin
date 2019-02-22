package model.apikey

import com.algolia.search.exception.EmptyStringException
import com.algolia.search.model.APIKey
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldFailWith


@RunWith(JUnit4::class)
internal class TestAPIKey {

    @Test
    fun rawShouldNotBeEmpty() {
        EmptyStringException::class shouldFailWith { APIKey("") }
    }
}