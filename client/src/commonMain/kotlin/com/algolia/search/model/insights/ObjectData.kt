import com.algolia.search.serialize.internal.Key
import com.algolia.search.serialize.internal.asJsonInput
import com.algolia.search.serialize.internal.asJsonOutput
import com.algolia.search.serialize.internal.jsonPrimitiveOrNull
import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * ObjectData
 *
 * @param queryID Unique identifier for a search query, used to track purchase events with multiple records that originate from different searches.
 * @param price
 * @param quantity Quantity of a product that has been purchased or added to the cart. The total purchase value is the sum of `quantity` multiplied with the `price` for each purchased item.
 * @param discount
 */
@Serializable(ObjectData.Companion::class)
public data class ObjectData(

    /** Unique identifier for a search query, used to track purchase events with multiple records that originate from different searches. */
    @SerialName(value = Key.QueryID) val queryID: String? = null,

    @SerialName(value = Key.Price) val price: String? = null,

    /** Quantity of a product that has been purchased or added to the cart. The total purchase value is the sum of `quantity` multiplied with the `price` for each purchased item.  */
    @SerialName(value = Key.Quantity) val quantity: Int? = null,

    @SerialName(value = Key.Discount) val discount: String? = null,


) {
    @Serializer(ObjectData::class)
    @OptIn(ExperimentalSerializationApi::class)
    public companion object : KSerializer<ObjectData> {

        override fun serialize(encoder: Encoder, value: ObjectData) {
            val json = buildJsonObject {
                value.queryID?.let { put(Key.QueryID, it) }
                value.price?.let { put(Key.Price, it) }
                value.quantity?.let { put(Key.Quantity, it) }
                value.discount?.let { put(Key.Discount, it) }
            }
            encoder.asJsonOutput().encodeJsonElement(json)
        }

        override fun deserialize(decoder: Decoder): ObjectData {
            val json = decoder.asJsonInput().jsonObject
            return ObjectData(
                queryID = json.getValue(Key.QueryID).jsonPrimitiveOrNull?.contentOrNull,
                price = json.getValue(Key.Price).jsonPrimitiveOrNull?.contentOrNull,
                quantity = json.getValue(Key.Quantity).jsonPrimitiveOrNull?.intOrNull,
                discount = json.getValue(Key.Discount).jsonPrimitiveOrNull?.contentOrNull,
            )
        }
    }
}
