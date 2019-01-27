package com.algolia.search.model

import com.algolia.search.model.task.TaskID


interface Waitable {

    val taskID: TaskID
}