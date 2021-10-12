package com.algolia.search.transport

import com.algolia.search.configuration.CallType
import io.ktor.http.HttpMethod
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo

public interface CustomRequest {

    /**
     * Perform a custom request.
     *
     * @param httpMethod represents an HTTP method
     * @param callType indicate whether the HTTP call performed is of type Read (GET) or Write (POST, PUT..)
     * @param path request path
     * @param requestOptions configure request locally
     * @param body request body
     * @param responseType return value type
     *
     * @return request's response; type [T] must be serializable
     */
    public suspend fun <T : Any> customRequest(
        httpMethod: HttpMethod,
        callType: CallType,
        path: String,
        responseType: TypeInfo,
        body: String? = null,
        requestOptions: RequestOptions? = null,
    ): T
}

/**
 * Perform a custom request.
 *
 * @param httpMethod represents an HTTP method
 * @param callType indicate whether the HTTP call performed is of type Read (GET) or Write (POST, PUT..)
 * @param path request path
 * @param requestOptions configure request locally
 * @param body request body
 *
 * @return request's response; type [T] must be serializable
 */
public suspend inline fun <reified T : Any> CustomRequest.customRequest(
    httpMethod: HttpMethod,
    callType: CallType,
    path: String,
    body: String? = null,
    requestOptions: RequestOptions? = null,
): T = customRequest(
    httpMethod = httpMethod,
    callType = callType,
    path = path,
    requestOptions = requestOptions,
    body = body,
    responseType = typeInfo<T>()
)
