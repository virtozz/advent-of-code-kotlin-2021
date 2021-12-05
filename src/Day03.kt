fun main() {
    fun part1(input: List<String>): Int {
        val half = input.size / 2

        var gamma = ""
        var epsilon = ""

        for (i in 0..11) {
            val countOfZeros = input
                .map { it[i] }
                .count { it == '0' }

            if (countOfZeros > half) {
                gamma += "0"
                epsilon += "1"
            } else {
                gamma += "1"
                epsilon += "0"
            }
        }

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun mostCommonBit(input: List<String>, pos: Int): Char {
        val half = Math.ceil(input.size.toDouble() / 2)

        val countOfOnes = input
            .map { it[pos] }
            .count { it == '1' }

        return if (countOfOnes >= half) {
            '1'
        } else {
            '0'
        }
    }

    fun findOxygen(input: List<String>, pos: Int): String {
        if (input.size == 1) {
            return input.first()
        } else {
            val bit = mostCommonBit(input, pos)
            val oxygen = input.filter { it[pos] == bit }

            return findOxygen(oxygen, pos + 1)
        }
    }

    fun findCO2(input: List<String>, pos: Int): String {
        if (input.size == 1) {
            return input.first()
        } else {
            val bit = mostCommonBit(input, pos)
            val co2 = input.filter { it[pos] != bit }

            return findCO2(co2, pos + 1)
        }
    }

    fun part2(input: List<String>): Int {
        val oxygen = findOxygen(input, 0)
        val co2 = findCO2(input, 0)

        return oxygen.toInt(2) * co2.toInt(2)
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}