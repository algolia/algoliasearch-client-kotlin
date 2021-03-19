package suite

import kotlin.test.Test

internal class TestSecuredAPIKey {

    private val test = TestSecuredAPIKeyTools()

    @Test
    fun expiredKey() {
        test.expiredKey()
    }

    @Test
    fun validKey() {
        test.validKey()
    }

    @Test
    fun remainingValidityParameter() {
        test.remainingValidityParameter()
    }
}
