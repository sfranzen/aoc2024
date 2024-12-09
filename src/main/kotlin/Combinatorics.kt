fun <T> Iterable<T>.tuples(k: Int): Sequence<List<T>> = sequence {
    when {
        k <= 0 -> yield(emptyList())
        else -> yieldAll(flatMap { t -> tuples(k - 1).map { listOf(t) + it } })
    }
}

fun <T> Iterable<T>.combinations(k: Int): Sequence<List<T>> = sequence {
    when {
        k <= 0 -> yield(emptyList())
        else -> yieldAll(flatMapIndexed { i, t -> drop(i + 1).combinations(k - 1).map { listOf(t) + it } })
    }
}