package com.algolia.search.endpoint

import com.algolia.search.filter.FilterFacet
import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacetValue
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Query
import com.algolia.search.transport.RequestOptions


public interface EndpointSearch {

    val indexName: IndexName

    /**
     * Method used for querying an index.
     * The search query only allows for the retrieval of up to 1000 hits.
     * If you need to retrieve more than 1000 hits (e.g. for SEO), you can either leverage
     * the [Browse index][https://www.algolia.com/doc/api-reference/api-methods/browse/] method or increase
     * the [paginationLimitedTo][https://www.algolia.com/doc/api-reference/api-parameters/paginationLimitedTo/] parameter.
     *
     * @param query The [Query] used to search.
     * @param requestOptions [RequestOptions] sent along with the query.
     * @return [ResponseSearch].
     */
    suspend fun search(query: Query? = null, requestOptions: RequestOptions? = null): ResponseSearch

    /**
     * Get all index content without any record limit. Can be used for backups.
     * The browse method is an alternative to the Search index method.
     * If you need to retrieve the full content of your index (for backup, SEO purposes or for running a script on it),
     * you should use this method instead.
     * Results are ranked by attributes and custom ranking.
     * But for performance reasons, there is no ranking based on:
     * - distinct
     * - typo-tolerance
     * - number of matched words
     * - proximity
     * - geo distance
     *
     * You shouldn’t use this method for building a search UI.
     * The analytics API does not collect data from browse method usage.
     * If you need to retrieve more than 1,000 results, you should look into the
     * [paginationLimitedTo][https://www.algolia.com/doc/api-reference/api-parameters/paginationLimitedTo/] parameter.
     *
     * If more records are available, [ResponseSearch.cursorOrNull] will not be null.
     *
     * @param query The [Query] used to search.
     * @param requestOptions [RequestOptions] sent along with the query.
     * @return [ResponseSearch].
     */
    suspend fun browse(query: Query? = null, requestOptions: RequestOptions? = null): ResponseSearch

    /**
     * @param cursor [Cursor] indicating the location to resume browsing from.
     * Must match the value returned by the previous call to [browse] [ResponseSearch.cursorOrNull]
     * @param requestOptions [RequestOptions] sent along with the query.
     * @return [ResponseSearch].
     */
    suspend fun browse(cursor: Cursor, requestOptions: RequestOptions? = null): ResponseSearch

    /**
     * Search for a set of values within a given facet attribute. Can be combined with a query.
     *
     * This method enables you to search through the values of a facet attribute,
     * selecting only a subset of those values that meet a given criteria.
     *
     * Note for a facet attribute to be searchable, it must have been declared in the
     * [attributesForFaceting][https://www.algolia.com/doc/api-reference/api-parameters/attributesForFaceting/]
     * index setting with the `searchable()` modifier.
     *
     * ```
     * val attribute = Attribute("myAttribute")
     *
     * Settings(
     *    attributesForFaceting = AttributeForFaceting.Searchable(attribute)
     * )
     * ```
     *
     * Facet-searching only affects facet values. It does not impact the underlying index search.
     *
     * The results are sorted by decreasing count. This can be adjusted via sortFacetValuesBy.
     *
     * By default, maximum 10 results are returned. This can be adjusted via maxFacetHits.
     *
     * This is often used in combination with a user’s current search (using the searchParameters).
     * By combining facet and query searches, you can control the number of facet values a user sees,
     * thereby focusing the user’s attention on what you consider to be the most relevant facet values.
     *
     * Searching for facet values is a powerful tool that allows you to use as many facet values as you need,
     * without overloading the user with too many values on the screen. Being able to search for values,
     * users can more easily drill down into the results. See more here.
     *
     * @param attribute The [Attribute] to facet on.
     * @param facetQuery The search query used to search the facet attribute.
     * Follows the same rules for an index query: a single character, a partial word, a word, or a phrase.
     * @param query The [Query] used to search.
     * @param requestOptions [RequestOptions] sent along with the query.
     * @return [ResponseSearchForFacetValue].
     */
    suspend fun searchForFacetValues(
        attribute: Attribute,
        facetQuery: String? = null,
        query: Query? = null,
        requestOptions: RequestOptions? = null
    ): ResponseSearchForFacetValue

    /**
     * Perform a [EndpointMultipleIndex.multipleQueries] and aggregate the results into a single [ResponseSearch].
     * The aggregated result will have a correct [com.algolia.search.model.search.Facet.count] for [Attribute] that are
     * marked as disjunctive.
     *
     * @param query The [Query] used to search.
     * @param disjunctiveFacet List of [Attribute] that are marked as disjunctive facets.
     * @param filters The [FilterFacet] to be applied
     * @param requestOptions [RequestOptions] sent along with the query.
     * @return [ResponseSearch]
     */
    suspend fun searchDisjunctiveFacets(
        query: Query,
        disjunctiveFacets: List<Attribute>,
        filters: List<FilterFacet>,
        requestOptions: RequestOptions? = null
    ): ResponseSearch
}