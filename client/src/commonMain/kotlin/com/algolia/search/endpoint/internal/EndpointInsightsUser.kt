@file:Suppress("FunctionName")

package com.algolia.search.endpoint.internal

import com.algolia.search.endpoint.EndpointInsights
import com.algolia.search.endpoint.EndpointInsightsUser
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.QueryID
import com.algolia.search.model.filter.Filter
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.InsightsEvent
import com.algolia.search.model.insights.UserToken
import io.ktor.client.statement.HttpResponse

internal class EndpointInsightsUserImpl(
    private val insights: EndpointInsights,
    private val userToken: UserToken,
) : EndpointInsightsUser {

    override suspend fun viewedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<Filter.Facet>,
        timestamp: Long?,
    ): HttpResponse {
        return insights.sendEvent(
            InsightsEvent.View(
                indexName = indexName,
                eventName = eventName,
                resources = InsightsEvent.Resources.Filters(filters),
                timestamp = timestamp,
                userToken = userToken
            )
        )
    }

    override suspend fun viewedObjectIDs(
        indexName: IndexName,
        eventName: EventName,
        objectIDs: List<ObjectID>,
        timestamp: Long?,
    ): HttpResponse {
        return insights.sendEvent(
            InsightsEvent.View(
                indexName = indexName,
                eventName = eventName,
                resources = InsightsEvent.Resources.ObjectIDs(objectIDs),
                timestamp = timestamp,
                userToken = userToken
            )
        )
    }

    override suspend fun clickedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<Filter.Facet>,
        timestamp: Long?,
    ): HttpResponse {
        return insights.sendEvent(
            InsightsEvent.Click(
                indexName = indexName,
                eventName = eventName,
                resources = InsightsEvent.Resources.Filters(filters),
                timestamp = timestamp,
                userToken = userToken
            )
        )
    }

    override suspend fun clickedObjectIDs(
        indexName: IndexName,
        eventName: EventName,
        objectIDs: List<ObjectID>,
        timestamp: Long?,
    ): HttpResponse {
        return insights.sendEvent(
            InsightsEvent.Click(
                indexName = indexName,
                eventName = eventName,
                resources = InsightsEvent.Resources.ObjectIDs(objectIDs),
                timestamp = timestamp,
                userToken = userToken
            )
        )
    }

    override suspend fun clickedObjectIDsAfterSearch(
        indexName: IndexName,
        eventName: EventName,
        queryID: QueryID,
        objectIDs: List<ObjectID>,
        positions: List<Int>,
        timestamp: Long?,
    ): HttpResponse {
        return insights.sendEvent(
            InsightsEvent.Click(
                indexName = indexName,
                eventName = eventName,
                resources = InsightsEvent.Resources.ObjectIDs(objectIDs),
                timestamp = timestamp,
                userToken = userToken,
                positions = positions,
                queryID = queryID
            )
        )
    }

    override suspend fun convertedFilters(
        indexName: IndexName,
        eventName: EventName,
        filters: List<Filter.Facet>,
        timestamp: Long?,
    ): HttpResponse {
        return insights.sendEvent(
            InsightsEvent.Conversion(
                indexName = indexName,
                eventName = eventName,
                resources = InsightsEvent.Resources.Filters(filters),
                timestamp = timestamp,
                userToken = userToken
            )
        )
    }

    override suspend fun convertedObjectIDs(
        indexName: IndexName,
        eventName: EventName,
        objectIDs: List<ObjectID>,
        timestamp: Long?,
    ): HttpResponse {
        return insights.sendEvent(
            InsightsEvent.Conversion(
                indexName = indexName,
                eventName = eventName,
                resources = InsightsEvent.Resources.ObjectIDs(objectIDs),
                timestamp = timestamp,
                userToken = userToken
            )
        )
    }

    override suspend fun convertedObjectIDsAfterSearch(
        indexName: IndexName,
        eventName: EventName,
        queryID: QueryID,
        objectIDs: List<ObjectID>,
        timestamp: Long?,
    ): HttpResponse {
        return insights.sendEvent(
            InsightsEvent.Conversion(
                indexName = indexName,
                eventName = eventName,
                resources = InsightsEvent.Resources.ObjectIDs(objectIDs),
                timestamp = timestamp,
                userToken = userToken,
                queryID = queryID
            )
        )
    }
}

/**
 * Create an [EndpointInsightsUser] instance.
 */
internal fun EndpointInsightsUser(
    insights: EndpointInsights,
    userToken: UserToken,
): EndpointInsightsUser = EndpointInsightsUserImpl(insights, userToken)
