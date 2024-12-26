import java.util.concurrent.ConcurrentHashMap

interface Graph<T> {
    val vertices: Set<T>
    operator fun get(key: T): Set<T>?
    fun contains(vertex: T): Boolean = this[vertex] != null
    fun isAdjacent(from: T, to: T): Boolean = this[from]?.contains(to) ?: false
    fun neighbours(vertex: T): Set<T>? = this[vertex]
    fun addVertex(vertex: T): Set<T>?
    fun removeVertex(vertex: T): Set<T>?
    fun addEdge(from: T, to: T): Boolean
    fun removeEdge(from: T, to: T): Boolean
}

inline fun <T : Any> buildGraph(vertices: Collection<T> = emptyList(), block: UndirectedGraph<T>.() -> Unit) =
    UndirectedGraph(vertices).apply(block)

inline fun <T : Any> buildDiGraph(vertices: Collection<T> = emptyList(), block: DirectedGraph<T>.() -> Unit) =
    DirectedGraph(vertices).apply(block)

open class AbstractGraph<T : Any>(vertices: Collection<T>) : Graph<T>,
    MutableMap<T, MutableSet<T>> by ConcurrentHashMap(vertices.size) {
    init {
        putAll(vertices.map { it to mutableSetOf() })
    }

    override val vertices get() = keys

    override fun addVertex(vertex: T) = put(vertex, mutableSetOf())
    override fun removeVertex(vertex: T) = remove(vertex)?.also {
        values.forEach { it.remove(vertex) }
    }

    override fun addEdge(from: T, to: T) = getOrPut(from, ::mutableSetOf).add(to)
    override fun removeEdge(from: T, to: T) = this[from]?.remove(to) ?: false
}

open class DirectedGraph<T : Any>(vertices: Collection<T> = emptyList()) : AbstractGraph<T>(vertices)

open class UndirectedGraph<T : Any>(vertices: Collection<T> = emptyList()) : AbstractGraph<T>(vertices) {
    override fun addEdge(from: T, to: T): Boolean = super.addEdge(from, to) && super.addEdge(to, from)
    override fun removeEdge(from: T, to: T) = super.removeEdge(from, to) && super.removeEdge(to, from)

    // The Bron-Kerbosch algorithm recursively locates the maximal cliques of an undirected graph.
    fun bronKerbosch(): List<Set<T>> = bronKerboschImpl(emptySet(), vertices, emptySet())

    // An implementation of the Bron-Kerbosch algorithm with pivot vertex. The vertex of the highest degree in the
    // subset P U X is chosen as the pivot.
    private fun bronKerboschImpl(r: Set<T>, p: Set<T>, x: Set<T>): List<Set<T>> {
        if (p.isEmpty() && x.isEmpty()) {
            return listOf(r)
        }

        val u = p.union(x).maxBy { neighbours(it)!!.size }
        val p1 = p.toMutableSet()
        val x1 = x.toMutableSet()
        return (p - neighbours(u)!!).flatMap { v ->
            val nv = neighbours(v)!!
            bronKerboschImpl(r + v, p1.intersect(nv), x1.intersect(nv)).also {
                p1 -= v
                x1 += v
            }
        }
    }
}
