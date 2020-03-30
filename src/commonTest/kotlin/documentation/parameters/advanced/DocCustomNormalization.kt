package documentation.parameters.advanced

import com.algolia.search.dsl.settings
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

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
        runBlocking {
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
