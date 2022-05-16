package model

import com.algolia.search.model.LogType.All
import com.algolia.search.model.LogType.Build
import com.algolia.search.model.LogType.Error
import com.algolia.search.model.LogType.Other
import com.algolia.search.model.LogType.Query
import com.algolia.search.serialize.internal.KeyAll
import com.algolia.search.serialize.internal.KeyBuild
import com.algolia.search.serialize.internal.KeyError
import com.algolia.search.serialize.internal.KeyQuery
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
