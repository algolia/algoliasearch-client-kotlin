package serialize

import client.data.TaskStatus
import client.data.TaskInfo
import client.serialize.KeyPublished
import client.serialize.KeyStatus
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestTaskInfo : TestSerializer<TaskInfo>(null, TaskInfo) {

    override val item = listOf(
        TaskInfo(TaskStatus.Published) to json {
            KeyStatus to KeyPublished
        }
    )
    override val items: List<Pair<List<TaskInfo>, JsonArray>> = listOf()
}