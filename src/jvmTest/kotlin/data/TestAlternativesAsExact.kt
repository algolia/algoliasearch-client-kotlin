package data

import com.algolia.search.saas.model.enums.AlternativesAsExact.*
import com.algolia.search.saas.serialize.KeyIgnorePlurals
import com.algolia.search.saas.serialize.KeyMultiWordsSynonym
import com.algolia.search.saas.serialize.KeySingleWordSynonym
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