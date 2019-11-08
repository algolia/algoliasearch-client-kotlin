package suite

import clientAdmin1
import runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class TestSecuredAPIKey {

    private val test = TestSecuredAPIKeyTools()

    @BeforeTest
    fun clean() {
        runBlocking {
            cleanIndex(clientAdmin1, test.suffix)
        }
    }

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