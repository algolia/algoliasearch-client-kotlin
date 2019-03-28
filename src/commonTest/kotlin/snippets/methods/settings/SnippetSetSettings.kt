package snippets.methods.settings

import com.algolia.search.model.Attribute
import com.algolia.search.model.settings.CustomRankingCriteria
import com.algolia.search.model.settings.SearchableAttribute
import com.algolia.search.model.settings.Settings
import runBlocking
import snippets.TestSnippets
import kotlin.test.Test


internal class SnippetSetSettings : TestSnippets() {

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
                    CustomRankingCriteria.Desc(followers)
                )
            )

            index.setSettings(settings, forwardToReplicas = true)
        }
    }
}