package com.algolia.client.dsl.live.testkit

import com.algolia.client.dsl.cases.json
import com.algolia.client.model.search.Condition
import com.algolia.client.model.search.Consequence
import com.algolia.client.model.search.IndexSettings
import com.algolia.client.model.search.Rule
import kotlinx.serialization.json.JsonObject

internal val FIXTURE_SETTINGS: IndexSettings =
  IndexSettings(
    attributesForFaceting =
      listOf(
        "color",
        "category",
        "label",
        "count",
        "provider",
        "genre",
        "isFeatured",
        "isPinned",
        "priority",
        "suggestionType",
        "filterOnly(locale)",
        "filterOnly(entityId)",
        "filterOnly(batchId)",
      ),
    searchableAttributes = listOf("title", "alternateTitles", "tags", "description"),
  )

internal val FIXTURE_RECORDS: List<JsonObject> =
  listOf(
    json(
      """{"objectID": "1", "color": "red", "category": "shirt", "label": "-Movie", "count": 10, "_tags": ["featured"],
   "locale": "en-US", "entityId": "e1", "batchId": "b1", "isFeatured": true, "genre": "comedy", "provider": "NBC",
   "isPinned": false, "priority": 1, "suggestionType": "show", "title": "Office Space",
   "alternateTitles": ["Workplace"], "tags": ["classic"], "description": "cult comedy"}"""
    ),
    json(
      """{"objectID": "2", "color": "blue", "category": "shirt", "label": "Movie", "count": -12, "_tags": ["-x"],
   "locale": "fr-FR", "entityId": "e2", "batchId": "b1", "isFeatured": false, "genre": "drama", "provider": "CBS",
   "isPinned": true, "priority": 2, "suggestionType": "movie", "title": "Le Bureau",
   "alternateTitles": ["The Office"], "tags": ["remake"], "description": "french drama"}"""
    ),
    json(
      """{"objectID": "3", "color": "red", "category": "pants", "count": 5,
   "locale": "en-GB", "entityId": "e3", "batchId": "b2", "isFeatured": false, "genre": "comedy", "provider": "BBC",
   "isPinned": false, "priority": 2, "suggestionType": "show", "title": "The Office",
   "alternateTitles": [], "tags": ["office"], "description": "british comedy"}"""
    ),
    json(
      """{"objectID": "4", "color": "green", "category": "pants", "_tags": ["x"],
   "locale": "en-US", "entityId": "e4", "batchId": "b2", "isFeatured": true, "genre": "documentary", "provider": "PBS",
   "isPinned": false, "priority": 3, "suggestionType": "episode", "title": "Green Planet",
   "alternateTitles": ["Planet"], "tags": ["nature"], "description": "office plants"}"""
    ),
    json(
      """{"objectID": "5", "color": "navy blue", "category": "shirt", "provider": "NBC: Universal \"East\"",
   "locale": "fr-FR", "entityId": "e5", "batchId": "b3", "isFeatured": false, "genre": "comedy",
   "isPinned": true, "priority": 1, "suggestionType": "show", "title": "Navy",
   "alternateTitles": [], "tags": [], "description": "naval comedy"}"""
    ),
  )

internal val FIXTURE_RULE: Rule =
  Rule(
    objectID = "ctx-desktop",
    conditions = listOf(Condition(context = "desktop")),
    consequence = Consequence(userData = json("""{"ctx":"desktop"}""")),
  )

internal class LiveFixture(
  val settings: IndexSettings,
  val records: List<JsonObject>,
  val rule: Rule?,
)

internal val MAIN_FIXTURE: LiveFixture =
  LiveFixture(FIXTURE_SETTINGS, FIXTURE_RECORDS, FIXTURE_RULE)

internal val ESCAPING_FIXTURE: LiveFixture =
  LiveFixture(
    settings =
      IndexSettings(
        attributesForFaceting = listOf("v", "my:attr", "my attr", "my num", "a:b", "a(b)")
      ),
    records =
      listOf(
        json("""{"objectID": "1", "v": "Books(Kids)", "_tags": ["a(b)"]}"""),
        json("""{"objectID": "2", "v": "a:b", "_tags": ["x:y"]}"""),
        json("""{"objectID": "3", "v": "a<b"}"""),
        json("""{"objectID": "4", "v": "C:\\ dir\\"}"""),
        json("""{"objectID": "5", "v": "back\\slash"}"""),
        json("""{"objectID": "6", "v": "a=b"}"""),
        json("""{"objectID": "7", "v": "a!b"}"""),
        json("""{"objectID": "8", "v": "a>b"}"""),
        json("""{"objectID": "9", "v": "trail\\"}"""),
        json("""{"objectID": "10", "v": "a.b", "my:attr": "z"}"""),
        json("""{"objectID": "11", "v": "q\"uote"}"""),
        json("""{"objectID": "12", "v": "John Doe"}"""),
        json("""{"objectID": "13", "v": "foo AND bar"}"""),
        json("""{"objectID": "14", "v": "foo OR bar"}"""),
        json("""{"objectID": "15", "v": "NOT bar"}"""),
        json("""{"objectID": "16", "v": "TO"}"""),
        json("""{"objectID": "17", "v": "café"}"""),
        json("""{"objectID": "18", "v": "a.b-c_1"}"""),
        json("""{"objectID": "19", "_tags": ["45\"-50\" tv's"]}"""),
        json("""{"objectID": "20", "my attr": "red", "my num": 15, "a:b": 2, "a(b)": 1}"""),
      ),
    rule = null,
  )
