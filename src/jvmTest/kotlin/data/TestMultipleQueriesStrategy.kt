package data

import com.algolia.search.saas.data.MultipleQueriesStrategy.*
import com.algolia.search.saas.serialize.KeyNone
import com.algolia.search.saas.serialize.KeyStopIfEnoughMatches
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
class TestMultipleQueriesStrategy {

    @Test
    fun raw() {
        None.raw shouldEqual KeyNone
        StopIfEnoughMatches.raw shouldEqual KeyStopIfEnoughMatches
        Unknown(unknown).raw shouldEqual unknown
    }
}