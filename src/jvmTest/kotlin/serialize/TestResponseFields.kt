package serialize

import com.algolia.search.model.enums.ResponseFields
import com.algolia.search.model.enums.ResponseFields.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
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