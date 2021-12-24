package dev.duprat.aoc2021.day23

class UnknownAmphipodException(type: String) :
    Exception("There cannot be an amphipod of type $type")

class IllicitMoveException(amphipod: Amphipod) :
    Exception("Illicit move from amphipod ${amphipod.type}")

class Amphipod {
  val type: String
  val energyPerStep: Int
  val targetRoom: Int
  val rooms: List<Room>
  val hallway: MutableList<Amphipod?>

  var hasAlreadyMoved: Boolean = false
  var hallwayPlace: Int? = null
  var currentRoom: Int?
  var energySpent: Int = 0

  constructor(type: String, roomIndex: Int, rooms: List<Room>, hallway: MutableList<Amphipod?>) {
    this.type = type
    energyPerStep =
        when (type) {
          "A" -> 1
          "B" -> 10
          "C" -> 100
          "D" -> 1000
          else -> throw UnknownAmphipodException(type)
        }

    targetRoom =
        when (type) {
          "A" -> 0
          "B" -> 1
          "C" -> 2
          "D" -> 3
          else -> throw UnknownAmphipodException(type)
        }

    this.rooms = rooms
    this.currentRoom = roomIndex
    this.hallway = hallway
  }

  fun moveToHallway(place: Int) {
    if (setOf(2, 4, 6, 8).contains(place)) throw IllicitMoveException(this)
    if (hallwayPlace != null) throw IllicitMoveException(this)
    if (currentRoom == null) throw IllicitMoveException(this)
    val roomIndex = currentRoom!!
    val room = rooms[roomIndex]
    if (room.bottom == this && room.top != null) throw IllicitMoveException(this)

    var moveEnergy = 0
    moveEnergy += if (this == room.bottom) 2 * energyPerStep else energyPerStep
    if (hallway
            .slice(Math.min(place, (roomIndex + 1) * 2)..Math.max(place, (roomIndex + 1) * 2))
            .filterNotNull()
            .size != 0
    )
        throw IllicitMoveException(this)

    hallwayPlace = place
    hallway[place] = this
    room.pop()
  }
}
