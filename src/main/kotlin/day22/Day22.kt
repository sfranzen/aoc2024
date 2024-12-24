package day22

import getInput

data class PriceChange(val price: Long, val change: Long)

fun mix(input: Long, secret: Long) = input xor secret
fun prune(input: Long) = input % 16777216

fun produce(secret: Long) =
    mix(secret * 64, secret).let(::prune).let { mix(it / 32, it) }.let(::prune).let { mix(it * 2048, it) }.let(::prune)

fun secrets(secret: Long) = generateSequence(secret, ::produce)

fun prices(secret: Long) = secrets(secret).map { it.toString().last().digitToInt().toLong() }

fun changeSequence(secret: Long) = prices(secret).zipWithNext { a, b -> PriceChange(b, b - a) }

fun bestPrice(list: List<PriceChange>) = list.windowed(4).maxBy { it.last().price }

fun convert(input: List<String>) = input.map(String::toLong)

fun part1(input: List<String>): Long = convert(input).sumOf { secrets(it).elementAt(2000) }

fun part2(input: List<String>): Long {
    val changes = convert(input).map { changeSequence(it).take(2000).toList() }
    fun matches(sequence: List<PriceChange>) = changes.mapNotNull { list ->
        list.windowed(4).find { sublist -> sublist.zip(sequence).all { (a, b) -> a.change == b.change } }
    }
    return changes[0].windowed(4).map(::matches).maxOf { group -> group.sumOf { it.last().price } }
}

fun main() {
    val input = getInput(22)
    println(part1(input))
    println(part2(input))
}