package suite

import com.algolia.search.client.ClientAlgolia
import com.algolia.search.toAPIKey
import com.algolia.search.toApplicationID
import java.text.SimpleDateFormat
import java.util.*

internal val clientSearch = ClientAlgolia(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_SEARCH_KEY_1").toAPIKey()
)
internal val clientAdmin1 = ClientAlgolia(
    System.getenv("ALGOLIA_APPLICATION_ID_1").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_1").toAPIKey()
)
internal val clientAdmin2 = ClientAlgolia(
    System.getenv("ALGOLIA_APPLICATION_ID_2").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_2").toAPIKey()
)

internal val clientMcm = ClientAlgolia(
    System.getenv("ALGOLIA_ADMIN_ID_MCM").toApplicationID(),
    System.getenv("ALGOLIA_ADMIN_KEY_MCM").toAPIKey()
)

internal val dateFormat = SimpleDateFormat("YYYY-MM-DD-HH-mm-ss").also {
    it.timeZone = TimeZone.getTimeZone("UTC")
}