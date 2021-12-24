package dev.duprat.aoc2021.day23

class FullRoomException : Error("Room is full")

class EmptyRoomException : Error("Room is empty")

class Room {
  var top: Amphipod? = null
  var bottom: Amphipod? = null

  fun add(a: Amphipod) {
    when {
      bottom == null -> bottom = a
      top == null -> top = a
      else -> throw FullRoomException()
    }
  }

  fun pop(): Amphipod {
    if (top != null) {
      val returnVal = top!!
      top = null
      return returnVal
    }
    if (bottom != null) {
      val returnVal = bottom!!
      bottom = null
      return returnVal
    }
    throw EmptyRoomException()
  }

  val isEmpty: Boolean
    get() = this.top == null && this.bottom == null

  override fun toString(): String {
    return setOf(top, bottom).filterNotNull().map { it.type }.joinToString(",")
  }
}
