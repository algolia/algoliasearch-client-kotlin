package serialize.task

import com.algolia.search.model.task.TaskInfo
import com.algolia.search.model.task.TaskStatus
import com.algolia.search.serialize.KeyPendingTask
import com.algolia.search.serialize.KeyStatus
import serialize.TestSerializer

internal class TestTaskCluster : TestSerializer<TaskInfo>(TaskInfo.serializer()) {

    override val items = listOf(
        TaskInfo(TaskStatus.Published, false) to json {
            KeyStatus to TaskStatus.Published.raw
            KeyPendingTask to false
        }
    )
}
