package client

import com.algolia.search.saas.data.Synonym
import com.algolia.search.saas.data.TaskStatus
import kotlinx.coroutines.runBlocking
import objectIDA
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestClientSynonym {

    private val synonyms = listOf("synonym1", "synonym2")

    @Test
    fun oneWay() {
        runBlocking {
            index.apply {
                saveSynonym(Synonym.OneWay(unknown, synonyms), objectIDA).wait().status shouldEqual TaskStatus.Published
                deleteSynonym(objectIDA).wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun multiWay() {
        runBlocking {
            index.apply {
                saveSynonym(Synonym.MultiWay(synonyms), objectIDA).wait().status shouldEqual TaskStatus.Published
                deleteSynonym(objectIDA).wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun alternativeOne() {
        alternative(Synonym.Typo.Two)
    }

    @Test
    fun alternativeTwo() {
        alternative(Synonym.Typo.One)
    }

    private fun alternative(typo: Synonym.Typo) {
        runBlocking {
            index.apply {
                saveSynonym(
                    Synonym.AlternativeCorrections(unknown, synonyms, typo),
                    objectIDA
                ).wait().status shouldEqual TaskStatus.Published
                deleteSynonym(objectIDA).wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun placeholder() {
        runBlocking {
            index.apply {
                saveSynonym(
                    Synonym.Placeholder("<placeholder>", synonyms),
                    objectIDA
                ).wait().status shouldEqual TaskStatus.Published
                deleteSynonym(objectIDA).wait().status shouldEqual TaskStatus.Published
            }
        }
    }
}