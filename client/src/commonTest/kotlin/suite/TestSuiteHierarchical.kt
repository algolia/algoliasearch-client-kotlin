package suite

import clientAdmin1
import com.algolia.search.model.Attribute
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.filter.FilterGroup
import com.algolia.search.model.search.Facet
import com.algolia.search.model.settings.AttributeForFaceting
import com.algolia.search.model.settings.Settings
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskStatus
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.JsonObject
import shouldBeTrue
import shouldEqual
import kotlin.test.Test

internal class TestSuiteHierarchical {

    private val suffix = "hierarchical"
    private val indexName = testSuiteIndexName(suffix)
    private val index = clientAdmin1.initIndex(indexName)

    private val color = Attribute("color")
    private val hierarchicalCategory = Attribute("hierarchicalCategories")
    private val hierarchicalCategoryLvl0 = Attribute("$hierarchicalCategory.lvl0")
    private val hierarchicalCategoryLvl1 = Attribute("$hierarchicalCategory.lvl1")
    private val hierarchicalCategoryLvl2 = Attribute("$hierarchicalCategory.lvl2")
    private val hierarchicalCategoryLvl3 = Attribute("$hierarchicalCategory.lvl3")

    private val category1 = "Category1"
    private val category2 = "Category2"
    private val category3 = "Category3"
    private val category3Sub1 = "Category3 > SubCategory1"
    private val category3Sub2 = "Category3 > SubCategory2"
    private val category3Sub1Sub1 = "Category3 > SubCategory1 > SubSubCategory1"
    private val category3Sub1Sub2 = "Category3 > SubCategory1 > SubSubCategory2"
    private val category3Sub2Sub1 = "Category3 > SubCategory2 > SubSubCategory1"
    private val category3Sub2Sub2 = "Category3 > SubCategory2 > SubSubCategory2"
    private val category3Sub2Sub2Sub1 = "Category3 > SubCategory2 > SubSubCategory2 > SubSubSubCategory1"
    private val category3Sub2Sub2Sub2 = "Category3 > SubCategory2 > SubSubCategory2 > SubSubSubCategory2"

    @Test
    fun hierarchicalDisjunctive() {
        runTest {
            val facets = setOf(hierarchicalCategory, color)
            val settings = Settings(
                attributesForFaceting = facets.map { AttributeForFaceting.Default(it) }
            )
            val objects = load(ListSerializer(JsonObject.serializer()), "hierarchical_disjunctive.json")

            index.apply {
                val tasks = mutableListOf<Task>()

                tasks += saveObjects(objects)
                tasks += setSettings(settings)

                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
            }

            val attributes = listOf(
                hierarchicalCategoryLvl0,
                hierarchicalCategoryLvl1,
                hierarchicalCategoryLvl2,
                hierarchicalCategoryLvl3
            )
            val path = listOf(
                Filter.Facet(hierarchicalCategoryLvl0, category3),
                Filter.Facet(hierarchicalCategoryLvl1, category3Sub2),
                Filter.Facet(hierarchicalCategoryLvl2, category3Sub2Sub2)
            )
            val filterGroups = setOf(
                FilterGroup.Or.Facet(
                    Filter.Facet(color, "red")
                ),
                FilterGroup.And.Hierarchical(
                    filters = setOf(Filter.Facet(hierarchicalCategoryLvl2, category3Sub2Sub2)),
                    path = path,
                    attributes = attributes
                )
            )

            val response = index.advancedSearch(filterGroups = filterGroups)

            response.hierarchicalFacets shouldEqual mapOf(
                hierarchicalCategoryLvl0 to listOf(Facet(category3, 4), Facet(category2, 1)),
                hierarchicalCategoryLvl1 to listOf(Facet(category3Sub2, 4)),
                hierarchicalCategoryLvl2 to listOf(Facet(category3Sub2Sub2, 3), Facet(category3Sub2Sub1, 1)),
                hierarchicalCategoryLvl3 to listOf(Facet(category3Sub2Sub2Sub1, 1), Facet(category3Sub2Sub2Sub2, 1))
            )
            response.hits.size shouldEqual 3
            response.disjunctiveFacets shouldEqual mapOf(
                color to listOf(
                    Facet("red", 3),
                    Facet("blue", 1)
                )
            )
        }
    }

