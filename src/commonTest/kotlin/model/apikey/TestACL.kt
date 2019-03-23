package model.apikey

import com.algolia.search.model.apikey.ACL.*
import com.algolia.search.serialize.*
import shouldEqual
import unknown
import kotlin.test.Test


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
        ListIndices.raw shouldEqual KeyListIndexes
        Logs.raw shouldEqual KeyLogs
        SeeUnretrievableAttributes.raw shouldEqual KeySeeUnretrievableAttributes
        Other(unknown).raw shouldEqual unknown
    }
}