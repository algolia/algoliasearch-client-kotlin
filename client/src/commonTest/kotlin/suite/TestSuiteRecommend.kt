package suite

import clientAdmin1
import clientRecommend
import com.algolia.search.model.IndexName
import com.algolia.search.model.ObjectID
import com.algolia.search.model.recommend.FrequencyBoughtTogether
import com.algolia.search.model.recommend.RecommendationModel
import com.algolia.search.model.recommend.RecommendationsQuery
import com.algolia.search.model.recommend.RelatedProducts
import kotlin.test.Test
import runBlocking

class TestSuiteRecommend {

    val indexName = IndexName("products")
    val index = clientAdmin1.initIndex(indexName)

   @Test
   fun testGetRecommends() {
       runBlocking {
           val query = RecommendationsQuery(
               indexName = indexName,
               model = RecommendationModel.BoughtTogether,
               objectID = ObjectID("B018APC4LE"),
               threshold = 0
           )
           val requests = listOf(query)
           clientRecommend.getRecommendations(requests)
       }
   }

    @Test
    fun testRelatedProducts() {
        runBlocking {
            val query = RelatedProducts(
                indexName = indexName,
                objectID = ObjectID("B018APC4LE"),
                threshold = 0
            )
            val requests = listOf(query)
            clientRecommend.getRelatedProducts(requests)
        }
    }

    @Test
    fun testFrequencyBoughtTogether() {
        runBlocking {
            val query = FrequencyBoughtTogether(
                indexName = indexName,
                objectID = ObjectID("B018APC4LE"),
                threshold = 0
            )
            val requests = listOf(query)
            clientRecommend.getFrequentlyBoughtTogether(requests)
        }
    }
}
