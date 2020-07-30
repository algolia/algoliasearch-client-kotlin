package serialize

import com.algolia.search.model.LogType
import com.algolia.search.model.LogType.All
import com.algolia.search.model.LogType.Build
import com.algolia.search.model.LogType.Error
import com.algolia.search.model.LogType.Other
import com.algolia.search.model.LogType.Query
import kotlinx.serialization.json.JsonPrimitive
import unknown

internal class TestLogType : TestSerializer<LogType>(LogType) {

    override val items = listOf(
        All to JsonPrimitive(All.raw),
        Query to JsonPrimitive(LogType.Query.raw),
        Build to JsonPrimitive(LogType.Build.raw),
        Error to JsonPrimitive(LogType.Error.raw),
        Other(unknown) to JsonPrimitive(unknown)
    )
}
