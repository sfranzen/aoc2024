import java.util.concurrent.ConcurrentHashMap

open class DirectedGraph<T : Any>(vertices: Collection<T>) {
    private val map = ConcurrentHashMap<T, MutableSet<T>>(vertices.size).apply { putAll(vertices.map { it to mutableSetOf() }) }

    val vertices get() = map.keys

    operator fun get(vertex: T) = map[vertex]

    fun neighbours(vertex: T) = this[vertex]
    fun addVertex(vertex: T) = map.put(vertex, mutableSetOf())
    fun removeVertex(vertex: T) = map.remove(vertex)?.also {
        map.values.forEach { it.remove(vertex) }
    }

    fun isAdjacent(from: T, to: T) = map[from]?.contains(to) ?: false
    fun addEdge(from: T, to: T) = map[from]?.add(to) ?: false
    fun removeEdge(from: T, to: T) = map[from]?.remove(to) ?: false
}