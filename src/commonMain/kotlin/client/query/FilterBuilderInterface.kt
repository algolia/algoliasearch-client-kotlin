package client.query

import client.data.Attribute


interface FilterBuilderInterface<T : Filter> {

    /**
     * Add a [filter] to this [Group]. If the [Group] does not exists, it will be created.
     */
    operator fun Group.plusAssign(filter: T)

    /**
     * Add the [filters] to this [Group]. If the [Group] does not exists, it will be created.
     */
    operator fun Group.plusAssign(filters: Collection<T>)

    /**
     * Remove the [filter] from this [Group]. If the [Group] is empty, it will be deleted.
     */
    operator fun Group.minusAssign(filter: T)

    /**
     * Remove the [filters] from this [Group]. If the [Group] is empty, it will be deleted.
     */
    operator fun Group.minusAssign(filters: Collection<T>)

    /**
     * Add a [filter] to this [Group]. If the [Group] does not exists, it will be created.
     */
    fun Group.add(filter: T)

    /**
     * Add the [filters] to a [Group]. If the [Group] does not exists, it will be created.
     */
    fun Group.addAll(filters: Collection<T>)

    /**
     * Remove the [filter] from a [Group]. If the [Group] is empty, it will be deleted.
     */
    fun Group.remove(filter: T)

    /**
     * Remove the [filters] from this [Group]. If the [Group] is empty, it will be deleted.
     */
    fun Group.removeAll(filters: Collection<T>)

    /**
     * Check whether this [Group] contains the [filter].
     */
    fun Group.contains(filter: T): Boolean

    /**
     * Remove all [Filter] from this [Group]. If the [attribute] is specified, only [Filter] with matching
     * [Filter.attribute] will be removed. If the [Group] is empty, it will be deleted.
     */
    fun Group.clear(attribute: Attribute? = null)

    /**
     * Replace in this [Group] all [Filter.attribute] matching [attribute] with its [replacement].
     */
    fun Group.replaceAttribute(attribute: Attribute, replacement: Attribute)

    /**
     * Get all [Filter] in this [Group]. If an [attribute] is specified,
     * only [Filter] with matching [Filter.attribute] will be returned.
     */
    fun Group.get(attribute: Attribute? = null): Set<T>

    /**
     * Move a [filter] from this [Group] to its [destination]. If this [Group] is empty, it will be deleted.
     * If the [destination] does not exists, it will be created.
     * @return True if the filter was found in this [Group] and successfully moved to its [destination].
     */
    fun Group.move(destination: Group, filter: T): Boolean

    /**
     * Get all [Filter] contained in every [Group] created with this [FilterBuilderInterface].
     * If an [attribute] is specified, only [Filter] with matching [Filter.attribute] will be returned.
     */
    fun get(attribute: Attribute? = null): Set<T>

    /**
     * Check whether any [Group] contains the [filter].
     */
    fun contains(filter: T): Boolean

    /**
     * Check whether any [Filter] is present.
     */
    fun isEmpty(): Boolean

    /**
     * Clear every [Filter] from every [Group], and remove all [Group].
     */
    fun clear()
}