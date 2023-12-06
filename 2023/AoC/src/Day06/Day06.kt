
import java.io.File


// Det "smarta" sättet är att kolla vilka två tider som ger rekorddistansen, intervallet mellan går längre
// Detta gick dock fortare att programmera och exekveringstiden är ändå snabb
fun main() {
  val input: String = File("src/Day06/input").readText()
  println("Part 1: " + partOne(input))
  println("Part 2: " + partTwo(input))
  // println("Part 2: " + countWins(56977875, 546192711311139))
}

private fun partTwo(lines: String): Long {

  val line = lines.split("\n")
  val time = line[0].split(" ").drop(1).filter{ it.isNotEmpty() }.joinToString(separator = "")
  val record = line[1].split(" ").drop(1).filter{ it.isNotEmpty() }.joinToString(separator = "")

  return countWins(time.toLong(), record.toLong())
}

private fun partOne(lines: String): Long {
  val line = lines.split("\n")
  val times = line[0].split(" ").filter { it.isNotEmpty() }.drop(1).map { it.toLong() }
  val recordDistances = line[1].split(" ").filter { it.isNotEmpty() }.drop(1).map { it.toLong() }


  val numberRaceWins = mutableListOf<Long>()
  for(i in times.indices){
    val wins = countWins(times[i], recordDistances[i])
    numberRaceWins.add(wins)
  }

  return numberRaceWins.reduce {acc, i -> acc * i}
}

private fun countWins(time: Long, record: Long): Long {

  var res: Long = 0

  for(i in 0..time){
    val distance = i * (time - i)
    if(distance > record) {
      res++
    }
  }

  return res
}