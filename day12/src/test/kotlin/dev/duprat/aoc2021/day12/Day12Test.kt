/*
 * This Kotlin source file was generated by the Gradle "init" task.
 */
package dev.duprat.aoc2021.day12

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day12Test {
    @Test
    fun testInit() {
        val scenario = Day12Scenario()
        assertEquals(setOf("start", "b", "c", "d", "end"), scenario.smallCaves)
        assertEquals(setOf("A"), scenario.largeCaves)
        assertEquals(setOf("A", "start", "d", "end"), scenario.caveSystem["b"])
    }

    @Test
    fun testFindPathsToEnd() {
        val scenario = Day12Scenario()

        assertEquals(
                listOf("start,b,end", "start,b,A,end", "start,b,A,c,A,end").toSet(),
                scenario.findPathsToEnd("b", "start").toSet()
        )
        assertEquals(
                listOf("start,A,c,A,end", "start,A,c,A,b,end", "start,A,c,A,b,A,end").toSet(),
                scenario.findPathsToEnd("c", "start,A").toSet()
        )
        assertEquals(
                listOf(
                                "start,A,b,A,c,A,end",
                                "start,A,b,A,end",
                                "start,A,b,end",
                                "start,A,c,A,b,A,end",
                                "start,A,c,A,b,end",
                                "start,A,c,A,end",
                                "start,A,end",
                                "start,b,A,c,A,end",
                                "start,b,A,end",
                                "start,b,end",
                        )
                        .toSet(),
                scenario.findPathsToEnd().toSet()
        )
    }

    @Test
    fun testCountPaths() {
        assertEquals(10, Day12Scenario().countPaths())
        assertEquals(19, Day12Scenario("/inputLarge.txt").countPaths())
        assertEquals(226, Day12Scenario("/inputLarger.txt").countPaths())
    }

    @Test
    fun testCountPathsWithSmallerCavesTwice() {
        assertEquals(36, Day12Scenario().countPaths(2))
        assertEquals(103, Day12Scenario("/inputLarge.txt").countPaths(2))
        assertEquals(3509, Day12Scenario("/inputLarger.txt").countPaths(2))
    }
}
