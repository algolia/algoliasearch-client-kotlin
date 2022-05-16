package documentation.parameters.typos

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.TypoTolerance
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocTypoTolerance {

//    typoTolerance: TypoTolerance =
//    [TypoTolerance.True](#parameter-option-true)
//    | [TypoTolerance.False](#parameter-option-false)
//    | [TypoTolerance.Min](#parameter-option-min)
//    | [TypoTolerance.Strict](#parameter-option-strict)

    @Test
    fun snippet1() {
        runTest {
            val settings = settings {
                typoTolerance = TypoTolerance.True
                // typoTolerance = TypoTolerance.False
                // typoTolerance = TypoTolerance.Min
                // typoTolerance = TypoTolerance.Strict
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun snippet2() {
        runTest {
            val query = query("query") {
                typoTolerance = TypoTolerance.True
                // typoTolerance = TypoTolerance.False
                // typoTolerance = TypoTolerance.Min
                // typoTolerance = TypoTolerance.Strict
            }

            index.search(query)
        }
    }
}
