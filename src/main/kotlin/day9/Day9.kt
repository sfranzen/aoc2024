package day9

import getInput

data class Block(val id: ULong? = null) {
    fun isEmpty() = id == null
    override fun toString() = id?.toString() ?: "."
}

data class File(val id: ULong, val indices: List<Int>) {
    val size = indices.size
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

    private fun move(from: Int, to: Int) {
        filesystem[to] = filesystem[from]
        filesystem[from] = Block()
    }

    fun defragment() {
        val sources = filesystem.asReversed().asSequence().filterNot(Block::isEmpty).iterator()
        filesystem.take(occupied).forEachIndexed { index, target ->
            if (target.isEmpty()) {
                filesystem[index] = sources.next()
            }
        }
        filesystem.indices.drop(occupied).forEach { filesystem[it] = Block() }
    }

    fun defragment2() {
        val files = filesystem.withIndex().filterNot { it.value.isEmpty() }.groupBy { it.value.id!! }
            .map { (id, blocks) -> File(id, blocks.map { it.index }) }.reversed()
        for (file in files) {
            filesystem.indices.filter { it < file.indices[0] }.windowed(file.size)
                .firstOrNull { span -> span.all { filesystem[it].isEmpty() } }?.let { span ->
                    file.indices.zip(span).forEach { (fileIndex, spanIndex) -> move(fileIndex, spanIndex) }
                }
        }
    }

    fun checksum(): ULong = filesystem.withIndex().filterNot { it.value.isEmpty() }
        .sumOf { (index, block) -> index.toULong() * (block.id ?: 0u) }

    override fun toString() = filesystem.joinToString("")
}

fun part1(input: String) = Disk(input).apply(Disk::defragment).checksum()
fun part2(input: String) = Disk(input).apply(Disk::defragment2).checksum()

fun main() {
    val input = getInput(9).first()
    println(part1(input))
    println(part2(input))
}