package query

import client.data.*
import client.query.helper.NumericOperator
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestQuery {

    @Test
    fun booleanOperator() {
        assertEquals("<", NumericOperator.Lesser.raw)
        assertEquals("<=", NumericOperator.LesserOrEqual.raw)
        assertEquals("=", NumericOperator.Equals.raw)
        assertEquals("!=", NumericOperator.NotEquals.raw)
        assertEquals(">=", NumericOperator.GreaterOrEqual.raw)
        assertEquals(">", NumericOperator.Greater.raw)
    }

    @Test
    fun exactOnSingleWordQuery() {
        assertEquals("attribute", ExactOnSingleWordQuery.Attribute.raw)
        assertEquals("word", ExactOnSingleWordQuery.Word.raw)
        assertEquals("none", ExactOnSingleWordQuery.None.raw)
    }

    @Test
    fun queryType() {
        assertEquals("prefixLast", QueryType.PrefixLast.raw)
        assertEquals("prefixAll", QueryType.PrefixAll.raw)
        assertEquals("prefixNone", QueryType.PrefixNone.raw)
    }

    @Test
    fun removeWordIfNoResults() {
        assertEquals("none", RemoveWordIfNoResults.None.raw)
        assertEquals("lastWords", RemoveWordIfNoResults.LastWords.raw)
        assertEquals("firstWords", RemoveWordIfNoResults.FirstWords.raw)
        assertEquals("allOptional", RemoveWordIfNoResults.AllOptional.raw)
    }

    @Test
    fun responseFields() {
        assertEquals("*", ResponseFields.All.raw)
        assertEquals("aroundLatLng", ResponseFields.AroundLatLng.raw)
        assertEquals("automaticRadius", ResponseFields.AutomaticRadius.raw)
        assertEquals("exhaustiveFacetsCount", ResponseFields.ExhaustiveFacetsCount.raw)
        assertEquals("facets", ResponseFields.Facets.raw)
        assertEquals("facets_stats", ResponseFields.FacetsStats.raw)
        assertEquals("hits", ResponseFields.Hits.raw)
        assertEquals("hitsPerPage", ResponseFields.HitsPerPage.raw)
        assertEquals("index", ResponseFields.Index.raw)
        assertEquals("length", ResponseFields.Length.raw)
        assertEquals("nbHits", ResponseFields.NbHits.raw)
        assertEquals("nbPages", ResponseFields.NbPages.raw)
        assertEquals("offset", ResponseFields.Offset.raw)
        assertEquals("page", ResponseFields.Page.raw)
        assertEquals("params", ResponseFields.Params.raw)
        assertEquals("processingTimeMS", ResponseFields.ProcessingTimeMS.raw)
        assertEquals("query", ResponseFields.Query.raw)
        assertEquals("queryAfterRemoval", ResponseFields.QueryAfterRemoval.raw)
        assertEquals("userData", ResponseFields.UserData.raw)
    }

    @Test
    fun sortFacetValuesBy() {
        assertEquals("alpha", SortFacetValuesBy.Alpha.raw)
        assertEquals("count", SortFacetValuesBy.Count.raw)
    }

    @Test
    fun typoTolerance() {
        assertEquals("min", TypoTolerance.Min.raw)
        assertEquals("strict", TypoTolerance.Strict.raw)
    }
}