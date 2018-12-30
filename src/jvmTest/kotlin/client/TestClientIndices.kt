package client

import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
internal class TestClientIndices {

    @Test
    fun listIndexes() {
        runBlocking {
            index.getListIndexes()
        }
    }
}