package dev.duprat.aoc2021.day21

interface Die {
  fun roll(): Int
}

class DeterministicDie : Die {
  var value = 0

  override fun roll(): Int {
    value++
    if (value == 101) value = 1
    return value
  }
}

class Game {
  var pos1: Int
  var pos2: Int
  var score1: Int
  var score2: Int

  var isPlayer1: Boolean = true

  var rolls: Int = 0
  var total: Int = 0

  val positions: Pair<Int, Int>
    get() = Pair(pos1, pos2)

  val scores: Pair<Int, Int>
    get() = Pair(score1, score2)

  val winningScore: Int

  constructor(
      startingPositions: Pair<Int, Int>,
      startingScores: Pair<Int, Int> = Pair(0, 0),
      maxScore: Int = 1000,
      currentPlayer: Int = 1
  ) : super() {
    pos1 = startingPositions.first
    pos2 = startingPositions.second

    score1 = startingScores.first
    score2 = startingScores.second

    winningScore = maxScore
    isPlayer1 = currentPlayer == 1
  }

  fun loop10(n: Int): Int {
    val lastDigit = n % 10
    return if (lastDigit == 0) 10 else lastDigit
  }

  fun roll(result: Int) {
    total += result
    rolls++

    if (rolls % 3 != 0) return

    if (isPlayer1) {
      pos1 = loop10(pos1 + total)
      score1 += pos1
    } else {
      pos2 = loop10(pos2 + total)
      score2 += pos2
    }

    total = 0
    isPlayer1 = !isPlayer1
  }

  val loserScore: Int
    get() = Math.min(score1, score2)

  val hasEnded: Boolean
    get() = this.score1 >= winningScore || this.score2 >= winningScore

  val winner: Int
    get() = if (this.score1 >= winningScore) 1 else 2

  fun clone(): Game {
    return Game(
        startingPositions = Pair(pos1, pos2),
        startingScores = Pair(score1, score2),
        maxScore = winningScore,
        currentPlayer = if (isPlayer1) 1 else 2
    )
  }
}
