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
import com.algolia.search.serialize.internal.KeyAroundLatLng
import com.algolia.search.serialize.internal.KeyAutomaticRadius
import com.algolia.search.serialize.internal.KeyExhaustiveFacetsCount
import com.algolia.search.serialize.internal.KeyFacets
import com.algolia.search.serialize.internal.KeyFacets_Stats
import com.algolia.search.serialize.internal.KeyHits
import com.algolia.search.serialize.internal.KeyHitsPerPage
import com.algolia.search.serialize.internal.KeyIndex
import com.algolia.search.serialize.internal.KeyLength
import com.algolia.search.serialize.internal.KeyNbHits
import com.algolia.search.serialize.internal.KeyNbPages
import com.algolia.search.serialize.internal.KeyOffset
import com.algolia.search.serialize.internal.KeyPage
import com.algolia.search.serialize.internal.KeyParams
import com.algolia.search.serialize.internal.KeyProcessingTimeMS
import com.algolia.search.serialize.internal.KeyQuery
import com.algolia.search.serialize.internal.KeyQueryAfterRemoval
import com.algolia.search.serialize.internal.KeyStar
import com.algolia.search.serialize.internal.KeyUserData
import shouldEqual
import unknown
import kotlin.test.Test

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
