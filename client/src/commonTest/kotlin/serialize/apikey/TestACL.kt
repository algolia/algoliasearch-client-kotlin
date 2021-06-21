package serialize.apikey

import com.algolia.search.model.apikey.ACL
import kotlinx.serialization.json.JsonPrimitive
import serialize.TestSerializer
import unknown

internal class TestACL : TestSerializer<ACL>(ACL) {

    override val items = listOf(
        ACL.Search to JsonPrimitive(ACL.Search.raw),
        ACL.Browse to JsonPrimitive(ACL.Browse.raw),
        ACL.AddObject to JsonPrimitive(ACL.AddObject.raw),
        ACL.DeleteObject to JsonPrimitive(ACL.DeleteObject.raw),
        ACL.DeleteIndex to JsonPrimitive(ACL.DeleteIndex.raw),
        ACL.Settings to JsonPrimitive(ACL.Settings.raw),
        ACL.EditSettings to JsonPrimitive(ACL.EditSettings.raw),
        ACL.ListIndices to JsonPrimitive(ACL.ListIndices.raw),
        ACL.Logs to JsonPrimitive(ACL.Logs.raw),
        ACL.Analytics to JsonPrimitive(ACL.Analytics.raw),
        ACL.SeeUnretrievableAttributes to JsonPrimitive(ACL.SeeUnretrievableAttributes.raw),
        ACL.Other(unknown) to JsonPrimitive(unknown)
    )
}
