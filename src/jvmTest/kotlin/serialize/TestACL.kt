package serialize

import com.algolia.search.saas.model.api_key.ACL
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestACL : TestSerializer<ACL>(ACL) {

    override val items = listOf(
        ACL.Search to JsonLiteral(ACL.Search.raw),
        ACL.Browse to JsonLiteral(ACL.Browse.raw),
        ACL.AddObject to JsonLiteral(ACL.AddObject.raw),
        ACL.DeleteObject to JsonLiteral(ACL.DeleteObject.raw),
        ACL.DeleteIndex to JsonLiteral(ACL.DeleteIndex.raw),
        ACL.Settings to JsonLiteral(ACL.Settings.raw),
        ACL.EditSettings to JsonLiteral(ACL.EditSettings.raw),
        ACL.ListIndexes to JsonLiteral(ACL.ListIndexes.raw),
        ACL.Logs to JsonLiteral(ACL.Logs.raw),
        ACL.Analytics to JsonLiteral(ACL.Analytics.raw),
        ACL.SeeUnretrievableAttributes to JsonLiteral(ACL.SeeUnretrievableAttributes.raw),
        ACL.Other(unknown) to JsonLiteral(unknown)
    )
}