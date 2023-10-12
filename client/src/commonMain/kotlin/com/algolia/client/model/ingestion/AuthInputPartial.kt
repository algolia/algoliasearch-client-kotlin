/** Code generated by OpenAPI Generator (https://openapi-generator.tech), manual changes will be lost - read more on https://github.com/algolia/api-clients-automation. DO NOT EDIT. */
package com.algolia.client.model.ingestion

import com.algolia.client.exception.AlgoliaClientException
import com.algolia.client.extensions.internal.*
import kotlinx.serialization.*
import kotlinx.serialization.builtins.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.json.*

/**
 * AuthInputPartial
 */
@Serializable(AuthInputPartialSerializer::class)
public sealed interface AuthInputPartial {

  public companion object {

    /**
     * Authentication input used for token credentials.
     *
     * @param key
     */
    public fun AuthAPIKeyPartial(
      key: String? = null,
    ): AuthAPIKeyPartial = com.algolia.client.model.ingestion.AuthAPIKeyPartial(
      key = key,
    )

    /**
     * AuthAlgoliaPartial
     *
     * @param appID Algolia Application ID.
     * @param apiKey Algolia API Key, with the correct rights to push to an index and change settings.
     */
    public fun AuthAlgoliaPartial(
      appID: String? = null,
      apiKey: String? = null,
    ): AuthAlgoliaPartial = com.algolia.client.model.ingestion.AuthAlgoliaPartial(
      appID = appID,
      apiKey = apiKey,
    )

    /**
     * Authentication input for Basic login with username and password.
     *
     * @param username
     * @param password
     */
    public fun AuthBasicPartial(
      username: String? = null,
      password: String? = null,
    ): AuthBasicPartial = com.algolia.client.model.ingestion.AuthBasicPartial(
      username = username,
      password = password,
    )

    /**
     * Authentication input to connect to a Google service (e.g. BigQuery).
     *
     * @param clientEmail Email address of the Service Account.
     * @param privateKey Private key of the Service Account.
     */
    public fun AuthGoogleServiceAccountPartial(
      clientEmail: String? = null,
      privateKey: String? = null,
    ): AuthGoogleServiceAccountPartial = com.algolia.client.model.ingestion.AuthGoogleServiceAccountPartial(
      clientEmail = clientEmail,
      privateKey = privateKey,
    )

    /**
     * Authentication input for OAuth login.
     *
     * @param url The OAuth endpoint URL.
     * @param clientId The clientID.
     * @param clientSecret The secret.
     */
    public fun AuthOAuthPartial(
      url: String? = null,
      clientId: String? = null,
      clientSecret: String? = null,
    ): AuthOAuthPartial = com.algolia.client.model.ingestion.AuthOAuthPartial(
      url = url,
      clientId = clientId,
      clientSecret = clientSecret,
    )
  }
}

internal class AuthInputPartialSerializer : KSerializer<AuthInputPartial> {

  override val descriptor: SerialDescriptor = buildClassSerialDescriptor("AuthInputPartial")

  override fun serialize(encoder: Encoder, value: AuthInputPartial) {
    when (value) {
      is AuthAPIKeyPartial -> AuthAPIKeyPartial.serializer().serialize(encoder, value)
      is AuthAlgoliaPartial -> AuthAlgoliaPartial.serializer().serialize(encoder, value)
      is AuthBasicPartial -> AuthBasicPartial.serializer().serialize(encoder, value)
      is AuthGoogleServiceAccountPartial -> AuthGoogleServiceAccountPartial.serializer().serialize(encoder, value)
      is AuthOAuthPartial -> AuthOAuthPartial.serializer().serialize(encoder, value)
    }
  }

  override fun deserialize(decoder: Decoder): AuthInputPartial {
    val codec = decoder.asJsonDecoder()
    val tree = codec.decodeJsonElement()

    // deserialize AuthAPIKeyPartial
    if (tree is JsonObject) {
      try {
        return codec.json.decodeFromJsonElement<AuthAPIKeyPartial>(tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize AuthAPIKeyPartial (error: ${e.message})")
      }
    }

    // deserialize AuthAlgoliaPartial
    if (tree is JsonObject) {
      try {
        return codec.json.decodeFromJsonElement<AuthAlgoliaPartial>(tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize AuthAlgoliaPartial (error: ${e.message})")
      }
    }

    // deserialize AuthBasicPartial
    if (tree is JsonObject) {
      try {
        return codec.json.decodeFromJsonElement<AuthBasicPartial>(tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize AuthBasicPartial (error: ${e.message})")
      }
    }

    // deserialize AuthGoogleServiceAccountPartial
    if (tree is JsonObject) {
      try {
        return codec.json.decodeFromJsonElement<AuthGoogleServiceAccountPartial>(tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize AuthGoogleServiceAccountPartial (error: ${e.message})")
      }
    }

    // deserialize AuthOAuthPartial
    if (tree is JsonObject) {
      try {
        return codec.json.decodeFromJsonElement<AuthOAuthPartial>(tree)
      } catch (e: Exception) {
        // deserialization failed, continue
        println("Failed to deserialize AuthOAuthPartial (error: ${e.message})")
      }
    }

    throw AlgoliaClientException("Failed to deserialize json element: $tree")
  }
}