package com.algolia.search.model.response

import com.algolia.search.model.ClientDate
import com.algolia.search.model.IndexName
import com.algolia.search.model.QueryID
import com.algolia.search.model.insights.UserToken
import com.algolia.search.model.search.Query
import com.algolia.search.serialize.internal.Key
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ResponseLogs(
    @SerialName(Key.Logs) val logs: List<Log>
) {

    @Serializable
    public data class Log(
        /**
         * Date in ISO-8601 format.
         */
        @SerialName(Key.Timestamp) val timestamp: ClientDate,
        /**
         * Rest type of the method.
         */
        @SerialName(Key.Method) val method: String,
        /**
         * Http response code.
         */
        @SerialName(Key.Answer_Code) val answerCode: String,
        /**
         * Request body. It’s truncated after 1000 characters.
         */
        @SerialName(Key.Query_Body) val queryBody: String,
        /**
         * Answer body. It’s truncated after 1000 characters.
         */
        @SerialName(Key.Answer) val answer: String,
        /**
         * Request URL.
         */
        @SerialName(Key.Url) val url: String,
        /**
         * Client ip of the call.
         */
        @SerialName(Key.Ip) val ip: String,
        /**
         * Request Headers (API Key is obfuscated).
         */
        @SerialName(Key.Query_Headers) val queryHeaders: String,
        /**
         * SHA1 ID of entry.
         */
        @SerialName(Key.Sha1) val sha1: String,
        /**
         * Number of API calls.
         */
        @SerialName(Key.Nb_Api_Calls) val nbApiCallsOrNull: Long? = null,
        /**
         * Processing time for the query. This does not include network time.
         */
        @SerialName(Key.Processing_Time_Ms) val processingTimeMS: Long,
        /**
         * Number of hits returned for the query [Query].
         */
        @SerialName(Key.Query_Nb_Hits) val queryNbHitsOrNull: Int? = null,
        /**
         * [IndexName] of the log.
         */
        @SerialName(Key.Index) val indexNameOrNull: IndexName? = null,
        @SerialName(Key._Exhaustive_Nb_Hits) val exhaustiveNbHits: Boolean? = null,
        @SerialName(Key._Exhaustive_Faceting) val exhaustiveFaceting: Boolean? = null,
        @SerialName(Key.Query_Params) val queryParamsOrNull: String? = null,
        /**
         * Contains an object for each performed query with the indexName, queryID, offset, and userToken.
         */
        @SerialName(Key.Inner_Queries) val innerQueries: List<InnerQuery>? = null
    ) {

        public val indexName: IndexName
            get() = indexNameOrNull!!

        public val queryParams: String
            get() = queryParamsOrNull!!

        public val queryNbHits: Int
            get() = queryNbHitsOrNull!!

        public val nbApiCalls: Long
            get() = nbApiCallsOrNull!!

        @Serializable
        public data class InnerQuery(
            @SerialName(Key.Index_Name) val indexName: IndexName,
            @SerialName(Key.Query_ID) val queryID: QueryID? = null,
            @SerialName(Key.Offset) val offset: Int? = null,
            @SerialName(Key.User_Token) val userToken: UserToken? = null
        )
    }
}
