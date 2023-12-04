
import java.io.File

fun main() {
  val lines: List<String> = File("src/Day03/test_input").readLines()
  val resOne = partOne(lines)
  val resTwo = partTwo(lines)
  println("Part 1 result: $resOne")
  println("Part 2 result: $resTwo")
}

private fun partTwo(lines: List<String>): Int {
  val matrix = lines.map { it.toCharArray() }

  var res = 0
  for(i in matrix.indices) {
    for(j in matrix[0].indices) {
      if(matrix[i][j] == '*') {
        val adjNums = adjacentNumbers(matrix, i, j)
        if(adjNums.size > 1) {
          res += adjNums.reduce { acc, index -> acc * index }
        }
      }
    }
  }

  return res
}

private fun adjacentNumbers(matrix: List<CharArray>, row: Int, col: Int): List<Int> {

  val nums = mutableListOf<Int>()

  var i = -1
  while(i <= 1) {
    var j = -1
    while (j <= 1) {
      if(i == 0 && j == 0) {
        j++
      }
      val newRow = row + i
      val newCol = col + j

      if(matrix[newRow][newCol].isDigit()) {
        val number = findNumber(matrix, newRow, newCol)
        nums.add(number.toInt())
        var tempCol = newCol
        while (matrix[newRow][tempCol].isDigit()){
          tempCol++
          if(tempCol >= matrix[0].size) {
            tempCol--
            break
          }
        }

        j += tempCol - newCol
      }
      j++
    }
    i++
  }

  return nums
}

private fun partOne(lines: List<String>): Int {

  val matrix = lines.map { it.toCharArray() }

  val rows = matrix.size
  val cols = matrix[0].size
  var sum = 0

  for(i in 0..<rows) {
    var j = 0
    while(j < cols) {
      if(matrix[i][j].isDigit() && symbolAdjacent(matrix, i, j)){
        val number = findNumber(matrix, i, j)
        sum += number.toInt()

        var tempCol = j

        while (matrix[i][tempCol].isDigit()) {
          tempCol++
          if(tempCol >= cols) {
            tempCol--
            break
          }
        }

        val jump = tempCol - j
        j += jump
      } else {
        j++
      }
    }
  }

  return sum
}

private fun symbolAdjacent(matrix: List<CharArray>, row: Int, col: Int) : Boolean {
  val directions = listOf(-1, 0, 1)
  for(i in directions) {
    for(j in directions) {
      if(i == 0 && j == 0) continue
      val newRow = row + i
      val newCol = col + j

      if(newRow in matrix.indices && newCol in matrix[0].indices) {
        val adjacent = matrix[newRow][newCol]
        if(adjacent != '.' && !adjacent.isDigit()) return true
      }
    }
  }
  return false
}

private fun findNumber(matrix: List<CharArray>, row: Int, col: Int): String {
  var number = ""

  var currentCol = col
  while(currentCol > 0 && matrix[row][currentCol -1].isDigit()) {
    currentCol--
  }

  while(currentCol < matrix[0].size && matrix[row][currentCol].isDigit()) {
    number += matrix[row][currentCol]
    currentCol++
  }
  return number
}
