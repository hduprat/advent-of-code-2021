package dev.duprat.aoc2021.day22

import dev.duprat.aoc2021.utils.Scenario

class Day22Scenario : Scenario {
  val initRules: List<Rule>
  val rebootRules: List<Rule>

  var regions: List<Region>

  constructor(inputPath: String = "/input.txt") : super(inputPath) {
    val ruleList: List<Rule> = lines.map { Rule(it) }
    initRules = ruleList.filter { !it.isOutOfInitialRange() }
    rebootRules = ruleList.filter { it.isOutOfInitialRange() }

    regions = initRules.fold(listOf()) { list, rule -> rule.applyTo(list) }
  }

  fun reboot() {
    regions = rebootRules.fold(regions) { list, rule -> rule.applyTo(list) }
  }

  fun countLitCubes(): Long {
    return regions.sumOf { it.cardinal }
  }
}

fun main() {
  val scenario = Day22Scenario()
  println("Exercice 1")
  println(scenario.countLitCubes())
  println("Exercice 2")
  scenario.reboot()
  println(scenario.countLitCubes())
}
