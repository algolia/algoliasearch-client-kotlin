package com.algolia.search.endpoint

import com.algolia.search.dsl.filtering.FilterFacet
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.QueryID
import com.algolia.search.model.insights.EventName
import io.ktor.client.response.HttpResponse


public interface EndpointInsightsUser {

    suspend fun viewedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<FilterFacet>,
        timestamp: Long? = null
    ): HttpResponse

    suspend fun viewedObjectIDs(
        indexName: IndexName,
        eventName: EventName,
        objectIDs: List<ObjectID>,
        timestamp: Long? = null
    ): HttpResponse

    suspend fun clickedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<FilterFacet>,
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
        filters: List<FilterFacet>,
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