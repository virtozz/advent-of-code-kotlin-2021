fun main() {
    fun parseSteps(input: List<String>): List<Int> {
        return input[0].split(",").map { it.toInt() }
    }

    fun parseBoards(input: List<String>): List<Board> {
        val boards = mutableListOf<Board>()

        for (i in 1 until input.size) {
            if (input[i] == "") {
                boards.add(Board())
            } else {
                val board = boards.last()
                board.addLine(input[i])
            }
        }

        return boards
    }

    fun part1(input: List<String>): Int {
        val steps = parseSteps(input)
        val boards = parseBoards(input)

        for (step in steps) {
            boards.forEach { it.mark(step) }

            val winner = boards.find { it.bingo() }
            if (winner != null) {
                return step * winner.score()
            }
        }

        return 0
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    val input = readInput("Day04")

    println(part1(input))
    println(part2(input))
}

class Board {
    private val numbers = mutableListOf<Int>()
    private val markedNumbers = mutableListOf<Int>()

    fun addLine(line: String) {
        val newLine = line
            .trim()
            .split("\\s+".toRegex())
            .map { Integer.parseInt(it) }

        this.numbers.addAll(newLine)
    }

    fun mark(number: Int) {
        if (this.numbers.contains(number)) {
            this.markedNumbers.add(number)
        }
    }

    fun bingo(): Boolean {
        return bingoRows() || bingoColumns()
    }

    fun score(): Int {
        return this.numbers
            .filter { !this.markedNumbers.contains(it) }
            .sum()
    }

    private fun bingoRows(): Boolean {
        val rows = this.numbers.windowed(5, 5)
        return rows.any { this.markedNumbers.containsAll(it) }
    }

    private fun bingoColumns(): Boolean {
        for (i in 0..4) {
            val column = mutableListOf<Int>()

            for (j in i until this.numbers.size step 5) {
                column.add(this.numbers[j])
            }

            if (this.markedNumbers.containsAll(column)) { return true }
        }

        return false
    }
}