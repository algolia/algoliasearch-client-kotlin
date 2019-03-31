package documentation.methods.settings

import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.CustomRankingCriterium
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import runBlocking
import documentation.TestDocumentation
import kotlin.test.Test


internal class DocSetSettings : TestDocumentation() {

//    suspend fun Index.setSettings(
//        #{settings}: __Settings__,
//        resetToDefault: __List<SettingsKey>__ = listOf(),
//        #{forwardToReplicas}: __Boolean?__ = null,
//        requestOptions: __RequestOptions?__ = null
//    ): RevisionIndex

    @Test
    fun setSettings() {
        runBlocking {
            val name = Attribute("name")
            val address = Attribute("address")
            val followers = Attribute("followers")
            val settings = Settings(
                searchableAttributes = listOf(
                    SearchableAttribute.Default(name),
                    SearchableAttribute.Default(address)
                ),
                customRanking = listOf(
                    CustomRankingCriterium.Desc(followers)
                )
            )

            index.setSettings(settings, forwardToReplicas = true)
        }
    }
}