package silverstar.adventofcode2016.day3

/**
  * --- Day 3: Squares With Three Sides ---
  * Now that you can think clearly, you move deeper into the labyrinth of hallways and office furniture that makes up this part of Easter Bunny HQ.
  * This must be a graphic design department; the walls are covered in specifications for triangles.
  * Or are they?
  * The design document gives the side lengths of each triangle it describes, but... 5 10 25? Some of these aren't triangles. You can't help but mark the
  * impossible ones.
  * In a valid triangle, the sum of any two sides must be larger than the remaining side. For example, the "triangle" given above is impossible,
  * because 5 + 10 is not larger than 25.
  * In your puzzle input, how many of the listed triangles are possible?
  *
  * --- Part Two ---
  * Now that you've helpfully marked up their design documents, it occurs to you that triangles are specified in groups of three vertically.
  * Each set of three numbers in a column specifies a triangle. Rows are unrelated.
  * For example, given the following specification, numbers with the same hundreds digit would be part of the same triangle:
  * 101 301 501
  * 102 302 502
  * 103 303 503
  * 201 401 601
  * 202 402 602
  * 203 403 603
  * In your puzzle input, and instead reading by columns, how many of the listed triangles are possible?
  */
object SquaresWithThreeSides {

  def howManyTriangles(input: String): Int = {
    val tuples: List[(Int, Int, Int)] = input.trim
      .split("\\D+")
      .grouped(3)
      .map {
        case Array(a, b, c) => (a.toInt, b.toInt, c.toInt)
      }
      .toList
//    val tuples = input.split("\r\n").map(_.trim.split("\\s+")).map(toTuple).toList
    tuples.count(isTriangle)
  }

  def howManyTrianglesVertical(input: String): Int = {
    val withIndex = input.trim.split("\\D+").zipWithIndex
    //    val col1 = withIndex.filter(_._2 % 3 == 0).map(_._1).grouped(3).map(toTuple).toList
    (0 to 2)
      .foldLeft(List.empty[(Int, Int, Int)])((acc, i) =>
        acc ++ withIndex.filter(_._2 % 3 == i).map(_._1).grouped(3).map(toTuple).toList)
      .count(isTriangle)
  }

  def toTuple(array: Array[String]): (Int, Int, Int) = array match {
    case Array(a, b, c) => (a.toInt, b.toInt, c.toInt)
  }

  def isTriangle(possibleTriangle: (Int, Int, Int)): Boolean = {
    val (a, b, c) = possibleTriangle
    (a + b > c) && (b + c > a) && (a + c > b)
  }
}
