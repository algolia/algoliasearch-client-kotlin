package model.task

import com.algolia.search.model.task.TaskStatus.NotPublished
import com.algolia.search.model.task.TaskStatus.Published
import com.algolia.search.serialize.internal.KeyNotPublished
import com.algolia.search.serialize.internal.KeyPublished
import shouldEqual
import kotlin.test.Test

internal class TestTaskStatus {

    @Test
    fun raw() {
        Published.raw shouldEqual KeyPublished
        NotPublished.raw shouldEqual KeyNotPublished
    }
}
