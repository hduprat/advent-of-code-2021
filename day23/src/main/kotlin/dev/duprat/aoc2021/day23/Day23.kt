package dev.duprat.aoc2021.day23

import dev.duprat.aoc2021.utils.Scenario

val RoomRegex = Regex("""[\s#]+""")

class Day23Scenario : Scenario {
  val rooms: List<Room> = List(4, { Room() })
  var hallway: MutableList<Amphipod?> = MutableList(11, { null })

  constructor() : super() {
    lines[3].split(RoomRegex).drop(1).dropLast(1).forEachIndexed { index, type ->
      val a = Amphipod(type, index, rooms, hallway)
      rooms[index].add(a)
    }

    lines[2].split(RoomRegex).drop(1).dropLast(1).mapIndexed { index, type ->
      val a = Amphipod(type, index, rooms, hallway)
      rooms[index].add(a)
    }
  }
}

fun main() {
  val scenario = Day23Scenario()
  println("Exercice 1")
  println()
  println("Exercice 2")
  println()
}
