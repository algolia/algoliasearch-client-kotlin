package documentation.parameters.ranking

import com.algolia.search.dsl.replicas
import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocReplicas {

//    replicas {
//        +"replica_index1"
//        +"replica_index2"
//    }

    @Test
    fun snippet1() {
        runTest {
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
