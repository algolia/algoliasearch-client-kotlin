package data

import com.algolia.search.apikey.ACL.*
import com.algolia.search.serialize.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestACL {

    @Test
    fun raw() {
        Search.raw shouldEqual KeySearch
        Browse.raw shouldEqual KeyBrowse
        AddObject.raw shouldEqual KeyAddObject
        DeleteObject.raw shouldEqual KeyDeleteObject
        DeleteIndex.raw shouldEqual KeyDeleteIndex
        Settings.raw shouldEqual KeySettings
        EditSettings.raw shouldEqual KeyEditSettings
        Analytics.raw shouldEqual KeyAnalytics
        ListIndexes.raw shouldEqual KeyListIndexes
        Logs.raw shouldEqual KeyLogs
        SeeUnretrievableAttributes.raw shouldEqual KeySeeUnretrievableAttributes
        Other(unknown).raw shouldEqual unknown
    }
}