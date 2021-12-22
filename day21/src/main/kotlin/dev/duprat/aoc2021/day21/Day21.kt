package dev.duprat.aoc2021.day21

import dev.duprat.aoc2021.utils.Scenario

fun multiplyTermByTerm(a: List<Long>, b: List<Long>): Long {
  var result: Long = 0
  for (i in a.indices) {
    result += a[i] * b[i]
  }

  return result
}

class Day21Scenario : Scenario {
  val pos1: Int
  val pos2: Int

  constructor() : super() {
    pos1 = lines[0].takeLastWhile { it != ' ' }.toInt()
    pos2 = lines[1].takeLastWhile { it != ' ' }.toInt()
  }

  fun playDeterministicGame(): Game {
    val game = Game(Pair(pos1, pos2))
    val die = DeterministicDie()
    do {
      val result = die.roll()
      game.roll(result)
    } while (!game.hasEnded)

    return game
  }

  fun playQuantumGame(game: Game, roll3Result: Int? = null): Pair<Long, Long> {
    if (roll3Result != null) {
      game.roll(1)
      game.roll(1)
      game.roll(roll3Result - 2)
      if (game.hasEnded) {
        return if (game.winner == 1) Pair(1, 0) else Pair(0, 1)
      }
    }

    val scores = (3..9).map { playQuantumGame(game.clone(), it) }

    val player1Scores = scores.map { it.first }
    val player2Scores = scores.map { it.second }

    val cardinals = listOf<Long>(1, 3, 6, 7, 6, 3, 1)

    return Pair(
        multiplyTermByTerm(player1Scores, cardinals),
        multiplyTermByTerm(player2Scores, cardinals)
    )
  }
}

fun main() {
  println("Exercice 1")
  val scenario = Day21Scenario()
  val endgame = scenario.playDeterministicGame()
  println(endgame.rolls * endgame.loserScore)
  println("Exercice 2")
  val gamesWon = scenario.playQuantumGame(Game(Pair(scenario.pos1, scenario.pos2), maxScore = 21))
  println(Math.max(gamesWon.first, gamesWon.second))
}
