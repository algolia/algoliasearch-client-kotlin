package model

import com.algolia.search.model.enums.LogType.*
import com.algolia.search.serialize.KeyAll
import com.algolia.search.serialize.KeyBuild
import com.algolia.search.serialize.KeyError
import com.algolia.search.serialize.KeyQuery
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
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