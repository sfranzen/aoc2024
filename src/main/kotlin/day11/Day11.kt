package day11

import getInput
import java.util.concurrent.ConcurrentHashMap

fun blink(input: String) = buildList {
    input.split(' ').forEach {
        when {
            it == "0" -> add(1)
            it.length % 2 == 0 -> addAll(it.chunked(it.length / 2).map(String::toULong))
            else -> add(2024u * it.toULong())
        }
    }
}.joinToString(" ")

fun blinkSequence(input: String) = generateSequence(input, ::blink).drop(1)

fun stoneCount(input: String) = input.count { it == ' ' } + 1

class StoneCounter(input: String) {
    val map = ConcurrentHashMap<ULong, ULong>().apply {
        input.split(' ').map(String::toULong).forEach { merge(it, 1u, ULong::plus) }
    }

    fun blink() = map.entries.toList().forEach { (key, value) ->
        val str = key.toString()
        fun increment(key: ULong) = map.merge(key, value, ULong::plus)
        map.merge(key, value, ULong::minus)

        when {
            key == 0uL -> increment(1uL)

            str.length % 2 == 0 -> str.chunked(str.length / 2).map(String::toULong).forEach(::increment)

            else -> increment(key * 2024uL)
        }
    }

    fun count() = map.values.sum()
}

fun part1(input: String) = blinkSequence(input).take(25).last().let(::stoneCount)
fun part2(input: String) = StoneCounter(input).apply {
    repeat(75) {
        blink()
    }
}.count()

fun main() {
    val input = getInput(11).first()
    println(part1(input))
    println(part2(input))
}