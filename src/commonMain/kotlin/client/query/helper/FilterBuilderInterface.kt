package client.query.helper


interface FilterBuilderInterface<T : Filter> {

    operator fun Group.plusAssign(filter: T)

    operator fun Group.plusAssign(filters: Collection<T>)

    operator fun Group.minusAssign(filter: T)

    operator fun Group.minusAssign(filters: Collection<T>)

    fun Group.add(filter: T)

    fun Group.addAll(filters: Collection<T>)

    fun Group.remove(filter: T)

    fun Group.removeAll(filters: Collection<T>)

    fun Group.contains(filter: T): Boolean

    fun Group.clear(attribute: Attribute? = null)

    fun Group.replaceAttribute(attribute: Attribute, replacement: Attribute)

    fun Group.get(attribute: Attribute? = null): Set<T>

    fun Group.move(destination: Group, filter: T): Boolean

    fun get(attribute: Attribute? = null): Set<T>

    fun contains(filter: T): Boolean

    fun isEmpty(): Boolean

    fun clear()
}