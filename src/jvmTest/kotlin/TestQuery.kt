import client.Query
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestQuery {

    @Test
    fun query() {
        val query = Query()

        assertEquals(null, query.query)
        query.query = "query"
        assertEquals("query", query.query)
        query.query = null
        assertEquals(null, query.query)
    }

    @Test
    fun attributesToRetrieve() {
        val query = Query()
        val attributes = arrayOf("attributeA", "attributeB")

        assertEquals(null, query.getAttributesToRetrieve())
        query.setAttributesToRetrieve(*attributes)
        assertEquals(attributes.toList(), query.getAttributesToRetrieve())

        query.setAttributesToRetrieve()
        assertEquals(listOf(), query.getAttributesToRetrieve())
        query.setAttributesToRetrieveExcept(*attributes)

        assertEquals(listOf("-attributeA", "-attributeB", "*"), query.getAttributesToRetrieve())
        query.clearAttributesToRetrieve()
        assertEquals(null, query.getAttributesToRetrieve())
    }
}