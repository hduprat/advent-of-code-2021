package dev.duprat.aoc2021.utils

fun bin2hex(bin: String): String {
  return bin.chunked(4)
      .map {
        val paddedIt = if (it.length < 4) it.padEnd(4, '0') else it
        when (paddedIt) {
          "0000" -> '0'
          "0001" -> '1'
          "0010" -> '2'
          "0011" -> '3'
          "0100" -> '4'
          "0101" -> '5'
          "0110" -> '6'
          "0111" -> '7'
          "1000" -> '8'
          "1001" -> '9'
          "1010" -> 'A'
          "1011" -> 'B'
          "1100" -> 'C'
          "1101" -> 'D'
          "1110" -> 'E'
          "1111" -> 'F'
          else -> throw Error()
        }
      }
      .joinToString("")
}

fun hex2bin(hex: String): String {
  return hex
      .map {
        when (it) {
          '0' -> "0000"
          '1' -> "0001"
          '2' -> "0010"
          '3' -> "0011"
          '4' -> "0100"
          '5' -> "0101"
          '6' -> "0110"
          '7' -> "0111"
          '8' -> "1000"
          '9' -> "1001"
          'A' -> "1010"
          'B' -> "1011"
          'C' -> "1100"
          'D' -> "1101"
          'E' -> "1110"
          'F' -> "1111"
          else -> throw Error()
        }
      }
      .joinToString("")
}
