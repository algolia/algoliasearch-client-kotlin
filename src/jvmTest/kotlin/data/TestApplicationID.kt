package data

import com.algolia.search.saas.model.ApplicationID
import com.algolia.search.saas.exception.EmptyStringException
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue


@RunWith(JUnit4::class)
internal class TestApplicationID {

    @Test
    fun empty() {
        var isThrown = false
        try {
            ApplicationID("")
        } catch (exception: EmptyStringException) {
            isThrown = true
        }
        isThrown.shouldBeTrue()
    }
}