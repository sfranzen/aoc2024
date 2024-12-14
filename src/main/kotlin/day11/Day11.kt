package day11

import getInput

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

fun part1(input: String) = blinkSequence(input).take(25).last().let(::stoneCount)
fun part2(input: String) = 0

fun main() {
    val input = getInput(11).first()
    println(part1(input))
}