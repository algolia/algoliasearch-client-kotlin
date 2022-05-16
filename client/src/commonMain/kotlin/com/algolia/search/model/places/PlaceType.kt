package com.algolia.search.model.places

import com.algolia.search.model.internal.Raw
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(PlaceType.Companion::class)
public sealed class PlaceType(override val raw: String) : Raw<String> {

    public object City : PlaceType(Key.City)

    public object Country : PlaceType(Key.Country)

    public object Address : PlaceType(Key.Address)

    public object BusStop : PlaceType(Key.BusStop)

    public object TrainStation : PlaceType(Key.TrainStation)

    public object TownHall : PlaceType(Key.Townhall)

    public object Airport : PlaceType(Key.Airport)

    public data class Other(override val raw: String) : PlaceType(raw)

    public companion object : KSerializer<PlaceType> {

        private val serializer = String.serializer()

        override val descriptor: SerialDescriptor = serializer.descriptor

        override fun serialize(encoder: Encoder, value: PlaceType) {
            serializer.serialize(encoder, value.raw)
        }

        override fun deserialize(decoder: Decoder): PlaceType {
            return when (val string = serializer.deserialize(decoder)) {
                Key.City -> City
                Key.Country -> Country
                Key.Address -> Address
                Key.BusStop -> BusStop
                Key.TrainStation -> TrainStation
                Key.Townhall -> TownHall
                Key.Airport -> Airport
                else -> Other(string)
            }
        }
    }
}
