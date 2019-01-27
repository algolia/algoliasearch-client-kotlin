package com.algolia.search.response

import com.algolia.search.model.ObjectID
import com.algolia.search.model.common.Waitable
import com.algolia.search.model.common.TaskID
import com.algolia.search.serialize.KeyObjectIDs
import com.algolia.search.serialize.KeyTaskID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ResponseBatch(
    @SerialName(KeyTaskID) override val taskID: TaskID,
    @SerialName(KeyObjectIDs) val objectIDs: List<ObjectID?>
) : Waitable