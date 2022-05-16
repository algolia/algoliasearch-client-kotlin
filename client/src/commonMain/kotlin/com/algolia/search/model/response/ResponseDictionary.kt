package com.algolia.search.model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.model.task.DictionaryTask
import com.algolia.search.model.task.DictionaryTaskID
import com.algolia.search.model.task.TaskID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseDictionary(
    /**
     * The date at which the data has been applied.
     */
    @SerialName(Key.UpdatedAt) val updatedAt: ClientDate,
    /**
     * The [TaskID] which can be used to check the task status.
     */
    @SerialName(Key.TaskID) override val taskID: DictionaryTaskID,
) : DictionaryTask
