import java.io.FileNotFoundException

fun getInput(day: Int): List<String> {
    val resource = "/day$day.txt"
    return object {}.javaClass.getResourceAsStream(resource)
        ?.run { readAllBytes().decodeToString().split('\n').dropLastWhile { it.isBlank() } }
        ?: throw FileNotFoundException(resource)
}