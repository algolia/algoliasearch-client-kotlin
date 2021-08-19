package com.algolia.search.model.recommend.internal

import com.algolia.search.model.recommend.RecommendationModel
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal class RecommendationModelSerializer : KSerializer<RecommendationModel> {

    override val descriptor: SerialDescriptor = String.serializer().descriptor

    override fun deserialize(decoder: Decoder): RecommendationModel {
        return when (val value = decoder.decodeString()) {
            RecommendationModel.RelatedProducts.model -> RecommendationModel.RelatedProducts
            RecommendationModel.BoughtTogether.model -> RecommendationModel.BoughtTogether
            else -> RecommendationModel(value)
        }
    }

    override fun serialize(encoder: Encoder, value: RecommendationModel) {
        encoder.encodeString(value.model)
    }
}
