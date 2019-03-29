package snippets.parameters.ranking

import com.algolia.search.dsl.replicas
import com.algolia.search.dsl.settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetReplicas : TestSnippets() {

    @Test
    fun parameter() {
        settings {
            replicas {
                +"replica_index1"
                +"replica_index2"
            }
        }
    }

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