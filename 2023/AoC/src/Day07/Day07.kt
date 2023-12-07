import java.io.File

private var ranks = mutableListOf<Pair<String, Int>>()

// Inställd på part 2
fun main() {
  val input: String = File("src/Day07/input").readText()
  println("Part 1: " + partOne(input))
}

private fun partOne(input: String): Long {

  val pairs = input.split("\n")
  pairs.forEach { p ->
    val values = p.split(" ")
    val handBet =  Pair(values[0], values[1].toInt())
    sortPair(handBet)
  }
  var res: Long = 0
  for(i in ranks.indices) {
    res += (i+1) * ranks[i].second
  }
  return res
}

private fun sortPair(p: Pair<String, Int>) {

  for (i in ranks.indices) {
    if (compareRank(p, ranks[i])){
      ranks.add(i, p)
      return
    }
  }
  ranks.add(p)
}

private fun compareRank(p1: Pair<String, Int>, p2: Pair<String, Int>): Boolean {

  val p1Strength = handStrength(p1.first)
  val p2Strength = handStrength(p2.first)

  if (p2Strength > p1Strength) {
    return true
  }

  if (p1Strength > p2Strength) {
    return false
  }

  for (i in 0..p1.first.length){
    if (p1.first[i] != p2.first[i]) {
      return cardToInt(p2.first[i]) > cardToInt(p1.first[i])
    }
  }

  return false
}

private fun handStrength(p: String): Int {
  val freqMap = mutableMapOf<Char, Int>()
  for(char in p) {
    val newFreq = freqMap.getOrDefault(char, 0) + 1
    freqMap[char] = newFreq
  }

  val sortedFreqs = freqMap.entries.sortedByDescending { it.value }
  val maxFreqChar = sortedFreqs.first().key
  var maxFreq = sortedFreqs.first().value
  var nextMaxFreq = 0

  if(sortedFreqs.size > 1) {
    nextMaxFreq = sortedFreqs[1].value
  } else {
    nextMaxFreq = 0;
  }

  if(maxFreqChar == 'J') {
    maxFreq += nextMaxFreq
  } else {
    maxFreq += freqMap.getOrDefault('J', 0)
  }

  if (maxFreq == 5) {
    return 7
  }
  if (maxFreq == 4) {
    return 6
  }
  if(maxFreq == 3 && nextMaxFreq == 2) {
    return 5
  }
  if(maxFreq == 3) {
    return 4
  }
  if(maxFreq == 2 && nextMaxFreq == 2) {
    return 3
  }
  if(maxFreq == 2) {
    return 2
  }
  return 1
}

private fun cardToInt(card: Char): Int {
  return when (card) {
    'A' -> 14
    'K' -> 13
    'Q' -> 12
    'J' -> 1
    'T' -> 10
    else -> card.digitToInt()
  }
}