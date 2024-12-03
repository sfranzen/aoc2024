import day2.*
import kotlin.test.Test
import kotlin.test.assertContentEquals

class Day2Test {
    private val testInput = listOf(
        listOf(7, 6, 4, 2, 1),
        listOf(1, 2, 7, 8, 9),
        listOf(9, 7, 6, 2, 1),
        listOf(1, 3, 2, 4, 5),
        listOf(8, 6, 4, 4, 1),
        listOf(1, 3, 6, 7, 9)
    )

    @Test
    fun part1() {
        val expected = listOf(true, false, false, false, false, true)
        assertContentEquals(expected, testInput.map(::isSafe))
    }

    @Test
    fun part2() {
        val expected = listOf(true, false, false, true, true, true)
        assertContentEquals(expected, testInput.map(::isSafeWithDampening))
    }
}