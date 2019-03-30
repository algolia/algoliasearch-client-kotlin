package snippets.parameters.attributes

import com.algolia.search.dsl.query
import com.algolia.search.dsl.restrictSearchableAttributes
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import snippets.index
import kotlin.test.Test


internal class SnippetRestrictSearchableAttributes {

//    restrictSearchableAttributes {
//        +"attribute"
//    }

    @Test
    fun snippet() {
        shouldFailWith<ResponseException> {
            runBlocking {
                val query = query {
                    restrictSearchableAttributes {
                        +"title"
                        +"author"
                    }
                }

                index.search(query)
            }
        }
    }
}