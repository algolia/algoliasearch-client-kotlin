package client

import com.algolia.search.saas.data.Synonym
import com.algolia.search.saas.data.SynonymType
import com.algolia.search.saas.data.TaskStatus
import kotlinx.coroutines.runBlocking
import objectIDA
import objectIDB
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import shouldBeEmpty
import shouldContain
import shouldEqual
import unknown


@RunWith(JUnit4::class)
internal class TestClientSynonym {

    private val synonyms = listOf("synonym1", "synonym2")
    private val oneWay = Synonym.OneWay(objectIDA, unknown, synonyms)
    private val multiWay = Synonym.MultiWay(objectIDA, synonyms)
    private val alternativeOne = Synonym.AlternativeCorrections(objectIDB, unknown, synonyms, SynonymType.Typo.One)
    private val alternativeTwo = Synonym.AlternativeCorrections(objectIDB, unknown, synonyms, SynonymType.Typo.Two)
    private val placeholder = Synonym.Placeholder(objectIDB, "<placeholder>", synonyms)

    @Test
    fun oneWay() {
        runBlocking {
            index.apply {
                saveSynonym(oneWay).wait().status shouldEqual TaskStatus.Published
                getSynonym(oneWay.objectID) shouldEqual oneWay
                searchSynonym().hits shouldContain oneWay
                deleteSynonym(oneWay.objectID).wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun multiWay() {
        runBlocking {
            index.apply {
                saveSynonym(multiWay).wait().status shouldEqual TaskStatus.Published
                getSynonym(multiWay.objectID) shouldEqual multiWay
                searchSynonym().hits shouldContain multiWay
                deleteSynonym(multiWay.objectID).wait().status shouldEqual TaskStatus.Published
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
                getSynonym(alternative.objectID) shouldEqual alternative
                searchSynonym().hits shouldContain alternative
                deleteSynonym(alternative.objectID).wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun placeholder() {
        runBlocking {
            index.apply {
                val save = saveSynonym(placeholder)

                save.wait().status shouldEqual TaskStatus.Published
                getSynonym(placeholder.objectID) shouldEqual placeholder
                searchSynonym().hits shouldContain placeholder
                deleteSynonym(placeholder.objectID).wait().status shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun suite() {
        runBlocking {
            index.apply {
                saveSynonyms(listOf(oneWay, placeholder)).wait().status shouldEqual TaskStatus.Published
                searchSynonym().hits.size shouldEqual 2
                clearSynonyms().wait().status shouldEqual TaskStatus.Published
                searchSynonym().hits.shouldBeEmpty()
            }
        }
    }
}