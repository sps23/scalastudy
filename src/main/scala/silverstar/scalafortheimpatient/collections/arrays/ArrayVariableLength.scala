package silverstar.scalafortheimpatient.collections.arrays

object ArrayVariableLength {

  import scala.collection.mutable.ArrayBuffer

  val nums1 = ArrayBuffer[Int]()
  val nums2 = new ArrayBuffer[Int]
  val nums3 = new ArrayBuffer[Int]

  //add element to the end
  nums1 += 1
  //add collection to the end
  nums1 += (1, 2, 3, 5)
  nums3 += (1, 2, 3, 4, 5, 6)

  //append any collection to the end
  nums2 ++= Array(8, 13, 21)
  nums2 ++= ArrayBuffer(13, 56, 63, 73, 91)
  nums2.trimEnd(2)

  //adding or removing element at the end of array buffer is efficient
  // other operations are not so well optimized

  //insert before index 2
  nums3.insert(2, 0)
  nums3.insert(2, 8, 8, 8)

  //remove at index 
  nums3.remove(6)
  // remove 3 elements starting from index 6
  nums3.remove(6, 3)

  val numsArray = nums1.toArray
  val numsBuffer = Array[Int](9, 9, 9).toBuffer
}