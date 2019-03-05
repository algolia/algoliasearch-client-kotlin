package model

import com.algolia.search.model.Attribute
import com.algolia.search.exception.EmptyStringException
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue


@RunWith(JUnit4::class)
internal class TestAttribute {

    @Test
    fun empty() {
        var isThrown = false
        try {
            Attribute("")
        } catch (exception: EmptyStringException) {
            isThrown = true
        }
        isThrown.shouldBeTrue()
    }
}