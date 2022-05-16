package model.task

import com.algolia.search.model.task.TaskStatus.NotPublished
import com.algolia.search.model.task.TaskStatus.Published
import com.algolia.search.serialize.internal.Key
import shouldEqual
import kotlin.test.Test

internal class TestTaskStatus {

    @Test
    fun raw() {
        Published.raw shouldEqual Key.Published
        NotPublished.raw shouldEqual Key.NotPublished
    }
}
