package data

import client.data.ResponseFields.*
import client.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestResponseFields {

    @Test
    fun raw() {
        KeyStar shouldEqual All.raw
        KeyAroundLatLng shouldEqual AroundLatLng.raw
        KeyAutomaticRadius shouldEqual AutomaticRadius.raw
        KeyExhaustiveFacetsCount shouldEqual ExhaustiveFacetsCount.raw
        KeyFacets shouldEqual Facets.raw
        KeyFacetsStats shouldEqual FacetsStats.raw
        KeyHits shouldEqual Hits.raw
        KeyHitsPerPage shouldEqual HitsPerPage.raw
        KeyIndex shouldEqual Index.raw
        KeyLength shouldEqual Length.raw
        KeyNbHits shouldEqual NbHits.raw
        KeyNbPages shouldEqual NbPages.raw
        KeyOffset shouldEqual Offset.raw
        KeyPage shouldEqual Page.raw
        KeyParams shouldEqual Params.raw
        KeyProcessingTimeMS shouldEqual ProcessingTimeMS.raw
        KeyQuery shouldEqual Query.raw
        KeyQueryAfterRemoval shouldEqual QueryAfterRemoval.raw
        KeyUserData shouldEqual UserData.raw
        unknown shouldEqual Unknown(unknown).raw
    }
}