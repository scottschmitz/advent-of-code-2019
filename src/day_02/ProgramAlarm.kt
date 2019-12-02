package day_02

class ProgramAlarm {
    companion object {
        private const val NOUN = 1
        private const val VERB = 2

        fun solvePartOne(): Int {
            val instance = ProgramAlarm()
            val numbers = instance.readFileAsLinesUsingGetResourceAsStream("input.txt").toMutableList()

            // the first step is to restore the gravity assist program (your puzzle input) to the "1202 program alarm"
            // state it had just before the last computer caught fire. To do this, before running the program,
            // replace position 1 with the value 12 and replace position 2 with the value 2.
            numbers[NOUN] = 12
            numbers[VERB] = 2

            return instance.solve(numbers)
        }

        fun solvePartTwo(): Int {
            val instance = ProgramAlarm()
            val originalNumbers = instance.readFileAsLinesUsingGetResourceAsStream("input.txt")

            val target = 19_690_720
            for (i in 0..100) {
                for (j in 0..100) {
                    val numbers = originalNumbers.toMutableList()
                    numbers[NOUN] = i
                    numbers[VERB] = j
                    val solution = instance.solve(numbers)
                    if (solution == target) {
                        return 100 * i + j
                    }
                }
            }
            return -1
        }
    }

    fun readFileAsLinesUsingGetResourceAsStream(fileName: String): List<Int> {
        return this::class.java.getResourceAsStream(fileName)
            .bufferedReader()
            .readLines()
            .first()
            .split(",")
            .map { it.toInt() }
    }

    // The three integers immediately after the opcode tell you these three positions
    // the first two indicate the positions from which you should read the input values,
    // and the third indicates the position at which the output should be stored.
    fun solve(numbers: MutableList<Int>): Int {
        for (i in numbers.indices step 4) {
            val x = numbers[numbers[i + 1]]
            val y = numbers[numbers[i + 2]]
            val dest = numbers[i + 3]

            when (numbers[i]) {
                1 -> numbers[dest] = x + y // Addition
                2 -> numbers[dest] = x * y // Multiplication
                99 -> return numbers.first() // Quit
                else -> {
                    println("Bad input at $i: ${numbers[i]}")
                    return -1
                }
            }
        }
        return numbers.first()
    }
}

fun main() {
    println("Part 1: ${ProgramAlarm.solvePartOne()}")
    println("Part 2: ${ProgramAlarm.solvePartTwo()}")
}

