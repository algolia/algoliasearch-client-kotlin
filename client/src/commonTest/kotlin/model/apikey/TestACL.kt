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
import com.algolia.search.serialize.internal.Key
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestACL {

    @Test
    fun raw() {
        Search.raw shouldEqual Key.Search
        Browse.raw shouldEqual Key.Browse
        AddObject.raw shouldEqual Key.AddObject
        DeleteObject.raw shouldEqual Key.DeleteObject
        DeleteIndex.raw shouldEqual Key.DeleteIndex
        Settings.raw shouldEqual Key.Settings
        EditSettings.raw shouldEqual Key.EditSettings
        Analytics.raw shouldEqual Key.Analytics
        ListIndices.raw shouldEqual Key.ListIndexes
        Logs.raw shouldEqual Key.Logs
        SeeUnretrievableAttributes.raw shouldEqual Key.SeeUnretrievableAttributes
        Other(unknown).raw shouldEqual unknown
    }
}
