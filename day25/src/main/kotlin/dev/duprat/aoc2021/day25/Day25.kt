package dev.duprat.aoc2021.day25

import dev.duprat.aoc2021.utils.Scenario

class Day25Scenario : Scenario {
  var cucumbers: List<List<Char>>
  var step = 0

  constructor() : super() {
    cucumbers = lines.map { it.toList() }
  }

  fun moveEasternCucumbers(): Boolean {
    val nextCucumbers = cucumbers.mapTo(mutableListOf()) { it.mapTo(mutableListOf()) { '.' } }
    var hasChanged = false

    cucumbers.forEachIndexed { y, row ->
      row.forEachIndexed { x, cucumber ->
        val xNext = (x + 1) % row.size
        when (cucumber) {
          'v' -> nextCucumbers[y][x] = 'v'
          '>' ->
              if (row[xNext] == '.') {
                hasChanged = true
                nextCucumbers[y][xNext] = '>'
              } else nextCucumbers[y][x] = '>'
        }
      }
    }

    cucumbers = nextCucumbers

    return hasChanged
  }

  fun moveSouthernCucumbers(): Boolean {
    val nextCucumbers = cucumbers.mapTo(mutableListOf()) { it.mapTo(mutableListOf()) { '.' } }
    var hasChanged = false

    cucumbers.forEachIndexed { y, row ->
      val yNext = (y + 1) % cucumbers.size
      row.forEachIndexed { x, cucumber ->
        when (cucumber) {
          '>' -> nextCucumbers[y][x] = '>'
          'v' ->
              if (cucumbers[yNext][x] == '.') {
                hasChanged = true
                nextCucumbers[yNext][x] = 'v'
              } else nextCucumbers[y][x] = 'v'
        }
      }
    }

    cucumbers = nextCucumbers

    return hasChanged
  }

  fun moveCucumbers(): Boolean {
    val hasChangedAfterEast = moveEasternCucumbers()
    val hasChangedAfterSouth = moveSouthernCucumbers()

    step++

    return hasChangedAfterEast || hasChangedAfterSouth
  }

  fun moveUntilDeadlock() {
    while (moveCucumbers()) {}
  }

  fun draw() {
    cucumbers.forEach { println(it.joinToString("")) }
    println()
    println("-".repeat(cucumbers.last().size))
    println()
  }
}

fun main() {
  val scenario = Day25Scenario()
  println("Exercice 1")
  scenario.moveUntilDeadlock()
  println(scenario.step)
  println("Exercice 2")
  println()
}
