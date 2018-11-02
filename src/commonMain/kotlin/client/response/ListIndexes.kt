package client.response

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable


@Serializable
data class ListIndexes(
    val items: List<Item>,
    val nbPages: Int
) {

    @Serializable
    data class Item(
        val name: String,
        val createdAt: String,
        val updatedAt: String,
        val entries: Int,
        val dataSize: Int,
        val fileSize: Int,
        val lastBuildTimeS: Int,
        val numberOfPendingTasks: Int,
        val pendingTask: Boolean,
        @Optional val replicas: List<String>? = null
    )
}