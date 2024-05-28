import com.algolia.search.model.QueryID
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * ObjectData
 *
 * @param queryID Unique identifier for a search query, used to track purchase events with multiple records that originate from different searches.
 * @param price
 * @param quantity Quantity of a product that has been purchased or added to the cart. The total purchase value is the sum of `quantity` multiplied with the `price` for each purchased item.
 * @param discount
 */
@Serializable
public data class ObjectData(
    /**
     * Unique identifier for a search query, used to track purchase events with multiple records that originate from different searches.
     */
    @SerialName(value = Key.QueryID) val queryID: QueryID? = null,

    @SerialName(value = Key.Price) val price: String? = null,

    /**
     * Quantity of a product that has been purchased or added to the cart. The total purchase value is the sum of `quantity` multiplied with the `price` for each purchased item.
     */
    @SerialName(value = Key.Quantity) val quantity: Int? = null,

    @SerialName(value = Key.Discount) val discount: String? = null,
)
