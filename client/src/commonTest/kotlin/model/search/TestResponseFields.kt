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
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestResponseFields {

    @Test
    fun raw() {
        All.raw shouldEqual Key.Star
        AroundLatLng.raw shouldEqual Key.AroundLatLng
        AutomaticRadius.raw shouldEqual Key.AutomaticRadius
        ExhaustiveFacetsCount.raw shouldEqual Key.ExhaustiveFacetsCount
        Facets.raw shouldEqual Key.Facets
        FacetsStats.raw shouldEqual Key.Facets_Stats
        Hits.raw shouldEqual Key.Hits
        HitsPerPage.raw shouldEqual Key.HitsPerPage
        Index.raw shouldEqual Key.Index
        Length.raw shouldEqual Key.Length
        NbHits.raw shouldEqual Key.NbHits
        NbPages.raw shouldEqual Key.NbPages
        Offset.raw shouldEqual Key.Offset
        Page.raw shouldEqual Key.Page
        Params.raw shouldEqual Key.Params
        ProcessingTimeMS.raw shouldEqual Key.ProcessingTimeMS
        Query.raw shouldEqual Key.Query
        QueryAfterRemoval.raw shouldEqual Key.QueryAfterRemoval
        UserData.raw shouldEqual Key.UserData
        Other(unknown).raw shouldEqual unknown
    }
}
