package serialize

import com.algolia.search.saas.data.TaskInfo
import com.algolia.search.saas.data.TaskStatus
import kotlinx.serialization.json.json
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestTaskInfo : TestSerializer<TaskInfo>(TaskInfo.serializer()) {

    override val items = listOf(
        TaskInfo(TaskStatus.Published) to json {
            "status" to TaskStatus.Published.raw
        }
    )
}