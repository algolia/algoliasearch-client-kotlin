package client

import com.algolia.search.model.synonym.Synonym
import com.algolia.search.model.synonym.SynonymType
import com.algolia.search.model.common.TaskStatus
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
                saveSynonym(oneWay).wait() shouldEqual TaskStatus.Published
                getSynonym(oneWay.objectID) shouldEqual oneWay
                searchSynonyms().hits shouldContain oneWay
                deleteSynonym(oneWay.objectID).wait() shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun multiWay() {
        runBlocking {
            index.apply {
                saveSynonym(multiWay).wait() shouldEqual TaskStatus.Published
                getSynonym(multiWay.objectID) shouldEqual multiWay
                searchSynonyms().hits shouldContain multiWay
                deleteSynonym(multiWay.objectID).wait() shouldEqual TaskStatus.Published
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
                saveSynonym(alternative).wait() shouldEqual TaskStatus.Published
                getSynonym(alternative.objectID) shouldEqual alternative
                searchSynonyms().hits shouldContain alternative
                deleteSynonym(alternative.objectID).wait() shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun placeholder() {
        runBlocking {
            index.apply {
                val save = saveSynonym(placeholder)

                save.wait() shouldEqual TaskStatus.Published
                getSynonym(placeholder.objectID) shouldEqual placeholder
                searchSynonyms().hits shouldContain placeholder
                deleteSynonym(placeholder.objectID).wait() shouldEqual TaskStatus.Published
            }
        }
    }

    @Test
    fun suite() {
        runBlocking {
            index.apply {
                saveSynonyms(listOf(oneWay, placeholder)).wait() shouldEqual TaskStatus.Published
                searchSynonyms().hits.size shouldEqual 2
                clearSynonyms().wait() shouldEqual TaskStatus.Published
                searchSynonyms().hits.shouldBeEmpty()
            }
        }
    }
}