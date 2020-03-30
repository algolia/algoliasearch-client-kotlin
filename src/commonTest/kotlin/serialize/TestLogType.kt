package serialize

import com.algolia.search.model.LogType
import com.algolia.search.model.LogType.All
import com.algolia.search.model.LogType.Build
import com.algolia.search.model.LogType.Error
import com.algolia.search.model.LogType.Other
import com.algolia.search.model.LogType.Query
import kotlinx.serialization.json.JsonLiteral
import unknown

internal class TestLogType : TestSerializer<LogType>(LogType) {

    override val items = listOf(
        All to JsonLiteral(LogType.All.raw),
        Query to JsonLiteral(LogType.Query.raw),
        Build to JsonLiteral(LogType.Build.raw),
        Error to JsonLiteral(LogType.Error.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}
