package com.algolia.search.model.response

import com.algolia.search.model.ObjectID
import com.algolia.search.model.task.Task
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.KeyObjectIDs
import com.algolia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseBatch(
    @SerialName(KeyTaskID) override val taskID: TaskID,
    @SerialName(KeyObjectIDs) val objectIDs: List<ObjectID?>
) : Task