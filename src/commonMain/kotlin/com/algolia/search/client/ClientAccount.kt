package com.algolia.search.client

import com.algolia.search.model.task.Task
import io.ktor.client.features.BadResponseStatusException
import io.ktor.http.HttpStatusCode


public object ClientAccount {

    public suspend fun copyIndex(source: Index, destination: Index): List<Task> {
        if (source.api.applicationID == destination.api.applicationID) {
            throw IllegalArgumentException("Source and Destination indices should not be on the same application.")
        }
        var hasThrown404 = false
        try {
            destination.getSettings()
        } catch (exception: BadResponseStatusException) {
            hasThrown404 = exception.statusCode == HttpStatusCode.NotFound
            if (!hasThrown404) throw exception
        }
        if (!hasThrown404) {
            throw IllegalStateException("Destination index already exists. Please delete it before copying index across applications.")
        }

        val tasks = mutableListOf<Task>()

        destination.apply {
            tasks += setSettings(source.getSettings())
            source.browseAllRules { tasks += saveRules(hits.map { it.rule }) }
            source.browseAllSynonyms { tasks += saveSynonyms(hits) }
            source.browseAllObjects { tasks += saveObjects(hits.map { it.json }) }
        }
        return tasks
    }
}