package data

import client.data.ResponseFields
import client.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class TestResponseFields {

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
        assertEquals(KeyStar, ResponseFields.All.raw)
        assertEquals(KeyAroundLatLng, ResponseFields.AroundLatLng.raw)
        assertEquals(KeyAutomaticRadius, ResponseFields.AutomaticRadius.raw)
        assertEquals(KeyExhaustiveFacetsCount, ResponseFields.ExhaustiveFacetsCount.raw)
        assertEquals(KeyFacets, ResponseFields.Facets.raw)
        assertEquals(KeyFacetsStats, ResponseFields.FacetsStats.raw)
        assertEquals(KeyHits, ResponseFields.Hits.raw)
        assertEquals(KeyHitsPerPage, ResponseFields.HitsPerPage.raw)
        assertEquals(KeyIndex, ResponseFields.Index.raw)
        assertEquals(KeyLength, ResponseFields.Length.raw)
        assertEquals(KeyNbHits, ResponseFields.NbHits.raw)
        assertEquals(KeyNbPages, ResponseFields.NbPages.raw)
        assertEquals(KeyOffset, ResponseFields.Offset.raw)
        assertEquals(KeyPage, ResponseFields.Page.raw)
        assertEquals(KeyParams, ResponseFields.Params.raw)
        assertEquals(KeyProcessingTimeMS, ResponseFields.ProcessingTimeMS.raw)
        assertEquals(KeyQuery, ResponseFields.Query.raw)
        assertEquals(KeyQueryAfterRemoval, ResponseFields.QueryAfterRemoval.raw)
        assertEquals(KeyUserData, ResponseFields.UserData.raw)
        assertEquals(unknown, ResponseFields.Unknown(unknown).raw)
    }
}