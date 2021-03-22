package model.task

import com.algolia.search.model.task.TaskStatus.NotPublished
import com.algolia.search.model.task.TaskStatus.Published
import com.algolia.search.serialize.KeyNotPublished
import com.algolia.search.serialize.KeyPublished
import shouldEqual
import kotlin.test.Test

internal class TestTaskStatus {

    @Test
    fun raw() {
        Published.raw shouldEqual KeyPublished
        NotPublished.raw shouldEqual KeyNotPublished
    }
}
