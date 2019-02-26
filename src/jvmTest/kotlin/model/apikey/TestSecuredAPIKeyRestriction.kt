package model.apikey

import com.algolia.search.model.apikey.SecuredAPIKeyRestriction
import com.algolia.search.model.search.Query
import indexA
import indexB
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestSecuredAPIKeyRestriction {

    @Test
    fun stuff() {
        val restriction = SecuredAPIKeyRestriction(
            indexNames = listOf(indexA, indexB),
            sources = listOf("valueA", "valueB"),
            query = Query(query = "hello")
        )

        // TODO write test
    }
}