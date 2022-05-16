package model.multipleindex

import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.None
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.Other
import com.algolia.search.model.multipleindex.MultipleQueriesStrategy.StopIfEnoughMatches
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestMultipleQueriesStrategy {

    @Test
    fun raw() {
        None.raw shouldEqual Key.None
        StopIfEnoughMatches.raw shouldEqual Key.StopIfEnoughMatches
        Other(unknown).raw shouldEqual unknown
    }
}
