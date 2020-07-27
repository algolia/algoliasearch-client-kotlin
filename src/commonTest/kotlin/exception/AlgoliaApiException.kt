package exception

import kotlinx.serialization.Serializable

/**
 * Exception thrown in case of API failure such as 4XX, 5XX error.
 **/
@Serializable
internal class AlgoliaApiException(
    val status: Int?,
    override val message: String?
) : RuntimeException(message)
