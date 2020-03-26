package documentation.parameters.personalization

import com.algolia.search.dsl.query
import com.algolia.search.model.insights.UserToken
import documentation.index
import kotlin.test.Ignore
import kotlin.test.Test
import runBlocking

@Ignore
internal class DocUserToken {

//    userToken: UserToken = UserToken("YourCustomUserId")

    @Test
    fun snippet1() {
        runBlocking {
            val query = query("query") {
                userToken = UserToken("123456")
            }

            index.search(query)
        }
    }

    @Test
    fun snippet2() {
        runBlocking {
            val query = query("query") {
                enablePersonalization = true
                userToken = UserToken("123456")
            }

            index.search(query)
        }
    }
}
