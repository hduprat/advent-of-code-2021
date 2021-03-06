/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package dev.duprat.aoc2021.day1

class Day1 {
    companion object {
        public lateinit var lines: List<Int>

        fun readInput() {
            val file = this::class.java.getResource("/input.txt").readText()
            this.lines = file.split('\n').map { it.toInt() }
        }

        private fun detectLargerThanPrevious(list: List<Int>): List<Int> {
            val comparisonPairs = list.windowed(2)
            val compatiblePairs = comparisonPairs.filter { it[1] > it[0] }
            return compatiblePairs.map { it[1] }
        }

        fun detectDepthsLargerThanPrevious(): List<Int> {
            return detectLargerThanPrevious(this.lines)
        }

        fun solveDepthsLargerThanPrevious(): Int {
            return detectDepthsLargerThanPrevious().size
        }

        fun detectTripletSumsLargerThanPrevious(): List<Int> {
            val triplets = this.lines.windowed(3)
            val tripletSums = triplets.map { it.sum() }
            return detectLargerThanPrevious(tripletSums)
        }

        fun solveTripletSumsLargerThanPrevious(): Int {
            return detectTripletSumsLargerThanPrevious().size
        }
    }
}

fun main() {
    println("Exercice 1")
    Day1.readInput()
    println(Day1.solveDepthsLargerThanPrevious())
    println("Exercice 2")
    println(Day1.solveTripletSumsLargerThanPrevious())
}
