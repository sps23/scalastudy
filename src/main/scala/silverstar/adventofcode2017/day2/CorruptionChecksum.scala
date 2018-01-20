package silverstar.adventofcode2017.day2

import scala.util.Properties

/**
  * --- Day 2: Corruption Checksum ---
  * As you walk through the door, a glowing humanoid shape yells in your direction. "You there! Your state appears
  * to be idle. Come help us repair the corruption in this spreadsheet - if we take another millisecond,
  * we'll have to display an hourglass cursor!"
  * *
  * The spreadsheet consists of rows of apparently-random numbers. To make sure the recovery process is on the
  * right track, they need you to calculate the spreadsheet's checksum. For each row, determine the difference between
  * the largest value and the smallest value; the checksum is the sum of all of these differences.
  * *
  * For example, given the following spreadsheet:
  * 5 1 9 5
  * 7 5 3
  * 2 4 6 8
  * The first row's largest and smallest values are 9 and 1, and their difference is 8.
  * The second row's largest and smallest values are 7 and 3, and their difference is 4.
  * The third row's difference is 6.
  * In this example, the spreadsheet's checksum would be 8 + 4 + 6 = 18.
  */
object CorruptionChecksum {

  def calculateChecksum(input: String): Long = {
    val lines: Array[String] = input.split(Properties.lineSeparator)
    val spreadsheet: Array[Array[Int]] = lines.map(_.split("\\W").map(_.toInt))
    spreadsheet.foldLeft(0)((acc, row) => {
      acc + (row.max - row.min)
    })
  }
}
