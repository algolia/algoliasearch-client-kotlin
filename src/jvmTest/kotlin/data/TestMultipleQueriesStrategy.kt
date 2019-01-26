package data

import com.algolia.search.saas.model.multiple_index.MultipleQueriesStrategy.*
import com.algolia.search.saas.serialize.KeyNone
import com.algolia.search.saas.serialize.KeyStopIfEnoughMatches
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestMultipleQueriesStrategy {

    @Test
    fun raw() {
        None.raw shouldEqual KeyNone
        StopIfEnoughMatches.raw shouldEqual KeyStopIfEnoughMatches
        Other(unknown).raw shouldEqual unknown
    }
}