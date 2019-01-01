package client

import indexA
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestClient {

    @Test
    fun indexCache() {
        val initFirst = client.getIndex(indexA)
        client.getIndex(indexA) shouldEqual initFirst
    }
}