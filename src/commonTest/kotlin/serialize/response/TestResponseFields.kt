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
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown

internal class TestResponseFields : TestSerializer<ResponseFields>(ResponseFields) {

    override val items = listOf(
        All to JsonLiteral(All.raw),
        AroundLatLng to JsonLiteral(AroundLatLng.raw),
        AutomaticRadius to JsonLiteral(AutomaticRadius.raw),
        ExhaustiveFacetsCount to JsonLiteral(ExhaustiveFacetsCount.raw),
        Facets to JsonLiteral(Facets.raw),
        FacetsStats to JsonLiteral(FacetsStats.raw),
        Hits to JsonLiteral(Hits.raw),
        HitsPerPage to JsonLiteral(HitsPerPage.raw),
        Index to JsonLiteral(Index.raw),
        Length to JsonLiteral(Length.raw),
        NbHits to JsonLiteral(NbHits.raw),
        NbPages to JsonLiteral(NbPages.raw),
        Offset to JsonLiteral(Offset.raw),
        Page to JsonLiteral(Page.raw),
        Params to JsonLiteral(Params.raw),
        ProcessingTimeMS to JsonLiteral(ProcessingTimeMS.raw),
        Query to JsonLiteral(Query.raw),
        QueryAfterRemoval to JsonLiteral(QueryAfterRemoval.raw),
        UserData to JsonLiteral(UserData.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}
