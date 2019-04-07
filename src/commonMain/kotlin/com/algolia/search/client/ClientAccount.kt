package com.algolia.search.client

import com.algolia.search.model.task.Task
import io.ktor.client.features.ResponseException
import io.ktor.http.HttpStatusCode


public object ClientAccount {

    public suspend fun copyIndex(source: Index, destination: Index): List<Task> {
        if (source.transport.applicationID == destination.transport.applicationID) {
            throw IllegalArgumentException("Source and Destination indices should not be on the same application.")
        }
        var hasThrown404 = false
        try {
            destination.getSettings()
        } catch (exception: ResponseException) {
            hasThrown404 = exception.response.status.value == HttpStatusCode.NotFound.value
            if (!hasThrown404) throw exception
        }
        if (!hasThrown404) {
            throw IllegalStateException("Destination index already exists. Please delete it before copying index across applications.")
        }

        val tasks = mutableListOf<Task>()

        destination.apply {
            tasks += setSettings(source.getSettings())
            source.browseRules().forEach { tasks += saveRules(it.hits.map { hit -> hit.rule }) }
            source.browseSynonyms().forEach { tasks += saveSynonyms(it.hits) }
            source.browseObjects().forEach { tasks += saveObjects(it.hits.map { hit -> hit.json }) }
        }
        return tasks
    }
}