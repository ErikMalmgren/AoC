package Day05

import java.io.File

fun main() {
  val input: String = File("src/Day05/input").readText()
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
  val seedsToSoilMap = createMap(seedsToSoil)
  val soilToFertilizerMap = createMap(soilToFertilizer)
  val fertilizerToWaterMap = createMap(fertilizerToWater)
  val waterToLightMap = createMap(waterToLight)
  val lightToTemperatureMap = createMap(lightToTemperature)
  val temperatureToHumidityMap = createMap(temperatureToHumidity)
  val humidityToLocationMap = createMap(humidityToLocation)

  seeds.forEach { seed ->
    val dest1 = calcDestination(seedsToSoilMap, seed)
    val dest2 = calcDestination(soilToFertilizerMap, dest1)
    val dest3 = calcDestination(fertilizerToWaterMap, dest2)
    val dest4 = calcDestination(waterToLightMap, dest3)
    val dest5 = calcDestination(lightToTemperatureMap, dest4)
    val dest6 = calcDestination(temperatureToHumidityMap, dest5)
    locations.add(calcDestination(humidityToLocationMap, dest6))
  }


  // Del 2, jag tror att en bättre lösning är att kolla locations nerifrån tills man hittar en som kopplar till ett seed
  var minValue = Long.MAX_VALUE
  var i = 0
  while (i < seeds.size){
    var currentSeed = seeds[i]
    val currentRange = seeds[i + 1]

    for(j in 0..currentRange) {
      val dest1 = calcDestination(seedsToSoilMap, currentSeed)
      val dest2 = calcDestination(soilToFertilizerMap, dest1)
      val dest3 = calcDestination(fertilizerToWaterMap, dest2)
      val dest4 = calcDestination(waterToLightMap, dest3)
      val dest5 = calcDestination(lightToTemperatureMap, dest4)
      val dest6 = calcDestination(temperatureToHumidityMap, dest5)
      val dest7 = calcDestination(humidityToLocationMap, dest6)
      if(dest7 < minValue) {
        minValue = dest7
        println(currentSeed)
      }

      currentSeed++
    }

    i += 2
  }

  println("Part 1 result: " + locations.min())
  println("Part 2 result: " + minValue)
}

private fun createMap(map: List<String>): Map<Long, Pair<Long, Long>> {
  return map.map { str ->
    val parts = str.split(" ").map { it.toLong() }
    val source = parts[1]
    val destination = parts[0]
    val range = parts[2]
    source to (destination to range)
  }.toMap()
}

private fun calcDestination(map: Map<Long, Pair<Long, Long>>, seed: Long): Long {
  map.forEach { (source, pair) ->
    val (destination, range) = pair
    if (seed in source until (source + range)) {
      return destination + (seed - source)
    }
  }
  return seed
}

private fun calcDestinationOld(map: List<String>, seed: Long): Long {

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