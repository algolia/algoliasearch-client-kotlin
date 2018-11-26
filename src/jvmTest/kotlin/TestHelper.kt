import client.query.helper.FacetHelper
import client.query.Query
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestHelper {

    @Test
    fun facet() {
        val facets = listOf("color", "material")
        val helper = FacetHelper(*facets.toTypedArray())
        val query = Query()

        assertEquals(facets, helper)
        helper.remove("material")
        assertEquals(listOf("color"), helper)
        helper.add("material")
        assertEquals(facets, helper)
        helper.assign(query)
        assertEquals(facets, query.facets)
    }
}