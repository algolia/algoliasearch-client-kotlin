package com.algolia.search.endpoint

import com.algolia.search.Time
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.QueryID
import com.algolia.search.model.insights.EventName
import com.algolia.search.query.FilterFacet
import io.ktor.client.response.HttpResponse


interface EndpointInsightsUser {

    suspend fun viewedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<FilterFacet>,
        timestamp: Long = Time.getCurrentTimeMillis()
    ): HttpResponse

    suspend fun viewedObjectIDs(
        indexName: IndexName,
        eventName: EventName,
        objectIDs: List<ObjectID>,
        timestamp: Long = Time.getCurrentTimeMillis()
    ): HttpResponse

    suspend fun clickedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<FilterFacet>,
        timestamp: Long = Time.getCurrentTimeMillis()
    ): HttpResponse

    suspend fun clickedObjectIDs(
        indexName: IndexName,
        eventName: EventName,
        objectIDs: List<ObjectID>,
        timestamp: Long = Time.getCurrentTimeMillis()
    ): HttpResponse

    suspend fun clickedObjectIDsAfterSearch(
        indexName: IndexName,
        eventName: EventName,
        queryId: QueryID,
        objectIDs: List<ObjectID>,
        positions: List<Int>,
        timestamp: Long = Time.getCurrentTimeMillis()
    ): HttpResponse

    suspend fun convertedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<FilterFacet>,
        timestamp: Long = Time.getCurrentTimeMillis()
    ): HttpResponse

    suspend fun convertedObjectIDs(
        indexName: IndexName,
        eventName: EventName,
        objectIDs: List<ObjectID>,
        timestamp: Long = Time.getCurrentTimeMillis()
    ): HttpResponse

    suspend fun convertedObjectIDsAfterSearch(
        indexName: IndexName,
        eventName: EventName,
        queryId: QueryID,
        objectIDs: List<ObjectID>,
        timestamp: Long = Time.getCurrentTimeMillis()
    ): HttpResponse
}