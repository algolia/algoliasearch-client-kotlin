package serialize.rule

import attributeA
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.rule.*
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.list
import loadScratch
import objectIDA
import objectIDB
import serialize.TestSerializer
import shouldEqual
import unknown
import kotlin.test.Test


internal class TestConsequence : TestSerializer<Consequence>(Consequence.serializer(), Json.noDefaults) {

    private val edits = listOf(Edit(unknown))
    private val query = Query(query = unknown)
    private val queryJson = query.toJsonNoDefaults()
    private val filters = listOf(AutomaticFacetFilters(attributeA, 1, true))
    private val objectIDs = listOf(objectIDA, objectIDB)
    private val promotions = listOf(Promotion(objectIDA, 0))
    private val promotionsSerialized = Json.plain.toJson(Promotion.serializer().list, promotions)
    private val userData = json { KeyUserData to unknown }
    private val filtersJson = Json.plain.toJson(AutomaticFacetFilters.serializer().list, filters)

    override val items = listOf(
        Consequence() to json { },
        Consequence(query = query) to json { KeyParams to queryJson },
        Consequence(edits = edits) to json {
            KeyParams to json {
                KeyQuery to json {
                    KeyEdits to Json.plain.toJson(Edit.list, edits)
                }
            }
        },
        Consequence(query = query, promote = promotions, hide = objectIDs, userData = userData) to json {
            KeyParams to queryJson
            KeyPromote to promotionsSerialized
            KeyHide to jsonArray {
                +json { KeyObjectID to objectIDA.raw }
                +json { KeyObjectID to objectIDB.raw }
            }
            KeyUserData to userData
        },
        Consequence(automaticFacetFilters = filters, automaticOptionalFacetFilters = filters, query = query) to json {
            KeyParams to json {
                KeyQuery to unknown
                KeyAutomaticFacetFilters to filtersJson
                KeyAutomaticOptionalFacetFilters to filtersJson
            }
        }
    )

    @Test
    fun backwardCompatibility() {
        val rule = Json.parse(Rule.serializer(), loadScratch("rule_v1.json"))

        rule shouldEqual Rule(
            "query_edits".toObjectID(),
            Condition(
                Pattern.Literal("mobile phone"),
                Anchoring.Is
            ),
            Consequence(
                edits = listOf(Edit("mobile"), Edit("phone"))
            )
        )
    }
}