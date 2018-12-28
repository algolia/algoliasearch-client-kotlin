package data

import client.data.ResponseFields.*
import client.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
internal class TestResponseFields {

    @Test
    fun key() {
        assertEquals("*", KeyStar)
        assertEquals("aroundLatLng", KeyAroundLatLng)
        assertEquals("automaticRadius", KeyAutomaticRadius)
        assertEquals("exhaustiveFacetsCount", KeyExhaustiveFacetsCount)
        assertEquals("facets", KeyFacets)
        assertEquals("facets_stats", KeyFacetsStats)
        assertEquals("hits", KeyHits)
        assertEquals("hitsPerPage", KeyHitsPerPage)
        assertEquals("index", KeyIndex)
        assertEquals("length", KeyLength)
        assertEquals("nbHits", KeyNbHits)
        assertEquals("nbPages", KeyNbPages)
        assertEquals("offset", KeyOffset)
        assertEquals("page", KeyPage)
        assertEquals("params", KeyParams)
        assertEquals("processingTimeMS", KeyProcessingTimeMS)
        assertEquals("query", KeyQuery)
        assertEquals("queryAfterRemoval", KeyQueryAfterRemoval)
        assertEquals("userData", KeyUserData)
    }

    @Test
    fun raw() {
        assertEquals(KeyStar, All.raw)
        assertEquals(KeyAroundLatLng, AroundLatLng.raw)
        assertEquals(KeyAutomaticRadius, AutomaticRadius.raw)
        assertEquals(KeyExhaustiveFacetsCount, ExhaustiveFacetsCount.raw)
        assertEquals(KeyFacets, Facets.raw)
        assertEquals(KeyFacetsStats, FacetsStats.raw)
        assertEquals(KeyHits, Hits.raw)
        assertEquals(KeyHitsPerPage, HitsPerPage.raw)
        assertEquals(KeyIndex, Index.raw)
        assertEquals(KeyLength, Length.raw)
        assertEquals(KeyNbHits, NbHits.raw)
        assertEquals(KeyNbPages, NbPages.raw)
        assertEquals(KeyOffset, Offset.raw)
        assertEquals(KeyPage, Page.raw)
        assertEquals(KeyParams, Params.raw)
        assertEquals(KeyProcessingTimeMS, ProcessingTimeMS.raw)
        assertEquals(KeyQuery, Query.raw)
        assertEquals(KeyQueryAfterRemoval, QueryAfterRemoval.raw)
        assertEquals(KeyUserData, UserData.raw)
        assertEquals(unknown, Unknown(unknown).raw)
    }
}