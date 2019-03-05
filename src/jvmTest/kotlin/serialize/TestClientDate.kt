package serialize

import com.algolia.search.ClientDate
import com.algolia.search.dateISO8601
import com.algolia.search.dateISO8601Millis
import com.algolia.search.serialize.KSerializerClientDate
import kotlinx.serialization.json.JsonLiteral
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*


@RunWith(JUnit4::class)
internal class TestClientDate : TestSerializer<ClientDate>(KSerializerClientDate) {

    private val date = Date(0)
    private val format = dateISO8601.format(date)
    private val formatMillis = dateISO8601Millis.format(date)

    override val items = listOf(
        ClientDate(format) to JsonLiteral(format),
        ClientDate(formatMillis) to JsonLiteral(formatMillis)
    )
}