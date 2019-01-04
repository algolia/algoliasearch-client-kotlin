package client

import com.algolia.search.saas.data.ObjectId
import com.algolia.search.saas.data.TaskStatus
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClientObjects {

    @Serializable
    private data class Data(
        val brand: String,
        val name: String
    )

    @Test
    fun addObject() {
        runBlocking {
            index.run {
                val create = addObject(Data("test", "test"), Data.serializer())
                val taskCreate = create.wait()

                taskCreate.status shouldEqual TaskStatus.Published

                val taskDelete = deleteObject(create.objectId).wait()

                taskDelete.status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun updateObject() {
        runBlocking {
            index.run {
                val objectId = ObjectId("test")
                val taskCreate = updateObject(Data("test", "test"), Data.serializer(), objectId).wait()

                taskCreate.status shouldEqual TaskStatus.Published

                val taskInfoDelete = deleteObject(objectId).wait()

                taskInfoDelete.status shouldEqual TaskStatus.Published
            }
        }
    }
}