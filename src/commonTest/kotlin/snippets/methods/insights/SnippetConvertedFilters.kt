package snippets.methods.insights

import clientInsights
import com.algolia.search.dsl.filtering.FilterFacet
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.insights.EventName
import com.algolia.search.model.insights.UserToken
import runBlocking
import kotlin.test.Test


internal class SnippetConvertedFilters {

//    suspend fun ClientInsights.User.convertedFilters(
//        #{indexName}: __IndexName__,
//        #{eventName}: __EventName__,
//        #{filters}: __List<FilterFacet>__,
//        timestamp: __Long?__ = null
//    ): HttpResponse

    @Test
    fun convertedFilters() {
        runBlocking {
            val userToken = UserToken("user-id")

            clientInsights.User(userToken).convertedFilters(
                indexName = IndexName("indexName"),
                eventName = EventName("eventName"),
                filters = listOf(FilterFacet(Attribute("brand"), "apple"))
            )
        }
    }
}