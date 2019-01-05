package serialize

import com.algolia.search.saas.data.ExactOnSingleWordQuery
import com.algolia.search.saas.data.ExactOnSingleWordQuery.*
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestExactOnSingleWordQuery : TestSerializer<ExactOnSingleWordQuery>(ExactOnSingleWordQuery) {

    override val items = listOf(
        Attribute to JsonLiteral(Attribute.raw),
        None to JsonLiteral(None.raw),
        Word to JsonLiteral(Word.raw),
        Other(unknown) to JsonLiteral(unknown)
    )
}