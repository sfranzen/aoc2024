import java.io.File

fun getInput(day: Int) = File(object {}.javaClass.getResource("/day$day/input")?.file ?: "")