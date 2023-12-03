import java.io.File

fun main(args: Array<String>) {
  val lines: List<String> = File("src/Day02/input").readLines()
  val resOne = lines.sumOf { line -> partOne(line) }
  val resTwo = lines.sumOf { line -> partTwo(line) }
  println("Part 1 result: $resOne")
  println("Part 2 result: $resTwo")
}

private fun partOne(str: String): Int {
  val colorMax = colorMap(str)

  val parts = str.split(":").map{ it.trim()}
  val game = parts[0].split(" ")[1].toInt()

  if(colorMax["red"]!! > 12 || colorMax["green"]!! > 13 || colorMax["blue"]!! > 14) {
    return 0;
  }

  return game
}

private fun partTwo(str: String): Int {
  val colorMax = colorMap(str)

  return colorMax["red"]!! * colorMax["green"]!! * colorMax["blue"]!!
}

fun colorMap(str: String): Map<String, Int> {
  val map = mutableMapOf<String, Int>("red" to 0, "green" to 0, "blue" to 0)

  val parts = str.split(":").map{ it.trim()}
  parts.removeFirst()

  for(part in parts) {
    val contents = part.split(";").map{it.trim()}
    for(content in contents) {
      val colorAndNum = content.split(",").map {it.trim()}
      for(colorNum in colorAndNum) {
        val colorSplit = colorNum.split(" ")
        val count = colorSplit[0].toInt()
        val color = colorSplit[1]
        map[color] = maxOf(map[color] ?: 0, count)
      }
    }
  }
  return map;
}