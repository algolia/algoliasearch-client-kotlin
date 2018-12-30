package query

import attributeA
import attributeB
import attributeC
import client.query.*
import comparisonA
import comparisonB
import facetA
import facetB
import groupAndA
import groupAndB
import groupMap
import groupOrA
import groupOrB
import nameA
import nameB
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import rangeA
import rangeB
import set
import shouldBeFalse
import shouldBeTrue
import shouldEqual
import tagA
import tagB


@RunWith(JUnit4::class)
class TestFilterHelper {

    @Test
    fun keyOr() {
        Group.Key(nameA, Group.Type.OrFacet) shouldEqual groupOrA.key(facetA)
        Group.Key(nameA, Group.Type.OrFacet) shouldEqual groupOrA.key(facetB)
        Group.Key(nameA, Group.Type.OrNumeric) shouldEqual groupOrA.key(comparisonA)
        Group.Key(nameA, Group.Type.OrNumeric) shouldEqual groupOrA.key(rangeA)
        Group.Key(nameA, Group.Type.OrTag) shouldEqual groupOrA.key(tagA)
    }

    @Test
    fun keyAnd() {
        val key = Group.Key(nameA, Group.Type.And)

        key shouldEqual groupAndA.key(facetA)
        key shouldEqual groupAndA.key(facetB)
        key shouldEqual groupAndA.key(comparisonA)
        key shouldEqual groupAndA.key(rangeA)
        key shouldEqual groupAndA.key(tagA)
    }

    @Test
    fun add() {
        groupMap().apply {
            val filters = arrayOf(facetA, facetB, comparisonA, comparisonB, rangeA, rangeB, tagA, tagB)

            add(groupOrA, *filters)
            add(groupOrB, *filters)
            add(groupAndA, *filters)
            add(groupAndB, *filters)
            mutableMapOf(
                Group.Key(nameA, Group.Type.OrFacet) to set(facetA, facetB),
                Group.Key(nameA, Group.Type.OrNumeric) to set(comparisonA, comparisonB, rangeA, rangeB),
                Group.Key(nameA, Group.Type.OrTag) to set(tagA, tagB),
                Group.Key(nameA, Group.Type.And) to set(*filters),
                Group.Key(nameB, Group.Type.OrFacet) to set(facetA, facetB),
                Group.Key(nameB, Group.Type.OrNumeric) to set(comparisonA, comparisonB, rangeA, rangeB),
                Group.Key(nameB, Group.Type.OrTag) to set(tagA, tagB),
                Group.Key(nameB, Group.Type.And) to set(*filters)
            ) shouldEqual this
        }
    }

    @Test
    fun remove() {
        groupMap().apply {
            add(groupOrA, facetA, facetB)
            add(groupOrB, facetA, facetB)
            remove(groupOrA, facetA)
            mutableMapOf(
                Group.Key(nameA, Group.Type.OrFacet) to set(facetB),
                Group.Key(nameB, Group.Type.OrFacet) to set(facetA, facetB)
            ) shouldEqual this
            remove(groupOrA, facetB)
            mutableMapOf(
                Group.Key(nameB, Group.Type.OrFacet) to set(facetA, facetB)
            ) shouldEqual this
        }
    }

    @Test
    fun contains() {
        groupMap().apply {
            add(groupAndA, facetA)
            add(groupAndB, facetB)
            contains(groupAndA, facetA).shouldBeTrue()
            contains(groupAndA, facetB).shouldBeFalse()
            contains(facetA).shouldBeTrue()
            contains(facetB).shouldBeTrue()
        }
    }

    @Test
    fun clear() {
        groupMap().apply {
            add(groupAndA, facetA, facetB, comparisonA, comparisonB)
            clear(groupAndA, facetB.attribute)
            mutableMapOf(
                Group.Key(nameA, Group.Type.And) to set(facetA, comparisonA)
            ) shouldEqual this
            clear(groupAndA, null)
            isEmpty().shouldBeTrue()
        }
    }

    @Test
    fun replace() {
        groupMap().apply {
            add(groupOrA, facetA)
            replace(groupOrB, facetA, facetB).shouldBeFalse()
            replace(groupOrA, facetA, facetB).shouldBeTrue()
            mutableMapOf(
                Group.Key(nameA, Group.Type.OrFacet) to set(facetB)
            ) shouldEqual this
        }
    }

    @Test
    fun move() {
        groupMap().apply {
            add(groupOrA, facetA)
            move(groupOrA, groupOrB, facetB).shouldBeFalse()
            move(groupOrB, groupOrA, facetA).shouldBeFalse()
            move(groupOrA, groupOrB, facetA).shouldBeTrue()
            mutableMapOf(
                Group.Key(nameB, Group.Type.OrFacet) to set(facetA)
            ) shouldEqual this
        }
    }

    @Test
    fun replaceAttribute() {
        groupMap().apply {
            val original = mutableMapOf(
                Group.Key(nameA, Group.Type.And) to set(facetA, facetB, comparisonA, comparisonB)
            )

            add(groupAndA, facetA, facetB, comparisonA, comparisonB)
            replaceAttribute(groupAndA, attributeC, attributeA)
            original shouldEqual this
            replaceAttribute(groupAndB, attributeA, attributeB)
            original shouldEqual this
            replaceAttribute(groupAndA, attributeA, attributeC)
            mutableMapOf(
                Group.Key(nameA, Group.Type.And) to set(
                    facetA.copy(attribute = attributeC),
                    facetB,
                    comparisonA.copy(attribute = attributeC),
                    comparisonB
                )
            ) shouldEqual this
        }
    }

    @Test
    fun get() {
        groupMap().apply {
            add(groupAndA, facetA, facetB)
            add(groupAndB, facetA, facetB)
            set(facetA) shouldEqual get(groupAndA, attributeA)
            set(facetA, facetB) shouldEqual get(groupAndA, null)
            set(facetA, facetB) shouldEqual get(null)
        }
    }
}