package serialize

import client.data.ResponseFields
import client.data.ResponseFields.*
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonArray
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestResponseFields : TestSerializer<ResponseFields>(ResponseFields, ResponseFields) {

    override val item = listOf(
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
        Unknown(unknown) to JsonPrimitive(unknown)
    )
    override val items = listOf(
        listOf(
            All,
            AroundLatLng,
            AutomaticRadius,
            ExhaustiveFacetsCount,
            Facets,
            FacetsStats,
            Hits,
            HitsPerPage,
            Index,
            Length,
            NbHits,
            NbPages,
            Offset,
            Page,
            Params,
            ProcessingTimeMS,
            Query,
            QueryAfterRemoval,
            UserData,
            Unknown(unknown)
        ) to jsonArray {
            +All.raw
            +AroundLatLng.raw
            +AutomaticRadius.raw
            +ExhaustiveFacetsCount.raw
            +Facets.raw
            +FacetsStats.raw
            +Hits.raw
            +HitsPerPage.raw
            +Index.raw
            +Length.raw
            +NbHits.raw
            +NbPages.raw
            +Offset.raw
            +Page.raw
            +Params.raw
            +ProcessingTimeMS.raw
            +Query.raw
            +QueryAfterRemoval.raw
            +UserData.raw
            +unknown
        }
    )
}