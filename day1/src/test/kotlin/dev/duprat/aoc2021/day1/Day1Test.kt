/*
 * This Kotlin source file was generated by the Gradle "init" task.
 */
package dev.duprat.aoc2021.day1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun testReadFile() {
        Day1.readInput()
        assertEquals(10, Day1.lines.size)
        assertEquals(199, Day1.lines[0])
    }

    @Test
    fun testDepthsLargerThanPrevious() {
        Day1.readInput()
        assertEquals(
                listOf(200, 208, 210, 207, 240, 269, 263),
                Day1.detectDepthsLargerThanPrevious()
        )
    }

    @Test
    fun testResult() {
        Day1.readInput()
        assertEquals(7, Day1.solveDepthsLargerThanPrevious())
    }

    @Test
    fun testTripletSumsLargerThanPrevious() {
        Day1.readInput()
        assertEquals(listOf(618, 647, 716, 769, 792), Day1.detectTripletSumsLargerThanPrevious())
    }

    @Test
    fun testTripletSumsLargerThanPreviousResult() {
        Day1.readInput()
        assertEquals(5, Day1.solveTripletSumsLargerThanPrevious())
    }
}
