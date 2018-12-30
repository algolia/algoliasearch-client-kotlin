package client.client

import client.RequestOptions
import client.data.IndexName
import client.data.ListIndexes
import client.setRequestOptions
import io.ktor.client.request.get


class ClientIndices(
    val client: Client,
    override val indexName: IndexName
) : EndpointsIndices {

    override suspend fun getListIndexes(requestOptions: RequestOptions?): ListIndexes {
        return client.run {
            read.retry(requestOptions.computedReadTimeout, "/1/indexes") { path ->
                httpClient.get<ListIndexes>(path) {
                    setRequestOptions(requestOptions)
                }
            }
        }
    }
}