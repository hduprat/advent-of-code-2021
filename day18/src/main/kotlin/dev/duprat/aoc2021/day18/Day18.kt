package dev.duprat.aoc2021.day18

import dev.duprat.aoc2021.utils.Scenario

class Day18Scenario : Scenario {
  val n: SnailfishNumber

  constructor() : super() {
    n = SnailfishNumber(lines[0])
  }

  fun addAllHomework() {
    lines.drop(1).forEach { n.add(it) }
  }

  fun getAllLinesExcept(index: Int): List<String> {
    return lines.mapIndexed { i, elt -> if (i == index) null else elt }.filterNotNull()
  }

  fun findHighestMagnitude(): Long {
    val highestMagnitudes =
        lines.mapIndexed { index, elt ->
          getAllLinesExcept(index).maxOf {
            val number = SnailfishNumber(elt)
            number.add(it)
            number.magnitude
          }
        }

    return highestMagnitudes.maxOrNull() ?: 0
  }
}

fun main() {
  val scenario = Day18Scenario()
  scenario.addAllHomework()
  println("Exercice 1")
  println(scenario.n.magnitude)
  println("Exercice 2")
  println(scenario.findHighestMagnitude())
}
