package dsl.advanced

import com.algolia.search.dsl.advanced.DSLResponseFields
import com.algolia.search.model.search.ResponseFields
import shouldEqual
import kotlin.test.Test

internal class TestDSLResponseFields {

    @Test
    fun default() {
        val dsl = DSLResponseFields {
            +All
            +AroundLatLng
        }

        dsl shouldEqual listOf(
            ResponseFields.All,
            ResponseFields.AroundLatLng
        )
    }

    @Test
    fun responseFields() {
        DSLResponseFields {
            All shouldEqual ResponseFields.All
            AroundLatLng shouldEqual ResponseFields.AroundLatLng
            AutomaticRadius shouldEqual ResponseFields.AutomaticRadius
            ExhaustiveFacetsCount shouldEqual ResponseFields.ExhaustiveFacetsCount
            Facets shouldEqual ResponseFields.Facets
            FacetsStats shouldEqual ResponseFields.FacetsStats
            Hits shouldEqual ResponseFields.Hits
            HitsPerPage shouldEqual ResponseFields.HitsPerPage
            Index shouldEqual ResponseFields.Index
            Length shouldEqual ResponseFields.Length
            NbHits shouldEqual ResponseFields.NbHits
            NbPages shouldEqual ResponseFields.NbPages
            Offset shouldEqual ResponseFields.Offset
            Page shouldEqual ResponseFields.Page
            Params shouldEqual ResponseFields.Params
            ProcessingTimeMS shouldEqual ResponseFields.ProcessingTimeMS
            Query shouldEqual ResponseFields.Query
            QueryAfterRemoval shouldEqual ResponseFields.QueryAfterRemoval
            UserData shouldEqual ResponseFields.UserData
        }
    }
}
