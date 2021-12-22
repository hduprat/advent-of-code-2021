package dev.duprat.aoc2021.day21

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day21Test {
    @Test
    fun testInit() {
        val scenario = Day21Scenario()
        assertEquals(4, scenario.pos1)
        assertEquals(8, scenario.pos2)
    }

    @Test
    fun testGame() {
        val scenario = Day21Scenario()
        val endGame = scenario.playDeterministicGame()
        assertEquals(Pair(1000, 745), endGame.scores)
        assertEquals(993, endGame.rolls)
    }

    @Test
    fun testEndgame() {
        val scenario = Day21Scenario()
        val endGame = scenario.playDeterministicGame()
        assertEquals(739785, endGame.rolls * endGame.loserScore)
    }

    @Test
    fun testQuantumEndgame() {
        val scenario = Day21Scenario()
        val baseGame = Game(Pair(scenario.pos1, scenario.pos2), maxScore = 21)

        val endGame = scenario.playQuantumGame(baseGame)
        assertEquals(444356092776315, endGame.first)
        assertEquals(341960390180808, endGame.second)
    }
}
