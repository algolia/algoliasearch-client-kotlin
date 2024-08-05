package model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseListIndices
import com.algolia.search.serialize.internal.Key
import shouldEqual
import kotlin.test.Test
import kotlinx.serialization.json.*

internal class TestResponseListIndices {

    @Test
    fun dx() {
        val json = buildJsonObject {
            put(Key.Name, "indexName")
            put(Key.CreatedAt, "2024-07-26T13:20:00Z")
            put(Key.UpdatedAt, "2024-08-05T15:17:04Z")
            put(Key.Entries, 0)
            put(Key.DataSize, 0)
            put(Key.FileSize, 0)
            put(Key.NumberOfPendingTasks, 0)
            put(Key.Replicas,
                buildJsonArray { add("replicas") }
            )
            put(Key.Primary, "primary")
            put(Key.SourceABTest, "sourceABTest")
        }
        val item = ResponseListIndices.Item(
            indexName = IndexName("indexName"),
            createdAt = ClientDate(1722000000000),
            updatedAt = ClientDate(1722871024000),
            entries = 0,
            dataSize = 0,
            fileSize = 0,
            numberOfPendingTasksOrNull = 0,
            replicasOrNull = listOf(IndexName("replicas")),
            primaryOrNull = IndexName("primary"),
            sourceABTestOrNull = IndexName("sourceABTest"),
            abTestOrNull = null
        )

        Json.encodeToJsonElement(ResponseListIndices.Item.serializer(), item) shouldEqual json
    }
}
