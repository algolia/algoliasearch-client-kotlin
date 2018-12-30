package query

import buildTest
import client.data.Attribute
import client.query.helper.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


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
            "category:book AND category:office AND category:gift" shouldEqual buildTest()
        }

        FilterBuilder {
            groupA += categoryBook
            groupA += categoryOffice
            groupB += categoryGift
            "(category:book AND category:office) AND category:gift" shouldEqual buildTest()
            setOf(categoryBook, categoryOffice) shouldEqual groupA.get()
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
            "category:book OR category:office" shouldEqual buildTest()
            colors += colorRed
            colors += colorBlue
            "(category:book OR category:office) AND (color:red OR color:blue)" shouldEqual buildTest()
            categories -= categoryBook
            "category:office AND (color:red OR color:blue)" shouldEqual buildTest()
            categories.clear()
            "color:red OR color:blue" shouldEqual buildTest()
            clear()
            "" shouldEqual buildTest()
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
            "category:book OR category:office" shouldEqual buildTest()
            prices += comparison
            "price != 15.0 AND (category:book OR category:office)" shouldEqual buildTest()
            prices += range
            "price != 15.0 AND price:5.0 TO 20.0 AND (category:book OR category:office)" shouldEqual buildTest()
            categories -= categoryBook
            "price != 15.0 AND price:5.0 TO 20.0 AND category:office" shouldEqual buildTest()
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
            "euro != 15.0 AND euro:5.0 TO 20.0" shouldEqual buildTest()
            currency.replaceAttribute(euro, dollar)
            "dollar != 15.0 AND dollar:5.0 TO 20.0" shouldEqual buildTest()
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
            "price != 15.0 OR nbLike:100.0 TO 200.0" shouldEqual buildTest()
            setOf(comparisonPrice) shouldEqual groupA.get(price)
        }

        // In this scenario, we want to add them to different OR group
        FilterBuilder {
            groupA += comparisonPrice
            groupB += rangeLike
            "price != 15.0 AND nbLike:100.0 TO 200.0" shouldEqual buildTest()
        }
    }
}