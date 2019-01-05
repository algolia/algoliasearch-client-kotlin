package serialize

import com.algolia.search.saas.data.ResponseFields
import com.algolia.search.saas.data.ResponseFields.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestResponseFields : TestSerializer<ResponseFields>(ResponseFields) {

    override val items = listOf(
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
        Other(unknown)
    )
}