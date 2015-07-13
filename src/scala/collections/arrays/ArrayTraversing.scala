package scala.collections.arrays

/**
 * @author sylwesterstocki
 */
object ArrayTraversing {

  import scala.collection.mutable.ArrayBuffer

  val numsArray = Array(1, 2, 3, 4, 5)
  val numsArrayBuffer = ArrayBuffer(7, 8, 9, 10)

  def printlnArray(a: Array[Int]) {
    for (i <- 0 until a.length) println(i + ": " + a(i))
  }

  def printlnArrayBuffer(ab: ArrayBuffer[Int]) {
    for (i <- 0 until ab.length) println(i + ": " + ab(i))
  }

  def printArray(a: Array[Int]) {
    for (elem <- a) print(elem + "; ")
  }

  def printArrayBuffer(ab: ArrayBuffer[Int]) {
    for (elem <- ab) print(elem + "; ")
  }
}