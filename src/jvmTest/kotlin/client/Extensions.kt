package client

import client.client.Client
import client.data.ApiKey
import client.data.ApplicationId
import client.data.IndexName

internal val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
internal val applicationId = ApplicationId("latency")
internal val client = Client(applicationId, apiKey)
internal val indexName = IndexName("products_android_demo")
internal val index = client.getIndex(indexName)