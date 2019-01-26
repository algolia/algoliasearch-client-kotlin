package serialize

import com.algolia.search.saas.model.common.TaskInfo
import com.algolia.search.saas.model.common.TaskStatus
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