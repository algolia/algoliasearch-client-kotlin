package helper

import com.algolia.search.helper.sha256
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestHashing {

    @Test
    fun sha256() {
        "1234".sha256("test") shouldEqual "24c4f0295e1bea74f9a5cb5bc40525c8889d11c78c4255808be00defe666671f"
    }
}