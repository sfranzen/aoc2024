package day23

import buildGraph
import combinations
import getInput

fun networkGraph(connectionList: List<String>) = buildGraph {
    connectionList.map { it.split('-') }.forEach { (host1, host2) ->
        addEdge(host1, host2)
    }
}

fun part1(input: List<String>) = buildSet {
    networkGraph(input).run { bronKerbosch() }.forEach {
        when {
            it.size == 3 -> add(it)
            it.size > 3 -> addAll(it.combinations(3))
        }
    }
}.count { clique -> clique.any { it.startsWith('t') } }

fun part2(input: List<String>) = networkGraph(input).run { bronKerbosch() }.maxBy { it.size }.sorted().joinToString(",")

fun main() {
    val input = getInput(23)
    println(part1(input))
    println(part2(input))
}