package day_01

class RocketEquation {
    companion object {
        fun solvePartOne(): Int {
            val instance = RocketEquation()
            return instance.readFileAsLinesUsingGetResourceAsStream("input.txt")
                .map { instance.calculateFuel(it) }
                .sum()
        }

        fun solvePartTwo(): Int {
            val instance = RocketEquation()
            return instance.readFileAsLinesUsingGetResourceAsStream("input.txt")
                .map { instance.calculateTotalFuel(it, 0) }
                .sum()
        }
    }

    fun readFileAsLinesUsingGetResourceAsStream(fileName: String): List<Int> {
        return this::class.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readLines()
            .map { it.toInt() }
    }

    fun calculateFuel(mass: Int): Int {
        val fuel = (mass / 3) - 2
        return when (fuel > 0) {
            true -> fuel
            false -> 0
        }
    }

    fun calculateTotalFuel(mass: Int, currentFuel: Int): Int {
        val neededFuel = calculateFuel(mass)
        return when (neededFuel > 0) {
            true -> calculateTotalFuel(neededFuel, currentFuel + neededFuel)
            false -> currentFuel
        }
    }
}

fun main() {
    println("Part 1: ${RocketEquation.solvePartOne()}")
    println("Part 2: ${RocketEquation.solvePartTwo()}")
}

