package Day05

import java.io.File

fun main() {
  val input: String = File("src/Day05/test_input").readText()
  solution(input)
}

private fun solution(lines: String) {
  val splitLines = lines.split("\n\n")

  val seeds = splitLines[0].split(":").map { it.trim() }.removeLast().split(" ").map {it.trim()}.map { it.toLong() }
  val seedsToSoil = splitLines[1].split(":", "\n").map { it.trim() }.filter { it.isNotEmpty() }.drop(1)
  val soilToFertilizer = splitLines[2].split(":", "\n").map { it.trim() }.filter { it.isNotEmpty() }.drop(1)
  val fertilizerToWater = splitLines[3].split(":", "\n").map { it.trim() }.filter { it.isNotEmpty() }.drop(1)
  val waterToLight = splitLines[4].split(":", "\n").map { it.trim() }.filter { it.isNotEmpty() }.drop(1)
  val lightToTemperature = splitLines[5].split(":", "\n").map { it.trim() }.filter { it.isNotEmpty() }.drop(1)
  val temperatureToHumidity = splitLines[6].split(":", "\n").map { it.trim() }.filter { it.isNotEmpty() }.drop(1)
  val humidityToLocation = splitLines[7].split(":", "\n").map { it.trim() }.filter { it.isNotEmpty() }.drop(1)

  val locations = mutableListOf<Long>()

  seeds.forEach { seed ->
    val dest1 = calcDestination(seedsToSoil, seed)
    val dest2 = calcDestination(soilToFertilizer, dest1)
    val dest3 = calcDestination(fertilizerToWater, dest2)
    val dest4 = calcDestination(waterToLight, dest3)
    val dest5 = calcDestination(lightToTemperature, dest4)
    val dest6 = calcDestination(temperatureToHumidity, dest5)
    locations.add(calcDestination(humidityToLocation, dest6))
  }

  val locations2 = mutableListOf<Long>()
  var i = 0
  while (i < seeds.size){
    var currentSeed = seeds[i]
    val currentRange = seeds[i + 1]

    for(j in 0..currentRange) {
      val dest1 = calcDestination(seedsToSoil, currentSeed)
      val dest2 = calcDestination(soilToFertilizer, dest1)
      val dest3 = calcDestination(fertilizerToWater, dest2)
      val dest4 = calcDestination(waterToLight, dest3)
      val dest5 = calcDestination(lightToTemperature, dest4)
      val dest6 = calcDestination(temperatureToHumidity, dest5)
      val dest7 = calcDestination(humidityToLocation, dest6)
      locations2.add(dest7)
      currentSeed++
    }

    i += 2
  }

  println("Part 1 result: " + locations.min())
  println("Part 2 result: " + locations2.min())
}

private fun calcDestination(map: List<String>, seed: Long): Long {

  for(str in map){
    val parts = str.split(" ").map { it.trim() }.map { it.toLong() }
    val destination = parts[0]
    val source = parts[1]
    val range = parts[2]

    if(seed < source + range && seed >= source) {
      return destination + (seed - source)
    }
  }
  return seed
}