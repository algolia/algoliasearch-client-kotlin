package documentation.parameters.personalization

import com.algolia.search.dsl.query
import documentation.index
import kotlinx.coroutines.test.runTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocPersonalizationImpact {

//    personalizationImpact: Int = personalization_impact

    @Test
    fun snippet1() {
        runTest {
            val query = query("query") {
                personalizationImpact = 20
            }

            index.search(query)
        }
    }
}
