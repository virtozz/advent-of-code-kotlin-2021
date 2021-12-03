fun main() {
    val input = readInput("Day01").map { it.toInt() }

    var starCount = 0

    for (i in 1 until input.size) {
        if (input[i] > input[i-1]) {
            starCount++
        }
    }

    println(starCount)
}
