package data

import client.data.IndexName
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestIndexName {

    @Test
    fun pathIndexes() {
        val index = IndexName("somePath")

        index.pathIndexes() shouldEqual "/1/indexes/somePath"
        index.pathIndexes("/someSuffix") shouldEqual "/1/indexes/somePath/someSuffix"
    }
}