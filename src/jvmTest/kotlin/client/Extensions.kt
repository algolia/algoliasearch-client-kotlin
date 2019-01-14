package client

import com.algolia.search.saas.client.ClientAlgolia
import com.algolia.search.saas.data.*
import kotlinx.serialization.Serializable

internal val adminKey = APIKey(System.getenv("KOTLIN_CLIENT_ADMIN_KEY"))
internal val apiKey = APIKey(System.getenv("KOTLIN_CLIENT_API_KEY"))
internal val applicationId = ApplicationID(System.getenv("KOTLIN_CLIENT_APP_ID"))
internal val algolia = ClientAlgolia(applicationId, apiKey)
internal val index = algolia.getIndex(IndexName(System.getenv("KOTLIN_CLIENT_INDEX")))
internal val indexCopyA = algolia.getIndex(IndexName("${index.indexName}_copyA"))
internal val indexCopyB = algolia.getIndex(IndexName("${index.indexName}_copyB"))

@Serializable
internal data class Data(
    val name: String,
    val count: Int,
    val brands: List<String>,
    override val objectID: ObjectID
) : Indexable

internal val name = Attribute("name")
internal val count = Attribute("count")
internal val brand = Attribute("brand")
internal val iphone = "iPhone"
internal val samsung = "Samsung"
internal val dataCreate = Data("Phone", 1000, listOf(iphone), ObjectID("test_suite"))
internal val dataUpdate = dataCreate.copy(name = "Telephone")