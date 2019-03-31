package documentation.parameters.ranking

import com.algolia.search.dsl.replicas
import com.algolia.search.dsl.settings
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocReplicas : TestDocumentation() {

//    replicas {
//        +"replica_index1"
//        +"replica_index2"
//    }

    @Test
    fun snippet() {
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