package data

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.toAttribute
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestAttribute {

    @Test
    fun dx() {
        Attribute("attributeA") shouldEqual "attributeA".toAttribute()
    }

    @Test
    fun raw() {
        Attribute("raw").raw shouldEqual "raw"
    }
}