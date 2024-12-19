data class Vector2D(val row: Int = 0, val col: Int = 0) {
    operator fun unaryMinus() = copy(row = -row, col = -col)
    operator fun unaryPlus() = copy()
    operator fun plus(other: Vector2D) = copy(row = row + other.row, col = col + other.col)
    operator fun minus(other: Vector2D) = copy(row = row - other.row, col = col - other.col)
}

enum class Direction(val vector: Vector2D) {
    Up(-1, 0), Right(0, 1), Down(1, 0), Left(0, -1);

    constructor(row: Int, col: Int) : this(Vector2D(row, col))
}

fun line(position: Vector2D, direction: Vector2D) = generateSequence(position) { it + direction }

open class Map2D<T>(layout: List<List<T>>) : List<List<T>> by layout {
    val height get() = size
    val width get() = getOrNull(0)?.size ?: 0

    fun contains(position: Vector2D): Boolean = with(position) { row in indices && col in 0..<width }

    fun positionOf(value: T): Vector2D? = map { list -> list.indexOf(value) }.withIndex().find { (_, col) -> col != -1 }
        ?.let { (row, col) -> Vector2D(row, col) }

    fun positions(value: T): List<Vector2D> =
        mapIndexed { row, col, t -> if (value == t) Vector2D(row, col) else null }.flatten().filterNotNull()

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
