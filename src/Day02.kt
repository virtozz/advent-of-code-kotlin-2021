fun main() {
    val input = readInput("Day02").map{ it.split(" ") }

    var xPos = 0
    var yPos = 0

    for (element in input) {
        val (direction, value) = element
        when (direction) {
            "forward" -> xPos += value.toInt()
            "down" -> yPos += value.toInt()
            "up" -> yPos -= value.toInt()
        }
    }

    val position = xPos * yPos

    println(position)
}