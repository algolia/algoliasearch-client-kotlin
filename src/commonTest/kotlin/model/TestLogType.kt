package model

import com.algolia.search.model.LogType.*
import com.algolia.search.serialize.KeyAll
import com.algolia.search.serialize.KeyBuild
import com.algolia.search.serialize.KeyError
import com.algolia.search.serialize.KeyQuery
import kotlin.test.Test
import shouldEqual
import unknown


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