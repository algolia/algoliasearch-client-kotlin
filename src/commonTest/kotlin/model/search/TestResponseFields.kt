package model.search

import com.algolia.search.model.search.ResponseFields.*
import com.algolia.search.serialize.*
import kotlin.test.Test
import shouldEqual
import unknown


internal class TestResponseFields {

    @Test
    fun raw() {
        All.raw shouldEqual KeyStar
        AroundLatLng.raw shouldEqual KeyAroundLatLng
        AutomaticRadius.raw shouldEqual KeyAutomaticRadius
        ExhaustiveFacetsCount.raw shouldEqual KeyExhaustiveFacetsCount
        Facets.raw shouldEqual KeyFacets
        FacetsStats.raw shouldEqual KeyFacets_Stats
        Hits.raw shouldEqual KeyHits
        HitsPerPage.raw shouldEqual KeyHitsPerPage
        Index.raw shouldEqual KeyIndex
        Length.raw shouldEqual KeyLength
        NbHits.raw shouldEqual KeyNbHits
        NbPages.raw shouldEqual KeyNbPages
        Offset.raw shouldEqual KeyOffset
        Page.raw shouldEqual KeyPage
        Params.raw shouldEqual KeyParams
        ProcessingTimeMS.raw shouldEqual KeyProcessingTimeMS
        Query.raw shouldEqual KeyQuery
        QueryAfterRemoval.raw shouldEqual KeyQueryAfterRemoval
        UserData.raw shouldEqual KeyUserData
        Other(unknown).raw shouldEqual unknown
    }
}