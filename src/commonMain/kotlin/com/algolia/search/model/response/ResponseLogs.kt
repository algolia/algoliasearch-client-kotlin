package com.algolia.search.model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
public data class ResponseLogs(
    @SerialName(KeyLogs) val logs: List<Log>
) {

    @Serializable
    public data class Log(
        /**
         * Date in ISO-8601 format.
         */
        @SerialName(KeyTimestamp) val timestamp: ClientDate,
        /**
         * Rest type of the method.
         */
        @SerialName(KeyMethod) val method: String,
        /**
         * Http response code.
         */
        @SerialName(KeyAnswer_Code) val answerCode: String,
        /**
         * Request body. It’s truncated after 1000 characters.
         */
        @SerialName(KeyQuery_Body) val queryBody: String,
        /**
         * Answer body. It’s truncated after 1000 characters.
         */
        @SerialName(KeyAnswer) val answer: String,
        /**
         * Request URL.
         */
        @SerialName(KeyUrl) val url: String,
        /**
         * Client ip of the call.
         */
        @SerialName(KeyIp) val ip: String,
        /**
         * Request Headers (API Key is obfuscated).
         */
        @SerialName(KeyQuery_Headers) val queryHeaders: String,
        /**
         * SHA1 ID of entry.
         */
        @SerialName(KeySha1) val sha1: String,
        /**
         * Number of API calls.
         */
        @SerialName(KeyNb_Api_Calls) val nbApiCallsOrNull: Long? = null,
        /**
         * Processing time for the query. This does not include network time.
         */
        @SerialName(KeyProcessing_Time_Ms) val processingTimeMS: Long,
        /**
         * Number of hits returned for the query [Query].
         */
        @SerialName(KeyQuery_Nb_Hits) val queryNbHitsOrNull: Int? = null,
        /**
         * [IndexName] of the log.
         */
        @SerialName(KeyIndex) val indexNameOrNull: IndexName? = null,
        @SerialName(Key_Exhaustive_Nb_Hits) val exhaustiveNbHits: Boolean? = null,
        @SerialName(Key_Exhaustive_Faceting) val exhaustiveFaceting: Boolean? = null,
        @SerialName(KeyQuery_Params) val queryParamsOrNull: String? = null
    ) {

        public val indexName: IndexName
            get() = indexNameOrNull!!

        public val queryParams: String
            get() = queryParamsOrNull!!

        public val queryNbHits: Int
            get() = queryNbHitsOrNull!!

        public val nbApiCalls: Long
            get() = nbApiCallsOrNull!!
    }
}