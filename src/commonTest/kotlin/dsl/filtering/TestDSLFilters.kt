package dsl.filtering

import attributeA
import attributeB
import com.algolia.search.dsl.filtering.DSLFilters
import com.algolia.search.model.filter.FilterConverter
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestDSLFilters {

    @Test
    fun and() {
        val dsl = DSLFilters {
            and {
                +facet(attributeA, 0)
                +facet(attributeA, 1)
            }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "attributeA:0 AND attributeA:1"
    }

    @Test
    fun orFacet() {
        val dsl = DSLFilters {
            orFacet {
                +facet(attributeA, 0)
                +facet(attributeA, 1)
            }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "attributeA:0 OR attributeA:1"
    }

    @Test
    fun orTag() {
        val dsl = DSLFilters {
            orTag {
                +tag("a")
                +tag("b")
            }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "_tags:a OR _tags:b"
    }

    @Test
    fun orNumeric() {
        val dsl = DSLFilters {
            orNumeric {
                +range(attributeA, 0..1)
                +comparison(attributeA, NotEquals, 0)
            }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "attributeA:0 TO 1 OR attributeA != 0"
    }

    @Test
    fun emptyGroups() {
        val dsl = DSLFilters {
            and { }
            orFacet { }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual ""
    }

    @Test
    fun singleFilter() {
        val dsl = DSLFilters {
            and { +facet(attributeA, 0) }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "attributeA:0"
    }

    @Test
    fun oneOfEveryType() {
        val dsl = DSLFilters {
            and { +facet(attributeA, 0) }
            orFacet { +facet(attributeA, 0) }
            orTag { +tag(unknown) }
            orNumeric { +range(attributeA, 0..1) }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "attributeA:0 AND attributeA:0 AND _tags:unknown AND attributeA:0 TO 1"
    }

    @Test
    fun twoOfEveryType() {
        val dsl = DSLFilters {
            and {
                +facet(attributeA, 0)
                +facet(attributeB, 0)
            }
            orFacet {
                +facet(attributeA, 0)
                +facet(attributeB, 0)
            }
            orTag {
                +tag(attributeA.raw)
                +tag(attributeB.raw)
            }
            orNumeric {
                +range(attributeA, 0..1)
                +comparison(attributeB, Greater, 0)
            }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual
                "attributeA:0 AND attributeB:0 AND " +
                "(attributeA:0 OR attributeB:0) AND " +
                "(_tags:attributeA OR _tags:attributeB) AND " +
                "(attributeA:0 TO 1 OR attributeB > 0)"
    }

    @Test
    fun singleAndGroups_differentTypes() {
        val dsl = DSLFilters {
            and {
                +facet(attributeA, 0)
                +tag(unknown)
            }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "attributeA:0 AND _tags:unknown"
    }

    @Test
    fun twoAndGroups_OfTheSameType_balanced() {
        val dsl = DSLFilters {
            orTag {
                +tag(attributeA.raw)
                +tag(attributeB.raw)
            }
            orTag {
                +tag(attributeA.raw)
                +tag(attributeB.raw)
            }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "(_tags:attributeA OR _tags:attributeB) AND (_tags:attributeA OR _tags:attributeB)"
    }

    @Test
    fun twoAndGroups_OfDifferentTypes_balanced() {
        val dsl = DSLFilters {
            orTag {
                +tag(attributeA.raw)
                +tag(attributeB.raw)
            }
            orNumeric {
                +range(attributeA.raw, 0..1)
                +range(attributeB.raw, 0..1)
            }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "(_tags:attributeA OR _tags:attributeB) AND (attributeA:0 TO 1 OR attributeB:0 TO 1)"
    }

    @Test
    fun twoAndGroups_OfTheSameType_unbalanced() {
        val dsl = DSLFilters {
            orTag {
                +tag(attributeA.raw)
            }
            orTag {
                +tag(attributeA.raw)
                +tag(attributeB.raw)
            }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "_tags:attributeA AND (_tags:attributeA OR _tags:attributeB)"
    }

    @Test
    fun twoAndGroups_OfDifferentTypes_unbalanced() {
        val dsl = DSLFilters {
            orTag {
                +tag(attributeA.raw)
                +tag(attributeB.raw)
            }
            orNumeric {
                +range(attributeA.raw, 0..1)
            }
        }

        FilterConverter.SQLUnquoted(dsl) shouldEqual "(_tags:attributeA OR _tags:attributeB) AND attributeA:0 TO 1"
    }
}