    @Test
    fun hierarchical() {
        runTest {
            val facets = setOf(hierarchicalCategory)
            val settings = Settings(
                attributesForFaceting = facets.map { AttributeForFaceting.Default(it) }
            )
            val objects = load(ListSerializer(JsonObject.serializer()), "hierarchical.json")

            index.apply {
                val tasks = mutableListOf<Task>()

                tasks += saveObjects(objects)
                tasks += setSettings(settings)

                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
            }

            val attributes = listOf(
                hierarchicalCategoryLvl0,
                hierarchicalCategoryLvl1,
                hierarchicalCategoryLvl2,
                hierarchicalCategoryLvl3
            )
            val path = listOf(
                Filter.Facet(hierarchicalCategoryLvl0, category3),
                Filter.Facet(hierarchicalCategoryLvl1, category3Sub2),
                Filter.Facet(hierarchicalCategoryLvl2, category3Sub2Sub2)
            )
            val filterGroups = setOf(
                FilterGroup.And.Hierarchical(
                    filters = setOf(Filter.Facet(hierarchicalCategoryLvl2, category3Sub2Sub2)),
                    path = path,
                    attributes = attributes
                )
            )
            val response = index.advancedSearch(filterGroups = filterGroups)

            response.hierarchicalFacets shouldEqual mapOf(
                hierarchicalCategoryLvl0 to listOf(Facet(category3, 6), Facet(category2, 2), Facet(category1, 1)),
                hierarchicalCategoryLvl1 to listOf(Facet(category3Sub2, 4), Facet(category3Sub1, 2)),
                hierarchicalCategoryLvl2 to listOf(Facet(category3Sub2Sub2, 3), Facet(category3Sub2Sub1, 1)),
                hierarchicalCategoryLvl3 to listOf(Facet(category3Sub2Sub2Sub1, 1), Facet(category3Sub2Sub2Sub2, 1))
            )
            response.hits.size shouldEqual 3
        }
    }

    @Test
    fun hierarchicalFirstLevel() {
        runTest {
            val facets = setOf(hierarchicalCategory)
            val settings = Settings(
                attributesForFaceting = facets.map { AttributeForFaceting.Default(it) }
            )
            val objects = load(ListSerializer(JsonObject.serializer()), "hierarchical_disjunctive.json")

            index.apply {
                val tasks = mutableListOf<Task>()

                tasks += saveObjects(objects)
                tasks += setSettings(settings)

                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
            }

            val attributes = listOf(
                hierarchicalCategoryLvl0,
                hierarchicalCategoryLvl1,
                hierarchicalCategoryLvl2,
                hierarchicalCategoryLvl3
            )
            val path = listOf(
                Filter.Facet(hierarchicalCategoryLvl0, category3)
            )
            val filterGroups = setOf(
                FilterGroup.And.Hierarchical(
                    filters = setOf(Filter.Facet(hierarchicalCategoryLvl0, category3)),
                    attributes = attributes,
                    path = path
                )
            )

            val response = index.advancedSearch(filterGroups = filterGroups)

            response.hierarchicalFacets shouldEqual mapOf(
                hierarchicalCategoryLvl0 to listOf(Facet(category3, 8), Facet(category2, 3), Facet(category1, 1)),
                hierarchicalCategoryLvl1 to listOf(Facet(category3Sub2, 6), Facet(category3Sub1, 2))
            )
            response.hits.size shouldEqual 8
        }
    }

    @Test
    fun empty() {
        runTest {
            val facets = setOf(hierarchicalCategory)
            val settings = Settings(
                attributesForFaceting = facets.map { AttributeForFaceting.Default(it) }
            )
            val objects = load(ListSerializer(JsonObject.serializer()), "hierarchical.json")

            index.apply {
                val tasks = mutableListOf<Task>()

                tasks += saveObjects(objects)
                tasks += setSettings(settings)

                tasks.wait().all { it is TaskStatus.Published }.shouldBeTrue()
            }

            index.advancedSearch()
        }
    }
}
