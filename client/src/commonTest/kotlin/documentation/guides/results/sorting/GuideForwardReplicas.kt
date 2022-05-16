package documentation.guides.results.sorting

import com.algolia.search.dsl.settings
import com.algolia.search.model.search.TypoTolerance
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class GuideForwardReplicas {

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                typoTolerance = TypoTolerance.Strict
            }

            index.setSettings(settings, forwardToReplicas = true)
        }
    }
}
