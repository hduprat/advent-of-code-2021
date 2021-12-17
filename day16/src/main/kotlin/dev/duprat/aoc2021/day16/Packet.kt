package dev.duprat.aoc2021.day16

class WrongPacketError : Error() {}

abstract class Packet(binary: String) {
  val bin: String = binary
  protected val payloadBin: String = decodePayload()
  val version: Int = decodeVersion()
  protected val typeID: Int = decodeTypeID()
  var rest = ""
  abstract val value: Long

  private fun decodePayload(): String {
    return bin.drop(6)
  }

  private fun decodeVersion(): Int {
    return bin.slice(0..2).toInt(2)
  }

  fun decodeTypeID(): Int {
    return bin.slice(3..5).toInt(2)
  }

  abstract fun computePacketHierarchy(): Int

  abstract fun decodeValue(): Long
}
