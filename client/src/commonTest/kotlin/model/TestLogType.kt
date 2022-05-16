package model

import com.algolia.search.model.LogType.All
import com.algolia.search.model.LogType.Build
import com.algolia.search.model.LogType.Error
import com.algolia.search.model.LogType.Other
import com.algolia.search.model.LogType.Query
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestLogType {

    @Test
    fun raw() {
        All.raw shouldEqual Key.All
        Query.raw shouldEqual Key.Query
        Build.raw shouldEqual Key.Build
        Error.raw shouldEqual Key.Error
        Other(unknown).raw shouldEqual unknown
    }
}
