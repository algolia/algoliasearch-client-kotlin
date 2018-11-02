import client.query.AroundRadius
import client.query.QuerySerializable
import kotlinx.serialization.json.JSON
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class TestSerialization {


    @Test
    fun aroundRadius() {
        val inMeters = QuerySerializable(aroundRadius = AroundRadius.InMeters(10))
        val all = QuerySerializable(aroundRadius = AroundRadius.All)

        assertEquals("{\"aroundRadius\":10}", JSON.stringify(inMeters))
        assertEquals("{\"aroundRadius\":\"all\"}", JSON.stringify(all))
    }
}