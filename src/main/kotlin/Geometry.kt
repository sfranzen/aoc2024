data class Vector2D(val row: Int, val col: Int) {
    operator fun unaryMinus() = copy(row = -row, col = -col)
    operator fun unaryPlus() = copy()
    operator fun plus(other: Vector2D) = copy(row = row + other.row, col = col + other.col)
    operator fun minus(other: Vector2D) = copy(row = row - other.row, col = col - other.col)
}

open class Map2D<out T>(layout: List<List<T>>) : List<List<T>> by layout {
    val height get() = size
    val width get() = getOrNull(0)?.size ?: 0

    fun contains(position: Vector2D): Boolean = with(position) { row in indices && col in 0..<width }

    fun get(row: Int, col: Int): T? = getOrNull(row)?.getOrNull(col)
    fun get(position: Vector2D): T? = with(position) { get(row, col) }

    inline fun <R> mapIndexed(function: (a: Int, b: Int, value: T) -> R): List<List<R>> = mapIndexed { row, string ->
        string.mapIndexed { col, value -> function(row, col, value) }
    }

    override fun toString(): String = joinToString("\n")

    companion object {
        fun fromStringList(strings: List<String>) = Map2D(strings.map(String::toList))
    }
}