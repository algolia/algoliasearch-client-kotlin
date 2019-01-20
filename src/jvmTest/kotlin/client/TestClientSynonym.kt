package client

import com.algolia.search.saas.data.Synonym
import com.algolia.search.saas.data.SynonymType
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
    private val oneWay = Synonym.OneWay(objectIDA, unknown, synonyms)
    private val multiWay = Synonym.MultiWay(objectIDA, synonyms)
    private val alternativeOne = Synonym.AlternativeCorrections(objectIDA, unknown, synonyms, SynonymType.Typo.One)
    private val alternativeTwo = Synonym.AlternativeCorrections(objectIDA, unknown, synonyms, SynonymType.Typo.Two)
    private val placeholder = Synonym.Placeholder(objectIDA, "<placeholder>", synonyms)

    @Test
    fun oneWay() {
        runBlocking {
            index.apply {
                saveSynonym(oneWay).wait().status shouldEqual TaskStatus.Published
                getSynonym(objectIDA) shouldEqual oneWay
                deleteSynonym(objectIDA).wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun multiWay() {
        runBlocking {
            index.apply {
                saveSynonym(multiWay).wait().status shouldEqual TaskStatus.Published
                getSynonym(objectIDA) shouldEqual multiWay
                deleteSynonym(objectIDA).wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun alternativeOne() {
        alternative(alternativeOne)
    }

    @Test
    fun alternativeTwo() {
        alternative(alternativeTwo)
    }

    private fun alternative(alternative: Synonym.AlternativeCorrections) {
        runBlocking {
            index.apply {
                saveSynonym(alternative).wait().status shouldEqual TaskStatus.Published
                getSynonym(objectIDA) shouldEqual alternative
                deleteSynonym(objectIDA).wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun placeholder() {
        runBlocking {
            index.apply {
                val save = saveSynonym(placeholder)

                save.wait().status shouldEqual TaskStatus.Published
                getSynonym(objectIDA) shouldEqual placeholder
                deleteSynonym(objectIDA).wait().status shouldEqual TaskStatus.Published
            }
        }
    }
}