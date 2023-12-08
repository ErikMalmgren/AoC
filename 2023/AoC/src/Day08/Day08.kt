import java.io.File

fun main() {
  val input: String = File("src/Day08/input").readText()
  println("Part 1: " + partOne(input))
  println("Part 2: " + partTwo(input))
}

private fun partTwo(input: String): Long {
  val parts = input.split("\n\n")
  val instructions = parts[0]
  val nodes = parts[1]
  val map = createMap(nodes)

  val startLocations = map.keys.filter { it[2] == 'A' }.toMutableList()
  val exits = mutableListOf<Long>()

  startLocations.forEach { location ->
    var counter: Long = 0
    var localLocation = location
    while (localLocation[2] != 'Z') {
      val directions = map[localLocation]
      val index = counter % instructions.length
      if (directions != null) {
        localLocation = if (instructions[index.toInt()] == 'L') directions.first else directions.second
      }
      counter++
    }
    exits.add(counter)
  }

  return exits.reduce {acc, num -> lcm(acc, num)}
}

private fun gcd(a: Long, b: Long): Long {
  if(b.toInt() == 0) return a
  return gcd(b, a% b)
}

private fun lcm(a: Long, b: Long): Long {
  return a/ gcd(a, b) * b
}


private fun partOne(input: String): Int {
  val parts = input.split("\n\n")
  val instructions = parts[0]
  val nodes = parts[1]
  val map = createMap(nodes)

  var currentLocation = "AAA"
  var counter = 0
  while (currentLocation != "ZZZ"){
    val directions = map[currentLocation]
    val index = counter % instructions.length
    if (directions != null) {
      currentLocation = if (instructions[index] == 'L') directions.first else directions.second
    }
    counter++
  }

  return counter
}

private fun createMap(nodes: String): Map<String, Pair<String,String>> {
  val lines = nodes.split("\n")
  val res = mutableMapOf<String, Pair<String, String>>()
  lines.forEach { line ->
    val parts = line.split(" = ", ", ", "(", ")").filter { it.isNotEmpty() }
    res[parts[0]] = Pair(parts[1], parts[2])
  }
  return res
}