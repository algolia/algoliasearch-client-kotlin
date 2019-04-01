package dsl

import attributeA
import com.algolia.search.dsl.*
import indexA
import shouldNotBeNull
import unknown
import kotlin.test.Test


internal class TestDSLSettings {

    @Test
    fun searchableAttributes() {
        val settings = settings {
            searchableAttributes { +attributeA }
        }

        settings.searchableAttributes.shouldNotBeNull()
    }

    @Test
    fun attributesForFaceting() {
        val settings = settings {
            attributesForFaceting { +attributeA }
        }

        settings.attributesForFaceting.shouldNotBeNull()
    }

    @Test
    fun unretrieveableAttributes() {
        val settings = settings {
            unretrieveableAttributes { +attributeA }
        }

        settings.unretrievableAttributes.shouldNotBeNull()
    }

    @Test
    fun attributesToRetrieve() {
        val settings = settings {
            attributesToRetrieve { +attributeA }
        }

        settings.attributesToRetrieve.shouldNotBeNull()
    }

    @Test
    fun ranking() {
        val settings = settings {
            ranking { +Typo }
        }

        settings.ranking.shouldNotBeNull()
    }

    @Test
    fun customRanking() {
        val settings = settings {
            customRanking { +(attributeA modify Asc) }
        }

        settings.customRanking.shouldNotBeNull()
    }

    @Test
    fun replicas() {
        val settings = settings {
            replicas { +indexA }
        }

        settings.replicas.shouldNotBeNull()
    }

    @Test
    fun attributesToHighlight() {
        val settings = settings {
            attributesToHighlight { +attributeA }
        }

        settings.attributesToHighlight.shouldNotBeNull()
    }

    @Test
    fun attributesToSnippet() {
        val settings = settings {
            attributesToSnippet { +attributeA }
        }

        settings.attributesToSnippet.shouldNotBeNull()
    }

    @Test
    fun disableTypoToleranceOnAttributes() {
        val settings = settings {
            disableTypoToleranceOnAttributes { +attributeA }
        }

        settings.disableTypoToleranceOnAttributes.shouldNotBeNull()
    }

    @Test
    fun disableTypoToleranceOnWords() {
        val settings = settings {
            disableTypoToleranceOnWords { +unknown }
        }

        settings.disableTypoToleranceOnWords.shouldNotBeNull()
    }

    @Test
    fun queryLanguages() {
        val settings = settings {
            queryLanguages { +English }
        }

        settings.queryLanguages.shouldNotBeNull()
    }

    @Test
    fun camelCaseAttributes() {
        val settings = settings {
            camelCaseAttributes { +attributeA }
        }

        settings.camelCaseAttributes.shouldNotBeNull()
    }

    @Test
    fun decompoundedAttributes() {
        val settings = settings {
            decompoundedAttributes { +dutch { +attributeA } }
        }

        settings.decompoundedAttributes.shouldNotBeNull()
    }

    @Test
    fun optionalWords() {
        val settings = settings {
            optionalWords { +unknown }
        }

        settings.optionalWords.shouldNotBeNull()
    }

    @Test
    fun disablePrefixOnAttributes() {
        val settings = settings {
            disablePrefixOnAttributes { +attributeA }
        }

        settings.disablePrefixOnAttributes.shouldNotBeNull()
    }

    @Test
    fun disableExactOnAttributes() {
        val settings = settings {
            disableExactOnAttributes { +attributeA }
        }

        settings.disableExactOnAttributes.shouldNotBeNull()
    }

    @Test
    fun alternativesAsExact() {
        val settings = settings {
            alternativesAsExact { +MultiWordsSynonym }
        }

        settings.alternativesAsExact.shouldNotBeNull()
    }

    @Test
    fun numericAttributesForFiltering() {
        val settings = settings {
            numericAttributesForFiltering { +attributeA }
        }

        settings.numericAttributesForFiltering.shouldNotBeNull()
    }

    @Test
    fun responseFields() {
        val settings = settings {
            responseFields { +All }
        }

        settings.responseFields.shouldNotBeNull()
    }
}