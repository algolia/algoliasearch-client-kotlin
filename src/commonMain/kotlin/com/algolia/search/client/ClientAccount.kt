package com.algolia.search.client

import com.algolia.search.browseAllObjects
import com.algolia.search.browseAllRules
import com.algolia.search.browseAllSynonyms
import com.algolia.search.model.task.Task
import io.ktor.client.features.BadResponseStatusException
import io.ktor.http.HttpStatusCode


object ClientAccount {

    suspend fun copyIndex(source: Index, destination: Index): List<Task> {
        println(source.api.applicationID)
        println(destination.api.applicationID)
        if (source.api.applicationID == destination.api.applicationID) {
            throw Exception("Source and Destination indices should not be on the same application.")
        }
        var hasThrown404 = false
        try {
            destination.getSettings()
        } catch (exception: BadResponseStatusException) {
            hasThrown404 = exception.statusCode == HttpStatusCode.NotFound
            if (!hasThrown404) throw exception
        }
        if (!hasThrown404) {
            throw Exception("Destination index already exists. Please delete it before copying index across applications.")
        }

        val tasks = mutableListOf<Task>()

        destination.apply {
            tasks += setSettings(source.getSettings())
            source.browseAllRules { tasks += saveRules(hits.map { it.rule }) }
            source.browseAllSynonyms { tasks += saveSynonyms(hits) }
            source.browseAllObjects { hits?.let { tasks += saveObjects(it.map { it.json }) } }
        }
        return tasks
    }
}