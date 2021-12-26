package dev.duprat.aoc2021.day25

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day25Test {
    @Test
    fun testInit() {
        val scenario = Day25Scenario()
        assertEquals(9, scenario.cucumbers.size)
        assertEquals(90, scenario.cucumbers.sumOf { it.size })
        assertEquals(0, scenario.step)
    }

    @Test
    fun testStep() {
        val scenario = Day25Scenario()
        val hasChanged = scenario.moveCucumbers()
        assertEquals(
                """

....>.>v.>
v.v>.>v.v.
>v>>..>v..
>>v>v>.>.v
.>v.v...v.
v>>.>vvv..
..v...>>..
vv...>>vv.
>.v.v..v.v

""",
                scenario.cucumbers.joinToString("", prefix = "\n\n", postfix = "\n") {
                    it.joinToString("", postfix = "\n")
                }
        )
        assertEquals(true, hasChanged)
        assertEquals(1, scenario.step)
    }

    @Test
    fun testDeadlockStep() {
        val scenario = Day25Scenario()
        scenario.moveUntilDeadlock()
        assertEquals(58, scenario.step)
    }
}
