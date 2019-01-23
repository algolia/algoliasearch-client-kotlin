package client

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeTrue


@RunWith(JUnit4::class)
internal class TestClientMultiCluster {

    @Test
    fun list() {
        runBlocking {
            (multiCluster.listClusters().size >= 2).shouldBeTrue()
        }
    }
}