package silverstar.scalafortheimpatient.collections.arrays

import scala.collection.mutable.ArrayBuffer

object ArrayAlgorithms {

  val num1 = Array(4, 55, 6, 32, 78, 98, 1, 44)
  val num2 = Array(4, 55, 6, 32, 78, 98, 1, 44)
  val num3 = ArrayBuffer(4, 55, 6, 32, 78, 98, 1, 44)
  val string = ArrayBuffer("Mary", "had", "a", "little", "lamb")

  def sum(a: Array[Int]): Int = {
    a.sum
  }

  // kind = true -> maximum
  def extremum(s: ArrayBuffer[String], kind: Boolean = true): String = {
    if (kind) s.max
    else s.min
  }

  // direction = true -> ascending
  def sortArray(a: Array[Int], direction: Boolean = true): Array[Int] = {
    if (direction) a.sorted
    else a.sortWith(_ > _)
  }
  
    // direction = true -> ascending
  def sortArrayBuffer(a: ArrayBuffer[Int], direction: Boolean = true): ArrayBuffer[Int] = {
    if (direction) a.sorted
    else a.sortWith(_ > _)
  }

  def sortInPlace(a: Array[Int]) {
    scala.util.Sorting.quickSort(a)
    println(a.toString)
  }
  
  def printlnArray(a : Array[Int], version : Int = 0) {
    if(version == 0) {
      println(a.mkString(" and "))
    } else if(version == 1) {
      println(a.mkString(";"))
    } else if(version == 2) {
      println(a.mkString("[", ";", "]"))
    } else {
      println(a.toString)
    }
  }
}