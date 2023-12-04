package  Day04

import java.io.File
import kotlin.math.pow

private var currentCardIndex: Int = 0
private val cards = Array(198) {1}

fun main() {
  val lines: List<String> = File("src/Day04/input").readLines()
  val resOne = lines.sumOf{ line -> partOne(line) }
  lines.forEach { line -> partTwo(line) }
  val resTwo = cards.sum()
  println("Part 1 result: $resOne")
  println("Part 2 result: $resTwo")
}


private fun partOne(str: String): Double {

  val matches = numberMatches(str)

  if(matches == 0) {
    return 0.0
  }
  return 2.toDouble().pow(matches-1)
}

private fun partTwo(str: String) {

  val numberOfCards = cards[currentCardIndex]
  val matches = numberMatches(str)

  for(i in 0 until numberOfCards) {
    var tempCardIndex = currentCardIndex + 1
    for(j in 1..matches) {
      cards[tempCardIndex]++
      tempCardIndex++
    }
  }
  currentCardIndex++

}

private fun numberMatches(str: String): Int {
  val parts = str.split("|").map { it.trim() }
  val myNumbers = parts[1].split(" ").filter { it.isNotEmpty() }
  val parts2 = parts[0].split(":").map { it.trim() }
  val winningNumbers = parts2[1].split(" ").filter { it.isNotEmpty() }

  return myNumbers.count { it in winningNumbers }
}
