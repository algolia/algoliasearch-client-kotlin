package query

import client.query.Query
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

        FilterBuilder().apply {
            // User selects category:book
            or(categoryBook)
            assertEquals(categoryBook.build(), build())
            // User selects category:office
            or(categoryOffice)
            assertEquals("category:book OR category:office", build())
            // User selects color:red and color:blue
            or(colorRed, colorBlue)
            assertEquals("(category:book OR category:office) AND (color:red OR color:blue)", build())
        }
    }

    @Test
    fun showcaseRemoveAndReplace() {
        val published = FilterTag("published")
        val bestseller = FilterTag("bestseller")

        FilterBuilder().apply {
            and(published)
            assertEquals("_tags:published", build())
            replace(published, bestseller)
            assertEquals("_tags:bestseller", build())
            remove(bestseller)
            assertEquals("", build())
        }
    }

    @Test
    fun showcaseGetAndClear() {
        val phone = Attribute("phone")
        val apple = FilterFacet(phone, "apple")
        val samsung = FilterFacet(phone, "samsung")
        val chip = Attribute("chip")
        val intel = FilterFacet(chip, "intel")
        val qualcomm = FilterFacet(chip, "qualcomm")

        FilterBuilder().apply {
            or(apple, samsung)
            or(intel, qualcomm)
            assertEquals(setOf(intel, qualcomm), getFilters(chip))
            assertEquals(setOf(apple, samsung, intel, qualcomm), getFilters())
            assertEquals("(phone:apple OR phone:samsung) AND (chip:intel OR chip:qualcomm)", build())
            clear(phone)
            assertEquals("chip:intel OR chip:qualcomm", build())
            clear()
            assertEquals("", build())
        }
    }

    @Test
    fun showcaseConjunctiveWidgets() {
        // First widget for conjunctive filter on a isFeatured attribute
        val isFeatured = Attribute("isFeatured")
        val isFeaturedBoolean = FilterBoolean(isFeatured, true)
        // Second widget for a conjunctive filter on price attribute
        val price = Attribute("price")
        val priceFilter = FilterComparison(price, NumericOperator.Greater, 10.0)

        FilterBuilder().apply {
            // User selects isFeatured
            and(isFeaturedBoolean)
            assertEquals("isFeatured:true", build())
            // User selects a price filter greater than 10.0
            and(priceFilter)
            assertEquals("isFeatured:true AND price > 10.0", build())
        }
    }

    @Test
    fun showcaseReplaceAttribute() {
        // Widget for disjunctive filter on a price attribute with several currencies
        val euros = Attribute("euros")
        val dollars = Attribute("dollars")
        val priceNotEquals = FilterComparison(euros, NumericOperator.NotEquals, 10.0)
        val priceRange = FilterRange(euros, 0.0, 20.0)

        FilterBuilder().apply {
            // User selects price filters
            and(priceNotEquals, priceRange)
            assertEquals("euros != 10.0 AND euros:0.0 TO 20.0", build())
            // User switch currencies
            replaceAttribute(euros, dollars)
            assertEquals("dollars != 10.0 AND dollars:0.0 TO 20.0", build())
        }
    }

    @Test
    fun assign() {
        val query = Query()

        query.filterBuilder
            .and(FilterFacet(Attribute("attributeA"), "valueA"))

        assertEquals("attributeA:valueA", query.filterBuilder.build())
    }
}