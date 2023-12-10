import java.io.File

fun main() {
  val input: List<CharArray> = File("src/Day10/input").readLines().map { it.toCharArray() }
  println("Part 1: " + partOne(input))
}

fun partOne(matrix: List<CharArray>): Int {

  val startCords = findStart(matrix)
  return countLoop(matrix, startCords) / 2
}

fun findStart(matrix: List<CharArray>): Pair<Int, Int> {
  for((rowIndex, row) in matrix.withIndex()) {
    for((colIndex, value) in row.withIndex()) {
      if(value == 'S') {
        return Pair(rowIndex, colIndex)
      }
    }
  }
  return Pair(0, 0)
}

fun countLoop(matrix: List<CharArray>, start: Pair<Int, Int>): Int {
  var res = 2

  var lastCords = start
  var currentPipe = findConnection(start, matrix, lastCords)
  var nextPipe = findConnection(currentPipe, matrix, lastCords)

  while (nextPipe != start) {

    lastCords = currentPipe
    currentPipe = nextPipe
    nextPipe = findConnection(currentPipe, matrix, lastCords)
    res++

  }


  return res
}

fun findConnection(cords: Pair<Int, Int>, matrix: List<CharArray>, lastCords: Pair<Int, Int> ): Pair<Int, Int> {
  val row = cords.first
  val col = cords.second
  val currentPipe = matrix[row][col]
  val prevRow = lastCords.first
  val prevCol = lastCords.second

  when(currentPipe) {
    'S' -> {
      // Upp
      if(row -1 >= 0) {
        val currentChar = matrix[row - 1][col]
        when(currentChar) {
          '|', '7', 'F' -> return Pair(row -1, col)
        }
      }
      // Ner

      if(row + 1 < matrix.size) {
        val currentChar = matrix[row + 1][col]
        when (currentChar) {
          '|', 'L', 'J' -> return Pair(row + 1, col)
        }
      }
      // Vänster
      if(col - 1 >= 0) {
        val currentChar = matrix[row][col -1]
        when (currentChar) {
          '-', 'L', 'F' -> return Pair(row, col -1)
        }
      }
      // Höger
      if(col +1 < matrix.size) {
        val currentChar = matrix[row][col + 1]
        when (currentChar) {
          '-', 'J', '7' -> return Pair(row, col + 1)
        }
      }

    }
    '|' -> {
      return if(row > prevRow) {
        // Ner
        Pair (row + 1, col)
      } else {
        // Upp
        Pair(row -1, col)
      }

    }
    '-' -> {
      return if(col > prevCol) {
        // Höger
        Pair(row, col + 1)
      } else {
        // Vänster
        Pair(row, col - 1)
      }
    }
    'L' -> {
      // Kom från höger ska upp
      if(prevCol > col) {
        return Pair(row - 1, col)
      } else if(prevRow < row) {
        // Kom uppifrån ska höger
        return Pair(row, col +1)
      }
    }
    'J' -> {
      if(prevCol < col) {
        return Pair(row -1, col)
      } else if(prevRow < row) {
        return Pair(row, col -1)
      }

    }
    '7' -> {
      if(prevCol < col) {
        return Pair(row +1, col)
      } else if(prevRow > row) {
        return Pair(row, col -1)
      }
    }
    'F' -> {
      if(prevCol > col) {
        return Pair(row + 1, col)
      } else if(prevRow > row) {
        return Pair(row, col + 1)
      }
    }
  }

  return Pair(0,0)
}