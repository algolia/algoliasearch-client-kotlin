package com.algolia.search.saas.data


data class TaskCreate(
    val createdAt: String,
    val objectId: String,
    override val taskID: Long
) : TaskId