package model.apikey

import com.algolia.search.model.apikey.ACL.AddObject
import com.algolia.search.model.apikey.ACL.Analytics
import com.algolia.search.model.apikey.ACL.Browse
import com.algolia.search.model.apikey.ACL.DeleteIndex
import com.algolia.search.model.apikey.ACL.DeleteObject
import com.algolia.search.model.apikey.ACL.EditSettings
import com.algolia.search.model.apikey.ACL.ListIndices
import com.algolia.search.model.apikey.ACL.Logs
import com.algolia.search.model.apikey.ACL.Other
import com.algolia.search.model.apikey.ACL.Search
import com.algolia.search.model.apikey.ACL.SeeUnretrievableAttributes
import com.algolia.search.model.apikey.ACL.Settings
import com.algolia.search.serialize.internal.KeyAddObject
import com.algolia.search.serialize.internal.KeyAnalytics
import com.algolia.search.serialize.internal.KeyBrowse
import com.algolia.search.serialize.internal.KeyDeleteIndex
import com.algolia.search.serialize.internal.KeyDeleteObject
import com.algolia.search.serialize.internal.KeyEditSettings
import com.algolia.search.serialize.internal.KeyListIndexes
import com.algolia.search.serialize.internal.KeyLogs
import com.algolia.search.serialize.internal.KeySearch
import com.algolia.search.serialize.internal.KeySeeUnretrievableAttributes
import com.algolia.search.serialize.internal.KeySettings
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
