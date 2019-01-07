package client

import com.algolia.search.saas.client.AlgoliaClient
import com.algolia.search.saas.data.ApiKey
import com.algolia.search.saas.data.ApplicationID
import com.algolia.search.saas.data.IndexName


internal val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
internal val applicationId = ApplicationID("latency")
internal val client = AlgoliaClient(applicationId, apiKey)
internal val index = client.getIndex(IndexName("products_android_demo"))
internal val copy = client.getIndex(IndexName("products_android_demo_copy"))
internal val destination = client.getIndex(IndexName("products_android_demo_move"))