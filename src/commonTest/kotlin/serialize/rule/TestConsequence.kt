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
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import loadScratch
import objectIDA
import objectIDB
import serialize.TestSerializer
import shouldEqual
import unknown
import kotlin.test.Test

internal class TestConsequence : TestSerializer<Consequence>(Consequence.serializer(), JsonNoDefaults) {

    private val edits = listOf(Edit(unknown))
    private val query = Query(query = unknown)
    private val queryJson = query.toJsonNoDefaults()
    private val filters = listOf(AutomaticFacetFilters(attributeA, 1, true))
    private val objectIDs = listOf(objectIDA, objectIDB)
    private val promotions = listOf(Promotion(objectIDA, 0))
    private val promotionsSerialized = Json.encodeToJsonElement(ListSerializer(Promotion.serializer()), promotions)
    private val userData = buildJsonObject { put(KeyUserData, unknown) }
    private val filtersJson = Json.encodeToJsonElement(ListSerializer(AutomaticFacetFilters.serializer()), filters)

    override val items = listOf(
        Consequence() to buildJsonObject { },
        Consequence(query = query) to buildJsonObject { put(KeyParams, queryJson) },
        Consequence(edits = edits) to buildJsonObject {
            put(
                KeyParams,
                buildJsonObject {
                    put(
                        KeyQuery,
                        buildJsonObject {
                            put(KeyEdits, Json.encodeToJsonElement(ListSerializer(Edit), edits))
                        }
                    )
                }
            )
        },
        Consequence(
            query = query,
            promote = promotions,
            hide = objectIDs,
            userData = userData,
            filterPromotes = true
        ) to buildJsonObject {
            put(KeyParams, queryJson)
            put(KeyPromote, promotionsSerialized)
            put(
                KeyHide,
                buildJsonArray {
                    add(buildJsonObject { put(KeyObjectID, objectIDA.raw) })
                    add(buildJsonObject { put(KeyObjectID, objectIDB.raw) })
                }
            )
            put(KeyUserData, userData)
            put(KeyFilterPromotes, true)
        },
        Consequence(
            automaticFacetFilters = filters,
            automaticOptionalFacetFilters = filters,
            query = query
        ) to buildJsonObject {
            put(
                KeyParams,
                buildJsonObject {
                    put(KeyQuery, unknown)
                    put(KeyAutomaticFacetFilters, filtersJson)
                    put(KeyAutomaticOptionalFacetFilters, filtersJson)
                }
            )
        }
    )

    @Test
    fun backwardCompatibility() {
        val rule = Json.decodeFromString(Rule.serializer(), loadScratch("rule_v1.json"))

        rule shouldEqual Rule(
            "query_edits".toObjectID(),
            listOf(
                Condition(
                    Anchoring.Is,
                    Pattern.Literal("mobile phone")
                )
            ),
            Consequence(edits = listOf(Edit("mobile"), Edit("phone")))
        )

        promotions[0].position
        promotions[0].objectID
    }
}
