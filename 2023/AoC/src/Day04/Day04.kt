package  Day04

import java.io.File
import kotlin.math.pow

private var currentCardIndex: Int = 0
private lateinit var cards: Array<Int>

fun main() {
  val lines: List<String> = File("src/Day04/input").readLines()
  cards = Array(lines.size) {1}
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

  val tempCardIndex = currentCardIndex + 1
  for (j in tempCardIndex..<tempCardIndex + matches) {
    if (j < cards.size) {
      cards[j] += numberOfCards
    }
  }

  currentCardIndex++

}

private fun numberMatches(str: String): Int {
  val input = str.replace(":", "|").split("|").map{it.trim()}
  val myNumbers = input[1].split(" ").filter { it.isNotEmpty() }
  val winningNumbers = input[2].split(" ").filter { it.isNotEmpty() }

  return myNumbers.count { it in winningNumbers }
}
