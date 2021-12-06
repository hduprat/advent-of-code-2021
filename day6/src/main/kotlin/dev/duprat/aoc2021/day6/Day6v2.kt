/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package dev.duprat.aoc2021.day6

import dev.duprat.aoc2021.utils.Scenario

class Day6ScenarioVariant : Scenario {
    val initialLanternFishes: List<Int>

    val lanternFishSpawnDuration = 7
    val newLanternFishFirstDuration = 9

    val newLanternFishCount: Array<Long>

    constructor(maxDays: Int) : super() {
        initialLanternFishes = lines[0].split(",").map { it.toInt() }
        newLanternFishCount = Array<Long>(maxDays + 1) { 0 }
    }

    fun addNewLanternFishesBasedOnDay(day: Int) {
        val maxDays = newLanternFishCount.size - 1
        for (d in (day + newLanternFishFirstDuration)..maxDays step lanternFishSpawnDuration) {
            newLanternFishCount[d] += newLanternFishCount[day]
        }
    }

    fun countLanternFishes(): Long {
        val maxDays = newLanternFishCount.size - 1
        initialLanternFishes.forEach {
            for (d in (it + 1)..maxDays step lanternFishSpawnDuration) {
                newLanternFishCount[d]++
            }
        }
        (1 until newLanternFishCount.size).forEach {
            if (newLanternFishCount[it] != 0.toLong()) addNewLanternFishesBasedOnDay(it)
        }
        return initialLanternFishes.size + newLanternFishCount.sum()
    }
}
