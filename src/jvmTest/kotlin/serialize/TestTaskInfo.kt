package serialize

import client.data.TaskInfo
import client.data.TaskStatus
import client.serialize.KeyPublished
import client.serialize.KeyStatus
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestTaskInfo : TestSerializer<TaskInfo>(TaskInfo.serializer()) {

    override val item = listOf(
        TaskInfo(TaskStatus.Published) to json {
            KeyStatus to KeyPublished
        }
    )
}