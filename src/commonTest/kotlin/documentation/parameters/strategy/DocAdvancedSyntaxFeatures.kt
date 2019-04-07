package documentation.parameters.strategy

import com.algolia.search.dsl.advancedSyntaxFeatures
import com.algolia.search.dsl.settings
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test


@Ignore
internal class DocAdvancedSyntaxFeatures {

//    advancedSyntaxFeatures {
//        +ExactPhrase
//        +ExcludeWords
//    }

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                advancedSyntaxFeatures {
                    +ExactPhrase
                    +ExcludeWords
                }
            }

            index.setSettings(settings)
        }
    }
}