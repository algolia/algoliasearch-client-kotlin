package documentation.parameters.ranking

import com.algolia.search.dsl.replicas
import com.algolia.search.dsl.settings
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocReplicas : TestDocumentation() {

//    replicas {
//        +"replica_index1"
//        +"replica_index2"
//    }

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                replicas {
                    +"name_of_replica_index1"
                    +"name_of_replica_index2"
                }
            }

            index.setSettings(settings)
        }
    }
}