package silverstar.scalafortheimpatient.collections.arrays

import scala.collection.mutable.ArrayBuffer

object ArrayAlgorithms {

  val num1   = Array(4, 55, 6, 32, 78, 98, 1, 44)
  val num2   = Array(4, 55, 6, 32, 78, 98, 1, 44)
  val num3   = ArrayBuffer(4, 55, 6, 32, 78, 98, 1, 44)
  val string = ArrayBuffer("Mary", "had", "a", "little", "lamb")

  def sum(a: Array[Int]): Int = {
    a.sum
  }

  // kind = true -> maximum
  def extremum(s: ArrayBuffer[String], kind: Boolean): String = {
    if (kind) s.foldLeft("")((acc, ss) => if (ss >= acc) ss else acc)
    else s.foldLeft("")((acc, ss) => if (ss <= acc) ss else acc)
  }

  // direction = true -> ascending
  def sortArray(a: Array[Int], direction: Boolean): Array[Int] = {
    if (direction) a.sorted
    else a.sortWith(_ > _)
  }

  // direction = true -> ascending
  def sortArrayBuffer(a: ArrayBuffer[Int], direction: Boolean): ArrayBuffer[Int] = {
    if (direction) a.sorted
    else a.sortWith(_ > _)
  }

  def sortInPlace(a: Array[Int]): Unit = {
    scala.util.Sorting.quickSort(a)
    println(a.toString)
  }

  def printlnArray(a: Array[Int], version: Int): Unit = {
    if (version == 0) {
      println(a.mkString(" and "))
    } else if (version == 1) {
      println(a.mkString(";"))
    } else if (version == 2) {
      println(a.mkString("[", ";", "]"))
    } else {
      println(a.toString)
    }
  }
}
