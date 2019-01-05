package client

import com.algolia.search.saas.data.Attribute
import com.algolia.search.saas.data.Indexable
import com.algolia.search.saas.data.ObjectId
import com.algolia.search.saas.data.TaskStatus
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientIndexing {

    @Serializable
    private data class Data(
        val brand: String,
        val name: String
    )

    @Serializable
    private data class Product(
        @Optional val brand: String? = null,
        @Optional val name: String? = null,
        @Optional val price: Int? = null,
        @Optional val color: String? = null,
        @Optional val material: String? = null,
        @Optional val cotton: String? = null,
        @Optional val nbView: Int? = null,
        @Optional val nbLike: Int? = null,
        @Optional val nbWish: Int? = null,
        override val objectID: ObjectId
    ) : Indexable

    private val data = Data("test", "test")
    private val dataJson = Json.nonstrict.toJson(data, Data.serializer())

    @Test
    fun addObject() {
        runBlocking {
            index.run {
                val create = addObject(data, Data.serializer())
                val taskCreate = create.wait()

                taskCreate.status shouldEqual TaskStatus.Published

                val taskDelete = deleteObject(create.objectID).wait()

                taskDelete.status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun addObjectJson() {
        runBlocking {
            index.run {
                val create = addObject(dataJson)
                val taskCreate = create.wait()

                taskCreate.status shouldEqual TaskStatus.Published

                val taskDelete = deleteObject(create.objectID).wait()

                taskDelete.status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun updateObject() {
        runBlocking {
            index.run {
                val objectId = ObjectId("test")
                val taskCreate = updateObject(data, Data.serializer(), objectId).wait()

                taskCreate.status shouldEqual TaskStatus.Published

                val taskInfoDelete = deleteObject(objectId).wait()

                taskInfoDelete.status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun updateObjectJson() {
        runBlocking {
            index.run {
                val objectId = ObjectId("test")
                val taskCreate = updateObject(dataJson, objectId).wait()

                taskCreate.status shouldEqual TaskStatus.Published

                val taskInfoDelete = deleteObject(objectId).wait()

                taskInfoDelete.status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun getObject() {
        runBlocking {
            index.run {
                val objectId = ObjectId("442854")
                val product = Product(
                    brand = "Hermès",
                    name = "Birkin 35 So Black",
                    price = 18500,
                    color = "Black",
                    material = "Leather",
                    nbView = 5226,
                    nbLike = 381,
                    nbWish = 241,
                    objectID = objectId
                )

                index.run {
                    getObject(Product.serializer(), objectId) shouldEqual product
                }
            }
        }
    }

    @Test
    fun getObjectJson() {
        runBlocking {
            index.run {
                val objectId = ObjectId("442854")
                val product = json {
                    "brand" to "Hermès"
                    "name" to "Birkin 35 So Black"
                    "price" to 18500
                    "color" to "Black"
                    "material" to "Leather"
                    "nbView" to 5226
                    "nbLike" to 381
                    "nbWish" to 241
                    "priceUSD" to 25010.15
                    "objectID" to objectId.raw
                }

                index.run {
                    getObject(objectId) shouldEqual product
                }
            }
        }
    }

    @Test
    fun getObjectSearchableAttributes() {
        runBlocking {
            index.run {
                val objectId = ObjectId("442854")
                val product = Product(
                    brand = "Hermès",
                    name = "Birkin 35 So Black",
                    objectID = objectId
                )

                index.run {
                    getObject(
                        Product.serializer(),
                        objectId,
                        listOf(Attribute("brand"), Attribute("name"))
                    ) shouldEqual product
                }
            }
        }
    }

    @Test
    fun clear() {
        runBlocking {
            index.run {
                copyIndex(copy.indexName).wait().status shouldEqual TaskStatus.Published
            }
            copy.run {
                clearObjects().wait().status shouldEqual TaskStatus.Published
            }
        }
    }
}