import java.io.File

fun main() {
  val input: String = File("src/Day09/input").readText()
  println("Part 1: " + partOne(input))
  println("Part 2: " + partTwo(input))
}

private fun partTwo(input: String): Int {
  val lines = input.split("\n")
  val nextValues = mutableListOf<Int>()
  lines.forEach { line ->
    val value = calcNextValue(line.split(" ").map { it.toInt() }.reversed())
    nextValues.add(value)
  }
  return nextValues.sum()
}


private fun partOne(input: String): Int {

  val lines = input.split("\n")
  val nextValues = mutableListOf<Int>()
  lines.forEach { line ->
    val value = calcNextValue(line.split(" ").map { it.toInt() })
    nextValues.add(value)
  }
  return nextValues.sum()
}

private fun calcNextValue(line: List<Int>): Int {
  val differences = line.windowed(2, 1).map { it[1] - it[0] }

  return if(differences.all { it == 0}) {
    line.last() + differences.last()
  } else {
    calcNextValue(differences) + line.last()
  }
}
