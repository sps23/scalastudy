package silverstar.scalafortheimpatient.collections.arrays

@SuppressWarnings(Array("org.wartremover.warts.All"))
object ArrayTransforming {

  import scala.collection.mutable.ArrayBuffer

  val numsArray1: Array[Int] = Array(1, 2, 3, 4)
  val numsArray2: Array[Int] = Array(2, -4, 7, -5)

  val numsArrayBuffer1a: ArrayBuffer[Int] = ArrayBuffer[Int](-1, 2, 3, 4, -5)
  val numsArrayBuffer2a: ArrayBuffer[Int] = ArrayBuffer[Int](1, 2, 3, 4, -5)
  val numsArrayBuffer3a: ArrayBuffer[Int] = ArrayBuffer[Int](1, 2, 3, -4, -5)
  val numsArrayBuffer4a: ArrayBuffer[Int] = ArrayBuffer[Int](1, 2, -3, 4, -5)

  val numsArrayBuffer1b: ArrayBuffer[Int] = ArrayBuffer[Int](-1, 2, 3, 4, -5)
  val numsArrayBuffer2b: ArrayBuffer[Int] = ArrayBuffer[Int](1, 2, 3, 4, -5)
  val numsArrayBuffer3b: ArrayBuffer[Int] = ArrayBuffer[Int](1, 2, 3, -4, -5)
  val numsArrayBuffer4b: ArrayBuffer[Int] = ArrayBuffer[Int](1, 2, -3, 4, -5)

  val numsArrayBuffer1c: ArrayBuffer[Int] = ArrayBuffer[Int](-1, 2, 3, 4, -5)
  val numsArrayBuffer2c: ArrayBuffer[Int] = ArrayBuffer[Int](1, 2, 3, 4, -5)
  val numsArrayBuffer3c: ArrayBuffer[Int] = ArrayBuffer[Int](1, 2, 3, -4, -5)
  val numsArrayBuffer4c: ArrayBuffer[Int] = ArrayBuffer[Int](1, 2, -3, 4, -5)

  // transformation yields a new array

  def arrayDouble(a: Array[Int]): Array[Int] = {
    val result = for (elem <- a) yield 2 * elem
    result
  }

  def arrayDoubleEvenGourd(a: Array[Int]): Array[Int] = {
    for (elem <- a if elem % 2 == 0) yield 2 * elem
  }

  def arrayDoubleEvenMap(a: Array[Int]): Array[Int] = {
    a.filter(_ % 2 == 0).map(2 * _)
  }

  // Given an ArrayBuffer of integers remove all but the first negative number

  // Set a flag when the first negative number is called and remove all element beyond
  def removeAllAfterFirstNegativeInefficient(ab: ArrayBuffer[Int]): Unit = {
    println("ab = " + ab.toString())
    var first = true
    var n     = ab.length
    var i     = 0
    while (i < n) {
      //println("[i;n]=[" + i + ";" + n + "]; ab(" + i + ") = " + ab(i))
      if (first) {
        if (ab(i) < 0) first = false
        i += 1
      } else {
        ab.remove(i); n -= 1
      }
    }
    println("ab = " + ab.toString())
  }

  // Collect the indexes to keep, copy nonnegative element to the front and trim the end
  def removeAllAfterFirstNegativeEfficient1(ab: ArrayBuffer[Int]): Unit = {
    println("ab = " + ab.toString())
    var first = true
    val indexes = for (i <- ab.indices if first) yield {
      //println("[i;first]= [" + i + ";" + first + "]; ab(" + i + ") = " + ab(i))
      if (ab(i) < 0) {
        first = false
      }
      i
    }
    //println("indexes = " + indexes.toString())
    for (j <- indexes.indices) ab(j) = ab(indexes(j))
    ab.dropRightInPlace(ab.length - indexes.length)
    println("ab = " + ab.toString())
  }

  // Collect the indexes of negative, reverse the sequence, drop the last index, remove from a for each index
  def removeAllAfterFirstNegativeEfficient2(ab: ArrayBuffer[Int]): Unit = {
    println("ab = " + ab.toString())
    // var first = true
    val indexes = for (i <- ab.indices if ab(i) < 0) yield {
      //println("[i;first]= [" + i + ";" + first + "]; ab(" + i + ") = " + ab(i))
      i
    }
    val n = indexes.length - 2
    println("n = " + n + "; indexes = " + indexes.toString())
    val indexes2 = indexes.reverse.drop(n)
    println("indexes2 = " + indexes2.toString())
    for (j <- indexes2.indices) ab.remove(j)
    println("ab = " + ab.toString())
  }
}
