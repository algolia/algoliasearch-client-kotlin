package documentation.parameters.advanced

import com.algolia.search.dsl.settings
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
class DocCustomNormalization {

//    customNormalization: Map<String, Map<String, String>> = mapOf(
//        "default" to mapOf(
//            "ä" to "ae",
//            "ü" to "ue"
//        )
//    )

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                customNormalization = mapOf(
                    "default" to mapOf(
                        "ä" to "ae",
                        "ü" to "ue"
                    )
                )
            }

            index.setSettings(settings)
        }
    }
}
