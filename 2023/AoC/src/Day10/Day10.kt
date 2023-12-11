import java.io.File
private lateinit var map: List<CharArray>
fun main() {
  val input: List<CharArray> = File("src/Day10/input").readLines().map { it.toCharArray() }
  map = input.map { CharArray(it.size) { '0' } }
  println("Part 1: " + partOne(input))

  println("Part 2: " + partTwo())
}

fun partTwo(): Int {
  var res = 0
  // Raycasting
  val mapCopy = map

  for(row in map.indices) {
    var intersections = 0
    for(col in map[row].indices) {
      if(map[row][col] != '0') {
        if(checkIntersection(row, col)) {
          intersections++
        }
        continue
      }
      if(intersections % 2 != 0) {
        mapCopy[row][col] = '.'
        res++
      }
    }
  }

  return res
}

fun checkIntersection(row: Int, col: Int): Boolean {
  when(map[row][col]) {
    '|' -> return true
    'F' -> return true
    '7' -> return true
  }

  return false
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

  val firstPipe = currentPipe

  map[start.first][start.second] = matrix[start.first][start.second]
  map[currentPipe.first][currentPipe.second] = matrix[currentPipe.first][currentPipe.second]

  while (nextPipe != start) {

    lastCords = currentPipe
    currentPipe = nextPipe
    map[currentPipe.first][currentPipe.second] = matrix[currentPipe.first][currentPipe.second]
    nextPipe = findConnection(currentPipe, matrix, lastCords)
    res++

  }

  val lastPipe = currentPipe

  calcSignStart(firstPipe, lastPipe, start, matrix)
  return res
}

fun calcSignStart(firstPipe: Pair<Int, Int>, lastPipe: Pair<Int, Int>, start: Pair<Int, Int>, matrix: List<CharArray>) {
  val pipeStart = matrix[firstPipe.first][firstPipe.second]
  val pipeEnd = matrix[lastPipe.first][lastPipe.second]

  // |
  if (pipeStart == '|' && pipeEnd == '|') {
    map[start.first][start.second] = '|'
    return
  }

  // F loopen börjar alltid neråt

  if(lastPipe.first < firstPipe.first && lastPipe.second > firstPipe.second) {
    map[start.first][start.second] = 'F'
    return
  }

  // 7

  if(lastPipe.first < firstPipe.first && lastPipe.second < firstPipe.second) {
    map[start.first][start.second] = '7'
    return
  }

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