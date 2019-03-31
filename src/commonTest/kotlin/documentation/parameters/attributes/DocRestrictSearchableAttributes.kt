package documentation.parameters.attributes

import com.algolia.search.dsl.query
import com.algolia.search.dsl.restrictSearchableAttributes
import documentation.index
import io.ktor.client.features.ResponseException
import runBlocking
import shouldFailWith
import kotlin.test.Test


internal class DocRestrictSearchableAttributes {

//    restrictSearchableAttributes {
//        +"attribute"
//    }

    @Test
    fun query() {
        shouldFailWith<ResponseException> {
            runBlocking {
                val query = query("query") {
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