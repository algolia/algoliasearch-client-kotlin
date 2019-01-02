package serialize

import com.algolia.search.saas.data.AroundRadius
import com.algolia.search.saas.data.AroundRadius.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestAroundRadius : TestSerializer<AroundRadius>(AroundRadius) {

    override val items = listOf(
        All,
        InMeters(10),
        Unknown(unknown)
    )
}