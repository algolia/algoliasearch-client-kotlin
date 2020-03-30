package documentation.guides.results.sorting

import com.algolia.search.dsl.settings
import com.algolia.search.model.search.TypoTolerance
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class GuideForwardReplicas {

    @Test
    fun snippet1() {
        runBlocking {
            val settings = settings {
                typoTolerance = TypoTolerance.Strict
            }

            index.setSettings(settings, forwardToReplicas = true)
        }
    }
}
