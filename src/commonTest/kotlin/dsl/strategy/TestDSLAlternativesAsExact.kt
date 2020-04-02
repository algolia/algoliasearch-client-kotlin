package dsl.strategy

import com.algolia.search.dsl.strategy.DSLAlternativesAsExact
import com.algolia.search.model.search.AlternativesAsExact
import shouldEqual
import kotlin.test.Test

internal class TestDSLAlternativesAsExact {

    @Test
    fun default() {
        val dsl = DSLAlternativesAsExact {
            +SingleWordSynonym
            +MultiWordsSynonym
            +IgnorePlurals
        }

        dsl shouldEqual listOf(
            AlternativesAsExact.SingleWordSynonym,
            AlternativesAsExact.MultiWordsSynonym,
            AlternativesAsExact.IgnorePlurals
        )
    }

    @Test
    fun exact() {
        DSLAlternativesAsExact().apply {
            SingleWordSynonym shouldEqual AlternativesAsExact.SingleWordSynonym
            MultiWordsSynonym shouldEqual AlternativesAsExact.MultiWordsSynonym
            IgnorePlurals shouldEqual AlternativesAsExact.IgnorePlurals
        }
    }
}
