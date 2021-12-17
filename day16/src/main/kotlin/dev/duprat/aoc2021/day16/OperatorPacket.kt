package dev.duprat.aoc2021.day16

class OperatorPacket(bin: String) : Packet(bin) {

  init {
    if (typeID == 4) throw WrongPacketError()
  }

  protected val lengthTypeID: Int = decodeLengthTypeID()
  val subPackets: List<Packet> = decodeSubPackets()
  override val value: Long = decodeValue()

  fun decodeLengthTypeID(): Int {
    return payloadBin.first().digitToInt(2)
  }

  fun getFirstPacketIn(str: String): Packet {
    val packetTypeID = str.slice(3..5).toInt(2)
    if (packetTypeID == 4) return LiteralValuePacket(str)
    return OperatorPacket(str)
  }

  fun decodeSubPackets(): List<Packet> {
    if (lengthTypeID == 0) {
      val list = mutableListOf<Packet>()
      val subPacketsTotalLength = payloadBin.slice(1..15).toInt(2)
      var str = payloadBin.slice(16 until 16 + subPacketsTotalLength)
      rest = payloadBin.drop(16 + subPacketsTotalLength)

      list.add(getFirstPacketIn(str))
      while (list.last().rest.contains('1')) {
        list.add(getFirstPacketIn(list.last().rest))
      }

      return list
    }

    val list = mutableListOf<Packet>()
    val nSubPackets = payloadBin.slice(1..11).toInt(2)
    val str = payloadBin.drop(12)

    val firstPacket = getFirstPacketIn(str)
    list.add(firstPacket)
    for (i in 2..nSubPackets) {
      list.add(getFirstPacketIn(list.last().rest))
    }

    rest = list.last().rest

    return list
  }

  override fun computePacketHierarchy(): Int {
    return version + subPackets.sumOf { it.computePacketHierarchy() }
  }

  override fun decodeValue(): Long {
    return when (typeID) {
      0 -> subPackets.sumOf { it.value }
      1 -> subPackets.fold(1) { product, it -> it.value * product }
      2 -> subPackets.minOf { it.value }
      3 -> subPackets.maxOf { it.value }
      5 -> {
        if (subPackets.size != 2) throw WrongPacketError()
        return if (subPackets[0].value > subPackets[1].value) 1 else 0
      }
      6 -> {
        if (subPackets.size != 2) throw WrongPacketError()
        return if (subPackets[0].value < subPackets[1].value) 1 else 0
      }
      7 -> {
        if (subPackets.size != 2) throw WrongPacketError()
        return if (subPackets[0].value == subPackets[1].value) 1 else 0
      }
      else -> throw WrongPacketError()
    }
  }
}
