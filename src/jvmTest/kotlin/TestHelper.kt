import client.query.Filter
import client.query.helper.FacetHelper
import client.query.Query
import client.query.helper.FilterHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestHelper {

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

    @Test
    fun filter() {
        val helper = FilterHelper().also {
            it.add(Filter.Tag("attributeA"))
            it.addDisjunctiveGroup(Filter.Facet("attributeB", "stuff"), Filter.Boolean("attributeC", true, true))
            it.add(Filter.Tag("attributeA"))
        }
        println(helper.raw())
        helper.remove(Filter.Tag("attributeA"))
        println(helper.raw())
        helper.remove(Filter.Facet("attributeB", "stuff"))
        println(helper.raw())
        helper.add(Filter.Tag("attributeA", true))
        println(helper.raw())
    }
}