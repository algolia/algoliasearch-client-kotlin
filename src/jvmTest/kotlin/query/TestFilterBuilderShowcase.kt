package query

import buildTest
import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestFilterBuilderShowcase {

    @Test
    fun showcaseOneConjunctiveWidget() {
        val category = Attribute("category")
        val categoryBook = FilterFacet(category, "book")
        val categoryOffice = FilterFacet(category, "office")
        val categoryGift = FilterFacet(category, "gift")
        val groupA = GroupAnd("groupA")
        val groupB = GroupAnd("groupB")

        FilterBuilder {
            groupA += categoryBook
            groupA += categoryOffice
            groupA += categoryGift
            assertEquals("category:book AND category:office AND category:gift", buildTest())
        }

        FilterBuilder {
            groupA += categoryBook
            groupA += categoryOffice
            groupB += categoryGift
            assertEquals("(category:book AND category:office) AND category:gift", buildTest())
            assertEquals(setOf(categoryBook, categoryOffice), groupA.get())
        }
    }

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

        val categories = GroupOr("categories")
        val colors = GroupOr("colors")

        FilterBuilder {
            categories += categoryBook
            categories += categoryOffice
            assertEquals("category:book OR category:office", buildTest())
            colors += colorRed
            colors += colorBlue
            assertEquals("(category:book OR category:office) AND (color:red OR color:blue)", buildTest())
            categories -= categoryBook
            assertEquals("category:office AND (color:red OR color:blue)", buildTest())
            categories.clear()
            assertEquals("color:red OR color:blue", buildTest())
            clear()
            assertEquals("", buildTest())
        }
    }

    @Test
    fun showCaseOneConjunctiveOneDisjunctive() {
        // First widget for disjunctive faceting on a category attribute
        val category = Attribute("category")
        val categoryBook = FilterFacet(category, "book")
        val categoryOffice = FilterFacet(category, "office")

        // Second widget for conjunctive faceting on a euro attribute
        val price = Attribute("price")
        val comparison = FilterComparison(price, NumericOperator.NotEquals, 15.0)
        val range = FilterRange(price, 5.0, 20.0)

        val categories = GroupOr("categories")
        val prices = GroupAnd("prices")

        FilterBuilder {
            categories += listOf(categoryBook, categoryOffice)
            assertEquals("category:book OR category:office", buildTest())
            prices += comparison
            assertEquals("price != 15.0 AND (category:book OR category:office)", buildTest())
            prices += range
            assertEquals("price != 15.0 AND price:5.0 TO 20.0 AND (category:book OR category:office)", buildTest())
            categories -= categoryBook
            assertEquals("price != 15.0 AND price:5.0 TO 20.0 AND category:office", buildTest())
        }
    }

    @Test
    fun showcaseReplaceAttribute() {
        // Widget for conjunctive faceting on a euro attribute
        val euro = Attribute("euro")
        val dollar = Attribute("dollar")
        val comparison = FilterComparison(euro, NumericOperator.NotEquals, 15.0)
        val range = FilterRange(euro, 5.0, 20.0)
        val currency = GroupAnd("currency")

        FilterBuilder {
            currency += comparison
            currency += range
            assertEquals("euro != 15.0 AND euro:5.0 TO 20.0", buildTest())
            currency.replaceAttribute(euro, dollar)
            assertEquals("dollar != 15.0 AND dollar:5.0 TO 20.0", buildTest())
        }
    }

    @Test
    fun showcaseDisjunctiveFiltersOfSimilarTypesButDifferentAttributes() {
        val price = Attribute("price")
        val nbLike = Attribute("nbLike")
        val comparisonPrice = FilterComparison(price, NumericOperator.NotEquals, 15.0)
        val rangeLike = FilterRange(nbLike, 100.0, 200.0)
        val groupA = GroupOr("groupA")
        val groupB = GroupOr("groupB")

        // In this scenario, we want to add them to the same OR group
        FilterBuilder {
            groupA += comparisonPrice
            groupA += rangeLike
            assertEquals("price != 15.0 OR nbLike:100.0 TO 200.0", buildTest())
            assertEquals(setOf(comparisonPrice), groupA.get(price))
        }

        // In this scenario, we want to add them to different OR group
        FilterBuilder {
            groupA += comparisonPrice
            groupB += rangeLike
            assertEquals("price != 15.0 AND nbLike:100.0 TO 200.0", buildTest())
        }
    }
}