package client

import com.algolia.search.saas.client.AlgoliaClient
import com.algolia.search.saas.data.*
import kotlinx.serialization.Serializable


internal val apiKey = ApiKey("dc8e9efcfe38f7fbfb996047af06d8c5")
internal val applicationId = ApplicationID("latency")
internal val client = AlgoliaClient(applicationId, apiKey)
internal val index = client.getIndex(IndexName("products_android_demo"))
internal val copy = client.getIndex(IndexName("products_android_demo_copy"))
internal val destination = client.getIndex(IndexName("products_android_demo_move"))

@Serializable
internal data class Data(
    val name: String,
    val count: Int,
    val brands: List<String>,
    override val objectID: ObjectID
) : Indexable

internal val name = Attribute("name")
internal val count = Attribute("count")
internal val brands = Attribute("brands")
internal val iphone = "iPhone"
internal val samsung = "Samsung"
internal val dataCreate = Data("Phone", 1000, listOf(iphone), ObjectID("test_suite"))
internal val dataUpdate = dataCreate.copy(name = "Telephone")