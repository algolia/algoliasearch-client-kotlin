package serialize

import boolean
import com.algolia.search.saas.data.TypoTolerance
import com.algolia.search.saas.data.TypoTolerance.*
import com.algolia.search.saas.data.TypoTolerance.Boolean
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestTypoTolerance : TestSerializer<TypoTolerance>(TypoTolerance) {

    override val item = listOf(
        Boolean(boolean) to JsonPrimitive(boolean),
        Min to JsonPrimitive(Min.raw),
        Strict to JsonPrimitive(Strict.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
}