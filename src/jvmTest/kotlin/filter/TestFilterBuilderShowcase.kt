package filter

import buildTest
import com.algolia.search.model.Attribute
import com.algolia.search.filter.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestFilterBuilderShowcase {

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
            buildTest() shouldEqual "category:book AND category:office AND category:gift"
        }

        FilterBuilder {
            groupA += categoryBook
            groupA += categoryOffice
            groupB += categoryGift
            buildTest() shouldEqual "(category:book AND category:office) AND category:gift"
            groupA.get() shouldEqual setOf(categoryBook, categoryOffice)
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
            buildTest() shouldEqual "category:book OR category:office"
            colors += colorRed
            colors += colorBlue
            buildTest() shouldEqual "(category:book OR category:office) AND (color:red OR color:blue)"
            categories -= categoryBook
            buildTest() shouldEqual "category:office AND (color:red OR color:blue)"
            categories.clear()
            buildTest() shouldEqual "color:red OR color:blue"
            clear()
            buildTest() shouldEqual ""
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
            buildTest() shouldEqual "category:book OR category:office"
            prices += comparison
            buildTest() shouldEqual "price != 15.0 AND (category:book OR category:office)"
            prices += range
            buildTest() shouldEqual "price != 15.0 AND price:5.0 TO 20.0 AND (category:book OR category:office)"
            categories -= categoryBook
            buildTest() shouldEqual "price != 15.0 AND price:5.0 TO 20.0 AND category:office"
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
            buildTest() shouldEqual "euro != 15.0 AND euro:5.0 TO 20.0"
            currency.replaceAttribute(euro, dollar)
            buildTest() shouldEqual "dollar != 15.0 AND dollar:5.0 TO 20.0"
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
            buildTest() shouldEqual "price != 15.0 OR nbLike:100.0 TO 200.0"
            groupA.get(price) shouldEqual setOf(comparisonPrice)
        }

        // In this scenario, we want to add them to different OR group
        FilterBuilder {
            groupA += comparisonPrice
            groupB += rangeLike
            buildTest() shouldEqual "price != 15.0 AND nbLike:100.0 TO 200.0"
        }
    }
}