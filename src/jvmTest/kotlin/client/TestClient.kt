package client

import indexA
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import shouldNotBeNull


@RunWith(JUnit4::class)
@Ignore
internal class TestClient {

    @Test
    fun testApiKey() {
        apiKey.raw.shouldNotBeNull()
    }

    @Test
    fun testAppID() {
        applicationId.raw.shouldNotBeNull()
    }

    @Test
    fun indexCache() {
        val initFirst = algolia.getIndex(indexA)
        algolia.getIndex(indexA) shouldEqual initFirst
    }
}