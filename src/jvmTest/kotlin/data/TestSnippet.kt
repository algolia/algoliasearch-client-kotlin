package data

import attributeA
import attributeAll
import attributeB
import client.data.Snippet
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual


@RunWith(JUnit4::class)
internal class TestSnippet {

    @Test
    fun raw() {
        attributeA.raw shouldEqual Snippet(attributeA).raw
        "*" shouldEqual Snippet(attributeAll).raw
        "*:20" shouldEqual Snippet(attributeAll, 20).raw
        "$attributeB:10" shouldEqual Snippet(attributeB, 10).raw
    }
}