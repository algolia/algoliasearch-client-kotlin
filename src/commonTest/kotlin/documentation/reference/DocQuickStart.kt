package documentation.reference

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.multipleindex.BatchOperationIndex
import com.algolia.search.model.search.Query
import com.algolia.search.model.settings.CustomRankingCriterium
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import io.ktor.client.features.ResponseException
import kotlinx.serialization.Serializable
import runBlocking
import shouldFailWith
import documentation.TestDocumentation
import kotlin.test.Test


@Suppress("RemoveExplicitTypeArguments")
internal class DocQuickStart : TestDocumentation() {

    @Test
    fun attributesOrder() {
        runBlocking {
            val lastname = Attribute("lastname")
            val firstname = Attribute("firstname")
            val company = Attribute("company")
            val settings = Settings(
                searchableAttributes = listOf(
                    SearchableAttribute.Default(lastname),
                    SearchableAttribute.Default(firstname),
                    SearchableAttribute.Default(company)
                )
            )

            index.setSettings(settings)
        }
    }

    @Test
    fun customRanking() {
        runBlocking {
            val followers = Attribute("followers")
            val settings = Settings(
                customRanking = listOf(CustomRankingCriterium.Desc(followers))
            )

            index.setSettings(settings)
        }
    }

    @Test
    fun indexing() {
        runBlocking {
            @Serializable
            data class Contact(
                val firstname: String,
                val lastname: String,
                val followers: Int,
                val company: String
            )

            val contacts = listOf(
                Contact("Jimmie", "Barninger", 93, "California Paint"),
                Contact("Warren", "Speach", 42, "Norwalk Crmc")
            )
            val index = client.initIndex(IndexName("contacts"))

            index.apply {
                saveObjects(Contact.serializer(), contacts).wait()
            }
        }
    }

    @Test
    fun search() {
        shouldFailWith<ResponseException> {
            runBlocking {
                // Search for a first name
                index.search(Query("jimmie"))
                // Search for a first name with typo
                index.search(Query("jimie"))
                // Search for a company
                index.search(Query("california paint"))
                // Search for a first name and a company
                index.search(Query("jimmie paint"))
            }
        }
    }

    @Test
    fun waitOperation() {
        shouldFailWith<Exception> {
            runBlocking {
                index.apply {
                    setSettings(Settings()).wait()
                }
                client.run {
                    multipleBatchObjects(listOf<BatchOperationIndex>()).waitAll()
                }
            }
        }
    }
}