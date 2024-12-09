
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CombinatoricsTest {
    @Test
    fun tuples() {
        val input = listOf(0, 1, 2)
        val expected = listOf(
            listOf(0, 0),
            listOf(0, 1),
            listOf(0, 2),
            listOf(1, 0),
            listOf(1, 1),
            listOf(1, 2),
            listOf(2, 0),
            listOf(2, 1),
            listOf(2, 2)
        )

        assertEquals(expected, input.tuples(2).toList())
    }

    @Test
    fun combinations() {
        val input = listOf(0, 1, 2)
        val expected = listOf(
            listOf(0, 1), listOf(0, 2), listOf(1, 2)
        )

        assertEquals(expected, input.combinations(2).toList())
    }
}