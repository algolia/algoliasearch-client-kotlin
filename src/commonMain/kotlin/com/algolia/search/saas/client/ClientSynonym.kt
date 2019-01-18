package com.algolia.search.saas.client

import com.algolia.search.saas.data.*
import com.algolia.search.saas.endpoint.EndpointSynonym
import com.algolia.search.saas.serialize.KeyForwardToReplicas
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.put
import kotlinx.serialization.json.Json


internal class ClientSynonym(
    val client: Client,
    override val indexName: IndexName
) : EndpointSynonym,
    Client by client {

    override suspend fun saveSynonym(
        synonym: Synonym,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): TaskUpdateSynonym {
        return write.retry(
            requestOptions.computedWriteTimeout,
            indexName.pathIndexes("/synonyms/${synonym.objectID}")
        ) { path ->
            httpClient.put<TaskUpdateSynonym>(path) {
                setRequestOptions(requestOptions)
                forwardToReplicas?.let { parameter(KeyForwardToReplicas, it) }
                body = Json.stringify(Synonym, synonym)
            }
        }
    }

    override suspend fun getSynonym(objectID: ObjectID, requestOptions: RequestOptions?): Synonym {
        return read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/synonyms/$objectID")) { path ->
            httpClient.get<Synonym>(path) {
                setRequestOptions(requestOptions)
            }
        }
    }

    override suspend fun deleteSynonym(
        objectID: ObjectID,
        forwardToReplicas: Boolean?,
        requestOptions: RequestOptions?
    ): TaskDelete {
        return write.retry(requestOptions.computedWriteTimeout, indexName.pathIndexes("/synonyms/$objectID")) { path ->
            httpClient.delete<TaskDelete>(path) {
                setRequestOptions(requestOptions)
                forwardToReplicas?.let { parameter(KeyForwardToReplicas, it) }
            }
        }
    }
}