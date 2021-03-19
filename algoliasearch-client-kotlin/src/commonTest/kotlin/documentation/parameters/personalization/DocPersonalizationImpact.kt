package documentation.parameters.personalization

import com.algolia.search.dsl.query
import documentation.index
import runBlocking
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
internal class DocPersonalizationImpact {

//    personalizationImpact: Int = personalization_impact

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                personalizationImpact = 20
            }

            index.search(query)
        }
    }
}
