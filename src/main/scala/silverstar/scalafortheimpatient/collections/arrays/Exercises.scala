package silverstar.scalafortheimpatient.collections.arrays

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

object Exercises {

  //returns an array of n random integers from <0,n)
  def randomArray(n: Int): Array[Int] = {
    val result = ArrayBuffer[Int]()
    for (i <- 0 until n) {
      result += Random.nextInt(n)
    }
    result.toArray
  }

  //swaps adjacent elements of an array in place iterative way
  def swapAdjInPlaceIter(a: Array[Int]) {
    for (i <- 0 until a.length - 1 by 2) {
      val tmp = a(i)
      a(i) = a(i + 1)
      a(i + 1) = tmp
    }
    println("a = " + a.mkString("[", ";", "]"))
  }

  //swaps adjacent elements of an array in place recursive way
  def swapAdjInPlaceRec(a: Array[Int], startIndex: Int = 0) {
    if (a.length - startIndex > 1) {
      val tmp = a(startIndex)
      a(startIndex) = a(startIndex + 1)
      a(startIndex + 1) = tmp
      swapAdjInPlaceRec(a, startIndex + 2)
    } else {
      println("a = " + a.mkString("[", ";", "]"))
    }
  }

  //swaps adjacent elements of an array iterative way, creates new array
  def swapAdjIter(a: Array[Int]): ArrayBuffer[Int] = {
    val swaped = for (i <- 0 until a.length) yield {
      if (i == a.length - 1 && i % 2 == 0) {
        a(i)
      } else {
        if (i % 2 == 0) a(i + 1)
        else a(i - 1)
      }
    }
    val result = new ArrayBuffer[Int]()
    result ++= swaped
  }

  //swaps adjacent elements of an array recursive way, creates new array
  def swapAdjRec(a: Array[Int]): ArrayBuffer[Int] = {
    if (a.length == 0) {
      ArrayBuffer[Int]()
    } else if (a.length == 1) {
      ArrayBuffer[Int](a(0))
    } else {
      ArrayBuffer[Int](a(1), a(0)) ++= swapAdjRec(a.tail.tail)
    }
  }

  // returns new array that contains all positive values of original array in their original order,
  // followed by all zero and negative numbers in their original order 
  def separetaPosNegIter(a: Array[Int]): ArrayBuffer[Int] = {
    val positive = new ArrayBuffer[Int]
    val negative = new ArrayBuffer[Int]
    for (i <- a) {
      if (i > 0) positive += i
      else negative += i
    }
    val result = new ArrayBuffer[Int]()
    result ++= positive
    result ++= negative
    result
  }

  def avg(a: Array[Double]): Double = {
    a.sum / a.length
  }

  def withoutDuplicates(a: Array[Int]): Array[Int] = {
    a.distinct
  }

  def getTimeZones(region: String = "") : Array[String] = {
    val timeZones: Array[String] = java.util.TimeZone.getAvailableIDs()
    if (region.trim.isEmpty) {
      timeZones.sorted
    } else {
      val filteredStrings = for (s <- timeZones if s.startsWith(region)) yield {
        s.replaceFirst(region + "/", "")
      }
      filteredStrings.sorted
    }
  }
  
}