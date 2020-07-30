package serialize.response

import com.algolia.search.model.search.ResponseFields
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
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestResponseFields : TestSerializer<ResponseFields>(ResponseFields) {

    override val items = listOf(
        All to JsonPrimitive(All.raw),
        AroundLatLng to JsonPrimitive(AroundLatLng.raw),
        AutomaticRadius to JsonPrimitive(AutomaticRadius.raw),
        ExhaustiveFacetsCount to JsonPrimitive(ExhaustiveFacetsCount.raw),
        Facets to JsonPrimitive(Facets.raw),
        FacetsStats to JsonPrimitive(FacetsStats.raw),
        Hits to JsonPrimitive(Hits.raw),
        HitsPerPage to JsonPrimitive(HitsPerPage.raw),
        Index to JsonPrimitive(Index.raw),
        Length to JsonPrimitive(Length.raw),
        NbHits to JsonPrimitive(NbHits.raw),
        NbPages to JsonPrimitive(NbPages.raw),
        Offset to JsonPrimitive(Offset.raw),
        Page to JsonPrimitive(Page.raw),
        Params to JsonPrimitive(Params.raw),
        ProcessingTimeMS to JsonPrimitive(ProcessingTimeMS.raw),
        Query to JsonPrimitive(Query.raw),
        QueryAfterRemoval to JsonPrimitive(QueryAfterRemoval.raw),
        UserData to JsonPrimitive(UserData.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
