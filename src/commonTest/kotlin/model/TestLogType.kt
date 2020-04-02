package model

import com.algolia.search.model.LogType.All
import com.algolia.search.model.LogType.Build
import com.algolia.search.model.LogType.Error
import com.algolia.search.model.LogType.Other
import com.algolia.search.model.LogType.Query
import com.algolia.search.serialize.KeyAll
import com.algolia.search.serialize.KeyBuild
import com.algolia.search.serialize.KeyError
import com.algolia.search.serialize.KeyQuery
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestLogType {

    @Test
    fun raw() {
        All.raw shouldEqual KeyAll
        Query.raw shouldEqual KeyQuery
        Build.raw shouldEqual KeyBuild
        Error.raw shouldEqual KeyError
        Other(unknown).raw shouldEqual unknown
    }
}
