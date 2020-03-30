package serialize.rule

import attributeA
import com.algolia.search.helper.toObjectID
import com.algolia.search.model.rule.Anchoring
import com.algolia.search.model.rule.AutomaticFacetFilters
import com.algolia.search.model.rule.Condition
import com.algolia.search.model.rule.Consequence
import com.algolia.search.model.rule.Edit
import com.algolia.search.model.rule.Pattern
import com.algolia.search.model.rule.Promotion
import com.algolia.search.model.rule.Rule
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.Json
import com.algolia.search.serialize.JsonNoDefaults
import com.algolia.search.serialize.KeyAutomaticFacetFilters
import com.algolia.search.serialize.KeyAutomaticOptionalFacetFilters
import com.algolia.search.serialize.KeyEdits
import com.algolia.search.serialize.KeyFilterPromotes
import com.algolia.search.serialize.KeyHide
import com.algolia.search.serialize.KeyObjectID
import com.algolia.search.serialize.KeyParams
import com.algolia.search.serialize.KeyPromote
import com.algolia.search.serialize.KeyQuery
import com.algolia.search.serialize.KeyUserData
import com.algolia.search.serialize.toJsonNoDefaults
import kotlin.test.Test
import kotlinx.serialization.json.json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.list
import loadScratch
import objectIDA
import objectIDB
import serialize.TestSerializer
import shouldEqual
import unknown

internal class TestConsequence : TestSerializer<Consequence>(Consequence.serializer(), JsonNoDefaults) {

    private val edits = listOf(Edit(unknown))
    private val query = Query(query = unknown)
    private val queryJson = query.toJsonNoDefaults()
    private val filters = listOf(AutomaticFacetFilters(attributeA, 1, true))
    private val objectIDs = listOf(objectIDA, objectIDB)
    private val promotions = listOf(Promotion(objectIDA, 0))
    private val promotionsSerialized = Json.toJson(Promotion.serializer().list, promotions)
    private val userData = json { KeyUserData to unknown }
    private val filtersJson = Json.toJson(AutomaticFacetFilters.serializer().list, filters)

    override val items = listOf(
        Consequence() to json { },
        Consequence(query = query) to json { KeyParams to queryJson },
        Consequence(edits = edits) to json {
            KeyParams to json {
                KeyQuery to json {
                    KeyEdits to Json.toJson(Edit.list, edits)
                }
            }
        },
        Consequence(
            query = query,
            promote = promotions,
            hide = objectIDs,
            userData = userData,
            filterPromotes = true
        ) to json {
            KeyParams to queryJson
            KeyPromote to promotionsSerialized
            KeyHide to jsonArray {
                +json { KeyObjectID to objectIDA.raw }
                +json { KeyObjectID to objectIDB.raw }
            }
            KeyUserData to userData
            KeyFilterPromotes to true
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
                Anchoring.Is,
                Pattern.Literal("mobile phone")
            ),
            Consequence(
                edits = listOf(Edit("mobile"), Edit("phone"))
            )
        )
    }
}
