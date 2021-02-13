package silverstar.scalafortheimpatient.collections.arrays

object ArrayTraversing {

  import scala.collection.mutable.ArrayBuffer

  val numsArray       = Array(1, 2, 3, 4, 5)
  val numsArrayBuffer = ArrayBuffer(7, 8, 9, 10)

  def printlnArray(a: Array[Int]): Unit = {
    for (i <- a.indices) println(i.toString + ": " + a(i).toString)
  }

  def printlnArrayBuffer(ab: ArrayBuffer[Int]): Unit = {
    for (i <- ab.indices) println(i.toString + ": " + ab(i).toString)
  }

  def printArray(a: Array[Int]): Unit = {
    for (elem <- a) print(elem.toString + "; ")
  }

  def printArrayBuffer(ab: ArrayBuffer[Int]): Unit = {
    for (elem <- ab) print(elem.toString + "; ")
  }
}
