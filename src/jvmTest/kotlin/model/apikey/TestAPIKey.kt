package model.apikey

import com.algolia.search.model.APIKey
import com.algolia.search.exception.EmptyStringException
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue


@RunWith(JUnit4::class)
internal class TestAPIKey {

    @Test
    fun empty() {
        var isThrown = false
        try {
            APIKey("")
        } catch (exception: EmptyStringException) {
            isThrown = true
        }
        isThrown.shouldBeTrue()
    }
}