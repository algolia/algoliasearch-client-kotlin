package serialize.task

import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.internal.KeyPendingTask
import com.algolia.search.serialize.internal.KeyStatus
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import serialize.TestSerializer

internal class TestTaskCluster : TestSerializer<TaskInfo>(TaskInfo.serializer()) {

    override val items = listOf(
        TaskInfo(TaskStatus.Published, false) to buildJsonObject {
            put(KeyStatus, TaskStatus.Published.raw)
            put(KeyPendingTask, false)
        }
    )
}
