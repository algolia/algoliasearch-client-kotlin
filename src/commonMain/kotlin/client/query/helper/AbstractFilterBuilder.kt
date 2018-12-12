package client.query.helper


abstract class AbstractFilterBuilder<T : Filter> {

    internal val groups: GroupMap<T> = mutableMapOf()

    operator fun Group.plusAssign(filter: T) {
        groups.add(this, filter)
    }

    operator fun Group.plusAssign(filters: Collection<T>) {
        filters.forEach { groups.add(this, it) }
    }

    operator fun Group.minusAssign(filter: T) {
        groups.remove(this, filter)
    }

    operator fun Group.minusAssign(filters: Collection<T>) {
        filters.forEach { groups.remove(this, it) }
    }

    fun Group.contains(filter: Filter): Boolean {
        return groups.contains(this, filter)
    }

    fun Group.clear(attribute: Attribute? = null) {
        groups.clear(this, attribute)
    }

    abstract fun Group.replaceAttribute(attribute: Attribute, replacement: Attribute)

    fun Group.get(attribute: Attribute? = null): Set<T> {
        return groups.get(this, attribute)
    }

    fun clear() {
        groups.clear()
    }
}