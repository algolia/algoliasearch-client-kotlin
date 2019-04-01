package dsl

import attributeA
import com.algolia.search.dsl.*
import indexA
import shouldNotBeEmpty
import unknown
import kotlin.test.Test


internal class TestDSLSettings {

    @Test
    fun searchableAttributes() {
        val settings = settings {
            searchableAttributes { +attributeA }
        }

        settings.searchableAttributes!!.shouldNotBeEmpty()
    }

    @Test
    fun attributesForFaceting() {
        val settings = settings {
            attributesForFaceting { +attributeA }
        }

        settings.attributesForFaceting!!.shouldNotBeEmpty()
    }

    @Test
    fun unretrieveableAttributes() {
        val settings = settings {
            unretrieveableAttributes { +attributeA }
        }

        settings.unretrievableAttributes!!.shouldNotBeEmpty()
    }

    @Test
    fun attributesToRetrieve() {
        val settings = settings {
            attributesToRetrieve { +attributeA }
        }

        settings.attributesToRetrieve!!.shouldNotBeEmpty()
    }

    @Test
    fun ranking() {
        val settings = settings {
            ranking { +Typo }
        }

        settings.ranking!!.shouldNotBeEmpty()
    }

    @Test
    fun customRanking() {
        val settings = settings {
            customRanking { +(attributeA modify Asc) }
        }

        settings.customRanking!!.shouldNotBeEmpty()
    }

    @Test
    fun replicas() {
        val settings = settings {
            replicas { +indexA }
        }

        settings.replicas!!.shouldNotBeEmpty()
    }

    @Test
    fun attributesToHighlight() {
        val settings = settings {
            attributesToHighlight { +attributeA }
        }

        settings.attributesToHighlight!!.shouldNotBeEmpty()
    }

    @Test
    fun attributesToSnippet() {
        val settings = settings {
            attributesToSnippet { +attributeA }
        }

        settings.attributesToSnippet!!.shouldNotBeEmpty()
    }

    @Test
    fun disableTypoToleranceOnAttributes() {
        val settings = settings {
            disableTypoToleranceOnAttributes { +attributeA }
        }

        settings.disableTypoToleranceOnAttributes!!.shouldNotBeEmpty()
    }

    @Test
    fun disableTypoToleranceOnWords() {
        val settings = settings {
            disableTypoToleranceOnWords { +unknown }
        }

        settings.disableTypoToleranceOnWords!!.shouldNotBeEmpty()
    }

    @Test
    fun queryLanguages() {
        val settings = settings {
            queryLanguages { +English }
        }

        settings.queryLanguages!!.shouldNotBeEmpty()
    }

    @Test
    fun camelCaseAttributes() {
        val settings = settings {
            camelCaseAttributes { +attributeA }
        }

        settings.camelCaseAttributes!!.shouldNotBeEmpty()
    }

    @Test
    fun decompoundedAttributes() {
        val settings = settings {
            decompoundedAttributes { +dutch { +attributeA } }
        }

        settings.decompoundedAttributes!!.shouldNotBeEmpty()
    }
}