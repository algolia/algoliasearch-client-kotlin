package data

import com.algolia.search.saas.data.ObjectID
import com.algolia.search.saas.exception.EmptyStringException
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue


@RunWith(JUnit4::class)
internal class TestObjectID {

    @Test
    fun empty() {
        var isThrown = false
        try {
            ObjectID("")
        } catch (exception: EmptyStringException) {
            isThrown = true
        }
        isThrown.shouldBeTrue()
    }
}