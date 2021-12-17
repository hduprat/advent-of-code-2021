package dev.duprat.aoc2021.day16

open class LiteralValuePacket(bin: String) : Packet(bin) {
  init {
    if (typeID != 4) throw WrongPacketError()
  }
  override val value: Long = decodeValue()

  override fun decodeValue(): Long {
    val chunks = payloadBin.chunked(5)
    val lastValidChunk = chunks.indexOfFirst { it[0] == '0' }
    if (lastValidChunk == -1) throw WrongPacketError()

    if (lastValidChunk < chunks.size - 1) {
      rest = chunks.drop(lastValidChunk + 1).joinToString("")
    }

    return chunks.slice(0..lastValidChunk).map { it.drop(1) }.joinToString("").toLong(2)
  }

  override fun computePacketHierarchy(): Int {
    return version
  }
}
