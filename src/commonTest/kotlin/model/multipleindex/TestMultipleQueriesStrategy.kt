package model.multipleindex

import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.None
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.Other
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.StopIfEnoughMatches
import com.algolia.search.serialize.KeyNone
import com.algolia.search.serialize.KeyStopIfEnoughMatches
import kotlin.test.Test
import shouldEqual
import unknown

internal class TestMultipleQueriesStrategy {

    @Test
    fun raw() {
        None.raw shouldEqual KeyNone
        StopIfEnoughMatches.raw shouldEqual KeyStopIfEnoughMatches
        Other(unknown).raw shouldEqual unknown
    }
}
