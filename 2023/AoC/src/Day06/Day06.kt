import java.io.File
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
  val startTime = System.nanoTime()
  repeat(1000) {
    val input: String = File("src/Day06/input").readText()
    println("Part 1: " + partOne(input))
    println("Part 2: " + partTwo(input))
  }

  val currentTime = System.nanoTime()
  println("Snittid (ns): " + (currentTime - startTime) / 1000)

}

private fun partTwo(lines: String): Long {

  val line = lines.split("\n")
  val time = line[0].split(" ").drop(1).filter { it.isNotEmpty() }.joinToString(separator = "")
  val record = line[1].split(" ").drop(1).filter { it.isNotEmpty() }.joinToString(separator = "")

  return countWins(time.toDouble(), record.toDouble())
}

private fun partOne(lines: String): Long {
  val line = lines.split("\n")
  val times = line[0].split(" ").filter { it.isNotEmpty() }.drop(1).map { it.toDouble() }
  val recordDistances = line[1].split(" ").filter { it.isNotEmpty() }.drop(1).map { it.toDouble() }

  val numberRaceWins = mutableListOf<Long>()
  for (i in times.indices) {
    val wins = countWins(times[i], recordDistances[i])
    numberRaceWins.add(wins)
  }

  return numberRaceWins.reduce { acc, i -> acc * i }
}

private fun countWins(time: Double, record: Double): Long {

  val rootTerm = sqrt((time / 2).pow(2) - record)
  val t1 = ceil(time / 2 + rootTerm)
  val t2 = floor(time / 2 - rootTerm)

  return (t1 - t2).toLong() - 1
}