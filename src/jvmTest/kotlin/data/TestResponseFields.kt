package data

import com.algolia.search.saas.data.ResponseFields.*
import com.algolia.search.saas.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestResponseFields {

    @Test
    fun raw() {
        All.raw shouldEqual KeyStar
        AroundLatLng.raw shouldEqual KeyAroundLatLng
        AutomaticRadius.raw shouldEqual KeyAutomaticRadius
        ExhaustiveFacetsCount.raw shouldEqual KeyExhaustiveFacetsCount
        Facets.raw shouldEqual KeyFacets
        FacetsStats.raw shouldEqual KeyFacetsStats
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