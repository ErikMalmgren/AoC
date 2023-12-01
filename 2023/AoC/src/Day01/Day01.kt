import java.io.File
fun main(args: Array<String>) {
  val lines: List<String> = File("src/Day01/input").readLines()
  val lines2: List<String> = File("src/Day01/test_input_2").readLines()

  val resOne = lines.sumOf { line -> calcCalibrate(line) }
  val resTwo = lines.sumOf { line -> calcCalibrateRegex(line) }
  println("Part 1 result: $resOne")
  println("Part 2 result: $resTwo")

}

fun calcCalibrate(str: String): Int {
  val digits = str.filter { it.isDigit()}

  val first = digits.first().toString()
  val last = digits.last().toString()

  return "$first$last".toInt()
}

fun calcCalibrateRegex(str: String): Int {
  val wordMap = mapOf(
    "one" to "1e", "two" to "2o", "three" to "3e", "four" to "4r", "five" to "5e",
    "six" to "6x", "seven" to "7n", "eight" to "8t", "nine" to "9e"
  )
  var str2 = str
  var current = 0
  while(current < str2.length) {
    val matchingKey = wordMap.keys.find {str2.substring(current).startsWith((it))}
    if(matchingKey != null) {
      str2 = str2.replaceFirst(matchingKey, wordMap[matchingKey]!!)
      current += wordMap[matchingKey]!!.length -1
    } else
      current++
  }
  return calcCalibrate((str2))
}