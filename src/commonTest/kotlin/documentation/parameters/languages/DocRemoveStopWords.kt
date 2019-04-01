package documentation.parameters.languages

import com.algolia.search.dsl.query
import com.algolia.search.dsl.queryLanguages
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.QueryLanguage
import com.algolia.search.model.search.RemoveStopWords
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocRemoveStopWords {

//    removeStopWords: RemoveStopWords = RemoveStopWords.Boolean|RemoveStopWords.QueryLanguages

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                queryLanguages {
                    +English
                }
                removeStopWords = RemoveStopWords.Boolean(true)
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                removeStopWords = RemoveStopWords.QueryLanguages(QueryLanguage.English)
            }

            index.search(query)
        }
    }
}