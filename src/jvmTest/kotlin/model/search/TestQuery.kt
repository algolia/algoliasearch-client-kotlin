package model.search

import com.algolia.search.model.search.Query
import com.algolia.search.query.build
import facetA
import groupOrA
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestQuery {

    @Test
    fun testBuild() {
        val query = Query(
            query = "hello there"
        ).apply {
            isEveryWordInQueryOptional = true
        }

        query.build().also {
            it.filters shouldEqual null
            it.optionalFilters shouldEqual null
            it.optionalWords shouldEqual listOf(query.query)
        }
        query.filterBuilder.apply {
            groupOrA += facetA
        }
        query.optionalFilterBuilder.apply {
            groupOrA += facetA
        }
        query.build().also {
            it.filters shouldEqual query.filterBuilder.build()
            it.optionalFilters shouldEqual query.optionalFilterBuilder.build()
        }
        query.filters = "test"
        query.optionalFilters = listOf()
        query.build().also {
            it.filters shouldEqual "test"
            it.optionalFilters shouldEqual listOf()
        }
    }
}