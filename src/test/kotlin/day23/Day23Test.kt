package day23

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day23Test {
    private val testInput = """
        kh-tc
        qp-kh
        de-cg
        ka-co
        yn-aq
        qp-ub
        cg-tb
        vc-aq
        tb-ka
        wh-tc
        yn-cg
        kh-ub
        ta-co
        de-co
        tc-td
        tb-wq
        wh-td
        ta-ka
        td-qp
        aq-cg
        wq-ub
        ub-vc
        de-ta
        wq-aq
        wq-vc
        wh-yn
        ka-de
        kh-ta
        co-tc
        wh-qp
        tb-vc
        td-yn
    """.trimIndent().split('\n')

    @Test
    fun part1() {
        assertEquals(7,  part1(testInput))
    }

    @Test
    fun part2() {
        assertEquals("co,de,ka,ta",  part2(testInput))
    }
}