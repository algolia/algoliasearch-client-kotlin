package serialize

import attributeA
import com.algolia.search.saas.data.ObjectID
import com.algolia.search.saas.data.Query
import com.algolia.search.saas.data.QueryRule
import com.algolia.search.saas.data.QueryRule.Consequence
import com.algolia.search.saas.serialize.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.json
import kotlinx.serialization.list
import objectIDA
import objectIDB
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import unknown


@RunWith(JUnit4::class)
internal class TestQueryRuleConsequence : TestSerializer<QueryRule.Consequence>(QueryRule.Consequence) {

    private val edits = listOf(QueryRule.Edit("delete"))
    private val editsSerialized = Json.plain.toJson(edits, QueryRule.Edit.list)
    private val query = Query(query = unknown)
    private val filters = listOf(Consequence.AutomaticFacetFilters(attributeA, 1, true))
    private val filtersSerialized = Json.plain.toJson(filters, Consequence.AutomaticFacetFilters.serializer().list)
    private val objectIDs = listOf(objectIDA, objectIDB)
    private val objectIDsSerialized = Json.plain.toJson(objectIDs, ObjectID.list)
    private val promotions = listOf(Consequence.Promotion(objectIDA, 0))
    private val promotionsSerialized = Json.plain.toJson(promotions, Consequence.Promotion.serializer().list)

    override val items = listOf(
        Consequence() to json { KeyParams to json { } },
        Consequence(query) to json { KeyParams to json { KeyQuery to unknown } },
        Consequence(edits = edits) to json {
            KeyParams to json { KeyEdit to editsSerialized }
        },
        Consequence(
            params = query,
            hide = listOf(objectIDA, objectIDB),
            edits = edits,
            promote = promotions,
            automaticOptionalFacetFilters = filters,
            automaticFacetFilters = filters
        ) to json {
            KeyParams to json {
                KeyQuery to unknown
                KeyEdit to editsSerialized
                KeyAutomaticFacetFilters to filtersSerialized
                KeyAutomaticOptionalFacetFilters to filtersSerialized
            }
            KeyHide to objectIDsSerialized
            KeyPromote to promotionsSerialized
        }
    )
}