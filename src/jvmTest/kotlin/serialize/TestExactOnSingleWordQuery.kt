package serialize

import com.algolia.search.saas.data.ExactOnSingleWordQuery
import com.algolia.search.saas.data.ExactOnSingleWordQuery.*
import kotlinx.serialization.json.JsonPrimitive
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestExactOnSingleWordQuery : TestSerializer<ExactOnSingleWordQuery>(ExactOnSingleWordQuery) {

    override val item = listOf(
        Attribute to JsonPrimitive(Attribute.raw),
        None to JsonPrimitive(None.raw),
        Word to JsonPrimitive(Word.raw),
        Unknown(unknown) to JsonPrimitive(unknown)
    )
}