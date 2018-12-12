package query

import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilterBuilder {

    @Test
    fun showCaseTwoDisjunctiveWidgets() {
        // First widget for disjunctive faceting on a category attribute
        val category = Attribute("category")
        val categoryBook = FilterFacet(category, "book")
        val categoryOffice = FilterFacet(category, "office")
        // Second widget for disjunctive faceting on a color attribute
        val color = Attribute("color")
        val colorRed = FilterFacet(color, "red")
        val colorBlue = FilterFacet(color, "blue")

        val categories = Group.Or("categories")
        val colors = Group.Or("colors")

        FilterBuilder {
            categories += categoryBook
            categories += categoryOffice
            assertEquals("category:book OR category:office", build())
            colors += colorRed
            colors += colorBlue
            assertEquals("(category:book OR category:office) AND (color:red OR color:blue)", build())
            categories -= categoryBook
            assertEquals("category:office AND (color:red OR color:blue)", build())
            categories.clear()
            assertEquals("color:red OR color:blue", build())
            clear()
            assertEquals("", build())
        }
    }

    @Test
    fun showCaseOneConjunctiveOneDisjunctive() {
        // First widget for disjunctive faceting on a category attribute
        val category = Attribute("category")
        val categoryBook = FilterFacet(category, "book")
        val categoryOffice = FilterFacet(category, "office")

        // Second widget for conjunctive faceting on a euro attribute
        val euro = Attribute("euro")
        val comparison = FilterComparison(euro, NumericOperator.NotEquals, 15.0)
        val range = FilterRange(euro, 5.0, 20.0)

        val categories = Group.Or("categories")
        val euros = Group.And("euros")

        FilterBuilder {
            categories += listOf(categoryBook, categoryOffice)
            assertEquals("category:book OR category:office", build())
            euros += comparison
            assertEquals("euro != 15.0 AND (category:book OR category:office)", build())
            euros += range
            assertEquals("euro != 15.0 AND euro:5.0 TO 20.0 AND (category:book OR category:office)", build())
            euros.replaceAttribute(euro, euro.copy(name = "dollar"))
            assertEquals("dollar != 15.0 AND dollar:5.0 TO 20.0 AND (category:book OR category:office)", build())
        }
    }

    @Test
    fun and() {
        val groupA = Group.And("groupA")

        FilterBuilder {
            groupA += FilterFacet(Attribute("facet"))
            groupA += FilterTag("value")

            groupA
        }
    }
}