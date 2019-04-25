package com.algolia.search.endpoint

import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.QueryID
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.InsightsEvent
import io.ktor.client.response.HttpResponse


public interface EndpointInsightsUser {

    /**
     * Send a [InsightsEvent.View] to capture the [filters] a user uses when viewing.
     *
     * @param indexName Name of the index related to the view.
     * @param eventName Name of the event.
     * @param filters A list of [Filter.Facet].
     * @param timestamp An optional timestamp for the time of the event.
     * The server will automatically assign a timestamp if no value is passed
     */
    suspend fun viewedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<Filter.Facet>,
        timestamp: Long? = null
    ): HttpResponse

    /**
     * Send a [InsightsEvent.View] to capture clicked items.
     *
     * @param indexName Name of the index related to the view.
     * @param eventName Name of the event.
     * @param objectIDs A list of [ObjectID].
     * @param timestamp An optional timestamp for the time of the event.
     * The server will automatically assign a timestamp if no value is passed
     */
    suspend fun viewedObjectIDs(
        indexName: IndexName,
        eventName: EventName,
        objectIDs: List<ObjectID>,
        timestamp: Long? = null
    ): HttpResponse

    suspend fun clickedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<Filter.Facet>,
        timestamp: Long? = null
    ): HttpResponse

    suspend fun clickedObjectIDs(
        indexName: IndexName,
        eventName: EventName,
        objectIDs: List<ObjectID>,
        timestamp: Long? = null
    ): HttpResponse

    suspend fun clickedObjectIDsAfterSearch(
        indexName: IndexName,
        eventName: EventName,
        queryID: QueryID,
        objectIDs: List<ObjectID>,
        positions: List<Int>,
        timestamp: Long? = null
    ): HttpResponse

    suspend fun convertedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<Filter.Facet>,
        timestamp: Long? = null
    ): HttpResponse

    suspend fun convertedObjectIDs(
        indexName: IndexName,
        eventName: EventName,
        objectIDs: List<ObjectID>,
        timestamp: Long? = null
    ): HttpResponse

    suspend fun convertedObjectIDsAfterSearch(
        indexName: IndexName,
        eventName: EventName,
        queryID: QueryID,
        objectIDs: List<ObjectID>,
        timestamp: Long? = null
    ): HttpResponse
}