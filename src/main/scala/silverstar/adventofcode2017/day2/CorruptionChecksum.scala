package silverstar.adventofcode2017.day2

import scala.annotation.tailrec
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
  *
  * --- Part Two ---
  * "Based on what we're seeing, it looks like all the User wanted is some information about the evenly divisible
  * values in the spreadsheet. Unfortunately, none of us are equipped for that kind of calculation - most of us
  * specialize in bitwise operations."
  * *
  * It sounds like the goal is to find the only two numbers in each row where one evenly divides the other - that is,
  * where the result of the division operation is a whole number. They would like you to find those numbers
  * on each line, divide them, and add up each line's result.
  * *
  * For example, given the following spreadsheet:
  * 5 9 2 8
  * 9 4 7 3
  * 3 8 6 5
  * In the first row, the only two numbers that evenly divide are 8 and 2; the result of this division is 4.
  * In the second row, the two numbers are 9 and 3; the result is 3.
  * In the third row, the result is 2.
  * In this example, the sum of the results would be 4 + 3 + 2 = 9.
  */
object CorruptionChecksum {

  def calculateChecksum(input: String): Long = {
    val lines: Array[String] = input.split(Properties.lineSeparator)
    val spreadsheet: Array[Array[Int]] = lines.map(_.split("\\W").map(_.toInt))
    spreadsheet.foldLeft(0)((acc, row) => {
      acc + (row.max - row.min)
    })
  }

  def calculateNewChecksum1(input: String): Long = {

    def combinations(a: List[Int]): List[(Int, Int)] = {
      @tailrec
      def iter(aa: List[Int], acc: List[(Int, Int)]): List[(Int, Int)] = aa match {
        case aa1 :: aa2 :: tail =>
          val rest = aa2 :: tail
          val v: List[(Int, Int)] = rest.map(r => (aa1, r))
          iter(rest, acc ++ v)
        case _ => acc
      }

      iter(a.sorted.reverse, List())
    }

    val lines: Array[String] = input.split(Properties.lineSeparator)
    val spreadsheet: Array[Array[Int]] = lines.map(_.split("\\W").map(_.toInt))
    spreadsheet.foldLeft(0)((acc, row) => {
      val c = combinations(row.toList)
      val evenDivides = c.collectFirst {
        case pair if pair._1 % pair._2 == 0 => pair._1 / pair._2
      }.getOrElse(0)
      acc + evenDivides
    })
  }

  def calculateNewChecksum2(input: String): Int = {

    def findFirstEvenlyDivisible(a: List[Int]): Int = a match {
        case a1 :: a2 :: t =>
          val tail = a2 :: t
          tail.collectFirst {
            case r: Int if a1 % r == 0 => a1 / r
          }.getOrElse(findFirstEvenlyDivisible(tail))
        case _ => 0
      }

    val lines: Array[String] = input.split(Properties.lineSeparator)
    val spreadsheet: Array[Array[Int]] = lines.map(_.split("\\W").map(_.toInt))
    spreadsheet.foldLeft(0)((acc, row) => {
      acc + findFirstEvenlyDivisible(row.toList.sorted.reverse)
    })
  }
}
