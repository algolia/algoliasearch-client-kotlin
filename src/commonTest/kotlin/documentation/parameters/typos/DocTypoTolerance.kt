package documentation.parameters.typos

import com.algolia.search.dsl.query
import com.algolia.search.dsl.settings
import com.algolia.search.model.search.TypoTolerance
import documentation.TestDocumentation
import runBlocking
import kotlin.test.Test


internal class DocTypoTolerance : TestDocumentation() {

//    typoTolerance: TypoTolerance =
//    [TypoTolerance.Boolean(true)](#parameter-option-true)
//    | [TypoTolerance.Boolean(false)](#parameter-option-false)
//    | [TypoTolerance.Min](#parameter-option-min)
//    | [TypoTolerance.Strict](#parameter-option-strict)

    @Test
    fun settings() {
        runBlocking {
            val settings = settings {
                typoTolerance = TypoTolerance.Boolean(true)
//                typoTolerance = TypoTolerance.Boolean(false)
//                typoTolerance = TypoTolerance.Min
//                typoTolerance = TypoTolerance.Strict
            }

            index.setSettings(settings)
        }
    }

    @Test
    fun query() {
        runBlocking {
            val query = query("query") {
                typoTolerance = TypoTolerance.Boolean(true)
//                typoTolerance = TypoTolerance.Boolean(false)
//                typoTolerance = TypoTolerance.Min
//                typoTolerance = TypoTolerance.Strict
            }

            index.search(query)
        }
    }

}