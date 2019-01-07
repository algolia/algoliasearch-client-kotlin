package com.algolia.search.saas.data

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


@Serializable
data class TaskBatchWrite(
    override val taskID: TaskID,
    @Optional val objectIDs: List<ObjectID?>? = null
) : Task