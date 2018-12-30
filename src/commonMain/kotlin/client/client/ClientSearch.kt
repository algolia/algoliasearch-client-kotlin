package client.client

import client.RequestOptions
import client.data.*
import client.serialize.KeyFacetQuery
import client.serialize.KeyMaxFacetHits
import client.setBody
import client.setQueries
import client.setRequestOptions
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json


class ClientSearch(
    val client: Client,
    override val indexName: IndexName
) : EndpointsSearch {

    override suspend fun getListIndexes(requestOptions: RequestOptions?): ListIndexes {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
                httpClient.get<ListIndexes>(path) {
                    setRequestOptions(requestOptions)
                }
            }
        }
    }

    override suspend fun search(requestOptions: RequestOptions?): Hits {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes()) { path ->
                httpClient.get<Hits>(path) {
                    setRequestOptions(requestOptions)
                }
            }
        }
    }

    override suspend fun search(query: Query?, requestOptions: RequestOptions?): Hits {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/query")) { path ->
                httpClient.post<Hits>(path) {
                    setRequestOptions(requestOptions)
                    setBody(query)
                }
            }
        }
    }

    override suspend fun browse(query: Query?, requestOptions: RequestOptions?): Hits {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/browse")) { path ->
                httpClient.post<Hits>(path) {
                    setRequestOptions(requestOptions)
                    setBody(query)
                }
            }
        }
    }

    override suspend fun browse(cursor: String, requestOptions: RequestOptions?): Hits {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, indexName.pathIndexes("/browse")) { path ->
                httpClient.get<Hits>(path) {
                    setRequestOptions(requestOptions)
                    parameter("cursor", cursor)
                }
            }
        }
    }

    override suspend fun multipleQueries(
        queries: Collection<IndexQuery>,
        strategy: MultipleQueriesStrategy,
        requestOptions: RequestOptions?
    ): MultipleHits {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, "/1/indexes/*/queries") { path ->
                httpClient.post<MultipleHits>(path) {
                    setRequestOptions(requestOptions)
                    setQueries(queries, strategy)
                }
            }
        }
    }

    override suspend fun searchForFacetValue(
        facetName: String,
        query: Query?,
        facetQuery: String?,
        maxFacetHits: Int?,
        requestOptions: RequestOptions?
    ): FacetHits {
        return client.run {
            read.retry(
                requestOptions.computedReadTimeout,
                indexName.pathIndexes("/facets/$facetName/query")
            ) { path ->
                httpClient.post<FacetHits>(path) {
                    setRequestOptions(requestOptions)
                    val extraParams = json {
                        maxFacetHits?.let { KeyMaxFacetHits to it }
                        facetQuery?.let { KeyFacetQuery to it }
                    }

                    body = if (query != null) {
                        val serialize = Query.serialize(query)
                        val map = serialize.toMutableMap()

                        map.putAll(extraParams)
                        JsonObject(map)
                    } else {
                        extraParams
                    }.toString()
                }
            }
        }
    }
}