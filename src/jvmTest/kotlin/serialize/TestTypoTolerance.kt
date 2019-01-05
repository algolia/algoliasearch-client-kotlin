package serialize

import boolean
import com.algolia.search.saas.data.TypoTolerance
import com.algolia.search.saas.data.TypoTolerance.*
import com.algolia.search.saas.data.TypoTolerance.Boolean
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestTypoTolerance : TestSerializer<TypoTolerance>(TypoTolerance) {

    override val items = listOf(
        Boolean(boolean),
        Min,
        Strict,
        Other(unknown)
    )
}