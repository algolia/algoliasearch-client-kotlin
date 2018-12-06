import client.query.Query
import client.query.helper.FacetHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFacetHelper {

    @Test
    fun facet() {
        val facets = listOf("attributeA", "attributeB")
        val helper = FacetHelper(*facets.toTypedArray())
        val query = Query()

        assertEquals(facets, helper)
        helper.remove("attributeB")
        assertEquals(listOf("attributeA"), helper)
        helper.add("attributeB")
        assertEquals(facets, helper)
        helper.assign(query)
        assertEquals(facets, query.facets)
    }
}