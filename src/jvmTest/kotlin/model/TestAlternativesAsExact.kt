package model

import com.algolia.search.model.enums.AlternativesAsExact.*
import com.algolia.search.serialize.KeyIgnorePlurals
import com.algolia.search.serialize.KeyMultiWordsSynonym
import com.algolia.search.serialize.KeySingleWordSynonym
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestAlternativesAsExact {

    @Test
    fun raw() {
        IgnorePlurals.raw shouldEqual KeyIgnorePlurals
        SingleWordSynonym.raw shouldEqual KeySingleWordSynonym
        MultiWordsSynonym.raw shouldEqual KeyMultiWordsSynonym
        Other(unknown).raw shouldEqual unknown
    }
}