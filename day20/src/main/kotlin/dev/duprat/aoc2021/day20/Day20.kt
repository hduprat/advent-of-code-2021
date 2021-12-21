package dev.duprat.aoc2021.day20

import dev.duprat.aoc2021.utils.Scenario

class Day20Scenario : Scenario {
  val algorithm: String
  var image: Map<Pair<Int, Int>, Boolean> = mapOf()

  var minX: Int = 0
  var maxX: Int = 0
  var minY: Int = 0
  var maxY: Int = 0

  var enhanceFactor = 0

  var outOfBoundsValue: Char = '0'

  constructor() : super() {
    algorithm = lines[0]
    val img = mutableMapOf<Pair<Int, Int>, Boolean>()
    lines.drop(2).forEachIndexed { y, line ->
      line.forEachIndexed { x, char -> if (char == '#') img.put(x to y, true) }
    }
    image = img
    if (image.values.contains(false)) throw Error("Bad image")
  }

  fun algoValue(x: Int, y: Int): Int {
    val binValue: String =
        (-1..1)
            .map { dy ->
              (-1..1)
                  .map { dx ->
                    when {
                      x + dx <= minX -> outOfBoundsValue
                      x + dx >= maxX -> outOfBoundsValue
                      y + dy <= minY -> outOfBoundsValue
                      y + dy >= maxY -> outOfBoundsValue
                      image.getOrDefault(x + dx to y + dy, false) -> '1'
                      else -> '0'
                    }
                  }
                  .joinToString("")
            }
            .joinToString("")

    return binValue.toInt(2)
  }

  fun updateBounds() {
    minX = image.keys.minOf { it.first } - 1
    maxX = image.keys.maxOf { it.first } + 1

    minY = image.keys.minOf { it.second } - 1
    maxY = image.keys.maxOf { it.second } + 1
  }

  fun updateOutOfBoundsValue() {
    if (algorithm.first() == '.') outOfBoundsValue = '0'
    else if (algorithm.last() == '#') outOfBoundsValue = if (enhanceFactor == 0) '0' else '1'
    else outOfBoundsValue = if (enhanceFactor % 2 == 0) '0' else '1'
  }

  fun enhance(toFactor: Int = enhanceFactor + 1) {

    while (enhanceFactor < toFactor) {
      print("Enhancing step ${enhanceFactor+1} / $toFactor\r")

      updateBounds()
      updateOutOfBoundsValue()

      val newImg = mutableMapOf<Pair<Int, Int>, Boolean>()

      for (x in minX..maxX) {
        for (y in minY..maxY) {
          val newPixel = algorithm[algoValue(x, y)]
          if (newPixel == '#') newImg.put(x to y, true)
        }
      }

      image = newImg
      enhanceFactor++
    }
  }

  fun draw() {
    for (y in minY - 5..maxY + 5) {
      var line: String = ""
      for (x in minX - 5..maxX + 5) {
        line +=
            when {
              x <= minX -> outOfBoundsValue
              x >= maxX -> outOfBoundsValue
              y <= minY -> outOfBoundsValue
              y >= maxY -> outOfBoundsValue
              image.getOrDefault(x to y, false) -> '1'
              else -> '0'
            }
      }
      println(line.map { if (it == '1') '#' else '.' }.joinToString(""))
    }
  }
}

fun main() {
  val scenario = Day20Scenario()
  println("Exercice 1")
  scenario.enhance(2)
  println(scenario.image.size)
  println("Exercice 2")
  scenario.enhance(50)
  println(scenario.image.size)
}
