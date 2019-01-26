package serialize

import boolean
import com.algolia.search.model.enums.TypoTolerance
import com.algolia.search.model.enums.TypoTolerance.*
import com.algolia.search.model.enums.TypoTolerance.Boolean
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestTypoTolerance : TestSerializer<TypoTolerance>(TypoTolerance) {

    override val items = listOf(
        Boolean(boolean) to JsonLiteral(boolean),
        Min to JsonLiteral(Min.raw),
        Strict to JsonLiteral(Strict.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}