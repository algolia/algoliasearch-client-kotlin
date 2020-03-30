package model.search

import com.algolia.search.model.search.ResponseFields.All
import com.algolia.search.model.search.ResponseFields.AroundLatLng
import com.algolia.search.model.search.ResponseFields.AutomaticRadius
import com.algolia.search.model.search.ResponseFields.ExhaustiveFacetsCount
import com.algolia.search.model.search.ResponseFields.Facets
import com.algolia.search.model.search.ResponseFields.FacetsStats
import com.algolia.search.model.search.ResponseFields.Hits
import com.algolia.search.model.search.ResponseFields.HitsPerPage
import com.algolia.search.model.search.ResponseFields.Index
import com.algolia.search.model.search.ResponseFields.Length
import com.algolia.search.model.search.ResponseFields.NbHits
import com.algolia.search.model.search.ResponseFields.NbPages
import com.algolia.search.model.search.ResponseFields.Offset
import com.algolia.search.model.search.ResponseFields.Other
import com.algolia.search.model.search.ResponseFields.Page
import com.algolia.search.model.search.ResponseFields.Params
import com.algolia.search.model.search.ResponseFields.ProcessingTimeMS
import com.algolia.search.model.search.ResponseFields.Query
import com.algolia.search.model.search.ResponseFields.QueryAfterRemoval
import com.algolia.search.model.search.ResponseFields.UserData
import com.algolia.search.serialize.KeyAroundLatLng
import com.algolia.search.serialize.KeyAutomaticRadius
import com.algolia.search.serialize.KeyExhaustiveFacetsCount
import com.algolia.search.serialize.KeyFacets
import com.algolia.search.serialize.KeyFacets_Stats
import com.algolia.search.serialize.KeyHits
import com.algolia.search.serialize.KeyHitsPerPage
import com.algolia.search.serialize.KeyIndex
import com.algolia.search.serialize.KeyLength
import com.algolia.search.serialize.KeyNbHits
import com.algolia.search.serialize.KeyNbPages
import com.algolia.search.serialize.KeyOffset
import com.algolia.search.serialize.KeyPage
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyProcessingTimeMS
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyQueryAfterRemoval
import com.algolia.search.serialize.KeyStar
import com.algolia.search.serialize.KeyUserData
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
