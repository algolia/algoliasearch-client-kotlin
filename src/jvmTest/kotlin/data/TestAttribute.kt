package data

import com.algolia.search.saas.model.Attribute
import com.algolia.search.saas.exception.EmptyStringException
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