package com.algolia.search.model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient


@Serializable
public data class ResponseLogs(
    @SerialName(KeyLogs) val logs: List<Log>
) {

    @Serializable
    public data class Log(
        @SerialName(KeyTimestamp) val timestamp: ClientDate,
        @SerialName(KeyMethod) val method: String,
        @SerialName(KeyAnswer_Code) val answerCode: String,
        @SerialName(KeyQuery_Body) val queryBody: String,
        @SerialName(KeyAnswer) val answer: String,
        @SerialName(KeyUrl) val url: String,
        @SerialName(KeyIp) val ip: String,
        @SerialName(KeyQuery_Headers) val queryHeaders: String,
        @SerialName(KeySha1) val sha1: String,
        @SerialName(KeyProcessing_Time_Ms) val processingTimeMS: Long,
        @SerialName(KeyNb_Api_Calls) val nbApiCallsOrNull: Long? = null,
        @SerialName(KeyIndex) val indexNameOrNull: IndexName? = null,
        @SerialName(KeyQuery_Params) val queryParamsOrNull: String? = null,
        @SerialName(KeyQuery_Nb_Hits) val queryNbHitsOrNull: Int? = null,
        @SerialName(Key_Exhaustive_Nb_Hits) val exhaustiveNbHits: Boolean? = null
    ) {

        @Transient
        public val indexName: IndexName
            get() = indexNameOrNull!!

        @Transient
        public val queryParams: String
            get() = queryParamsOrNull!!

        @Transient
        public val queryNbHits: Int
            get() = queryNbHitsOrNull!!

        @Transient
        public val nbApiCalls: Long
            get() = nbApiCallsOrNull!!
    }
}