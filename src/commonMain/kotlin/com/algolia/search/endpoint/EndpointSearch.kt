package com.algolia.search.endpoint

import com.algolia.search.model.Attribute
import com.algolia.search.model.IndexName
import com.algolia.search.model.filter.FilterGroup
import com.algolia.search.model.response.ResponseHitWithPosition
import com.algolia.search.model.response.ResponseSearch
import com.algolia.search.model.response.ResponseSearchForFacets
import com.algolia.search.model.search.Cursor
import com.algolia.search.model.search.Query
import com.algolia.search.transport.RequestOptions

/**
 * [Documentation][https://www.algolia.com/doc/api-client/methods/search/?language=kotlin]
 */
public interface EndpointSearch {

    public val indexName: IndexName

    /**
     * Method used for querying an index.
     * The search query only allows for the retrieval of up to 1000 hits.
     * If you need to retrieve more than 1000 hits (e.g. for SEO), you can either leverage
     * the [Browse index][https://www.algolia.com/doc/api-reference/api-methods/browse/] method or increase
     * the [paginationLimitedTo][https://www.algolia.com/doc/api-reference/api-parameters/paginationLimitedTo/] parameter.
     *
     * @param query The [Query] used to search.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun search(query: Query = Query(), requestOptions: RequestOptions? = null): ResponseSearch

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
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun browse(query: Query = Query(), requestOptions: RequestOptions? = null): ResponseSearch

    /**
     * @param cursor [Cursor] indicating the location to resume browsing from.
     * Must match the value returned by the previous call to [browse] [ResponseSearch.cursorOrNull]
     * @param requestOptions Configure request locally with [RequestOptions]
     */
    public suspend fun browse(cursor: Cursor, requestOptions: RequestOptions? = null): ResponseSearch

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
     * @param facetQuery The facetQuery used to search for facets.
     * @param query: The [Query] to filter results.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun searchForFacets(
        attribute: Attribute,
        facetQuery: String? = null,
        query: Query = Query(),
        requestOptions: RequestOptions? = null
    ): ResponseSearchForFacets

    /**
     * Perform a [EndpointMultipleIndex.multipleQueries] and aggregate the results into a single [ResponseSearch].
     * The aggregated result will have a correct [com.algolia.search.model.search.Facet.count] for [Attribute] that are
     * marked as disjunctive, and will populate [ResponseSearch.hierarchicalFacets] if a [FilterGroup.And.Hierarchical]
     * is present in [filterGroups]
     *
     * @param query The [Query] used to search.
     * @param filterGroups List of [FilterGroup] to apply to the query.
     * @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun advancedSearch(
        query: Query = Query(),
        filterGroups: Set<FilterGroup<*>> = setOf(),
        requestOptions: RequestOptions? = null
    ): ResponseSearch

    /**
     *  Search iteratively through the search response [ResponseSearch.hits] field to find the first response hit that
     *  would match against the given [match] function.
     *  If no object has been found within the first result set, the function
     *  will perform a new search operation on the next page of results, if any,
     *  until a matching object is found or the end of results is reached, whichever
     *  happens first.
     *  [paginate] will stop the function at the end of the first page of search results even if no object does
     *  match.
     *
     *  @param match Predicate to match a given [ResponseSearch.Hit]
     *  @param query The [Query] used to search.
     *  @param paginate To prevent the iteration through pages of results.
     *  @param requestOptions Configure request locally with [RequestOptions].
     */
    public suspend fun findObject(
        match: (ResponseSearch.Hit) -> Boolean,
        query: Query = Query(),
        paginate: Boolean = true,
        requestOptions: RequestOptions? = null
    ): ResponseHitWithPosition?
}
