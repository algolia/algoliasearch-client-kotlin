package serialize.apikey

import com.algolia.search.model.apikey.ACL
import kotlinx.serialization.json.JsonLiteral
import serialize.TestSerializer
import unknown


internal class TestACL : TestSerializer<ACL>(ACL) {

    override val items = listOf(
        ACL.Search to JsonLiteral(ACL.Search.raw),
        ACL.Browse to JsonLiteral(ACL.Browse.raw),
        ACL.AddObject to JsonLiteral(ACL.AddObject.raw),
        ACL.DeleteObject to JsonLiteral(ACL.DeleteObject.raw),
        ACL.DeleteIndex to JsonLiteral(ACL.DeleteIndex.raw),
        ACL.Settings to JsonLiteral(ACL.Settings.raw),
        ACL.EditSettings to JsonLiteral(ACL.EditSettings.raw),
        ACL.ListIndices to JsonLiteral(ACL.ListIndices.raw),
        ACL.Logs to JsonLiteral(ACL.Logs.raw),
        ACL.Analytics to JsonLiteral(ACL.Analytics.raw),
        ACL.SeeUnretrievableAttributes to JsonLiteral(ACL.SeeUnretrievableAttributes.raw),
        ACL.Other(unknown) to JsonLiteral(unknown)
    )
}