package documentation.tutorials

import com.algolia.search.client.ClientSearch
import com.algolia.search.dsl.attributesForFaceting
import com.algolia.search.dsl.customRanking
import com.algolia.search.dsl.query
import com.algolia.search.dsl.rule.rules
import com.algolia.search.dsl.searchableAttributes
import com.algolia.search.dsl.settings
import com.algolia.search.model.APIKey
import com.algolia.search.model.ApplicationID
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.Test

@Ignore
internal class TutorialSetRelevance {

    @Test
    fun ecommerce() {
        runBlocking {
            val settings = settings {
                // Select the attributes you want to search in
                searchableAttributes {
                    +"brand"
                    +"name"
                    +"categories"
                    +"description"
                }
                // Define business metrics for ranking and sorting
                customRanking {
                    +Desc("popularity")
                }
                // Set up some attributes to filter results on
                attributesForFaceting {
                    +"categories"
                    +Searchable("brand")
                    +"price"
                }
            }

            val client = ClientSearch(
                ApplicationID("YourApplicationID"),
                APIKey("YourAPIKey")
            )
            val index = client.initIndex(IndexName("demo_ecommerce"))

            index.setSettings(settings)
        }
    }

    @Test
    fun saas() {
        runBlocking {
            val settings = settings {
                // Select the attributes you want to search in
                searchableAttributes {
                    +"Name"
                    +"Owner"
                    +"Account"
                    +"Email"
                    +"Website"
                }
                // Define business metrics for ranking and sorting
                customRanking {
                    +Desc("Amount")
                }
                // Set up some attributes to filter results on
                attributesForFaceting {
                    +"type"
                }
                attributeForDistinct = Attribute("type")
            }

            val client = ClientSearch(
                ApplicationID("YourApplicationID"),
                APIKey("YourAPIKey")
            )
            val index = client.initIndex(IndexName("demo_saas"))

            index.setSettings(settings)
        }
    }

    @Test
    fun media() {
        runBlocking {
            val settings = settings {
                // Select the attributes you want to search in
                searchableAttributes {
                    +"post_title"
                    +"author_name"
                    +"categories"
                    +"content"
                }
                // Define business metrics for ranking and sorting
                customRanking {
                    +Desc("post_date")
                    +Desc("record_index")
                }
                // Set up some attributes to filter results on
                attributesForFaceting {
                    +"categories"
                }
                // Define the attribute we want to distinct on
                attributeForDistinct = Attribute("post_id")
            }

            val client = ClientSearch(
                ApplicationID("YourApplicationID"),
                APIKey("YourAPIKey")
            )
            val index = client.initIndex(IndexName("demo_media"))

            index.setSettings(settings)
        }
    }

    @Test
    fun geo() {
        runBlocking {
            val settings = settings {
                searchableAttributes {
                    +"country"
                    +"city"
                    +"name"
                    +"airport_id"
                }
                customRanking {
                    +Desc("nb_airline_liaisons")
                }
                attributesForFaceting {
                    +"city"
                    +"country"
                }
            }

            val queryRules = rules {
                rule(
                    objectID = "country",
                    conditions = conditions {
                        +Condition(Contains, Facet("country"))
                    },
                    consequence = Consequence(query = query { aroundLatLngViaIP = false })
                )
                rule(
                    objectID = "city",
                    conditions = conditions {
                        +Condition(Contains, Facet("city"))
                    },
                    consequence = Consequence(query = query { aroundLatLngViaIP = false })
                )
            }

            val client = ClientSearch(
                ApplicationID("YourApplicationID"),
                APIKey("YourAPIKey")
            )
            val index = client.initIndex(IndexName("demo_geo"))

            // Configure relevancy
            index.setSettings(settings)
            // Add special rules
            index.saveRules(queryRules)
        }
    }
}
