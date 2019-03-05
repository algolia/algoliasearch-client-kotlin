package model

import com.algolia.search.model.ObjectID
import com.algolia.search.exception.EmptyStringException
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