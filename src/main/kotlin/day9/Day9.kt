package day9

import getInput

data class Block(val id: ULong? = null) {
    fun isEmpty() = id == null
    override fun toString() = id?.toString() ?: "."
}

class Disk(input: String) {
    private val filesystem = buildList {
        input.forEachIndexed { index, char ->
            val block = when (index % 2) {
                0 -> Block(index.toULong() / 2u)
                else -> Block()
            }
            repeat(char.digitToInt()) { add(block) }
        }
    }.toMutableList()

    private val occupied = filesystem.count { !it.isEmpty() }

    fun defragment() {
        val sources = filesystem.asReversed().asSequence().filterNot(Block::isEmpty).iterator()
        filesystem.take(occupied).forEachIndexed { index, target ->
            if (target.isEmpty()) {
                filesystem[index] = sources.next()
            }
        }
        filesystem.indices.drop(occupied).forEach { filesystem[it] = Block() }
    }

    fun checksum(): ULong = filesystem.withIndex().filterNot { it.value.isEmpty() }
        .sumOf { (index, block) -> index.toULong() * (block.id ?: 0u) }

    override fun toString() = filesystem.joinToString("")
}

fun part1(input: String) = Disk(input).apply(Disk::defragment).checksum()
fun part2(input: String) = 0

fun main() {
    val input = getInput(9).first()
    println(part1(input))
}