package silverstar.scalafortheimpatient.collections.arrays

object ArrayFixedLength {
  
  val nums = new Array[Int](10)
  
  val words = new Array[String](10)
  words(0) = "My"
  words(1) = "name"
  words(2) = "is"
  words(3) = "Sylwester"
  
  //no new if initial values are supplied
  val stringArray = Array("Null", "World")
  stringArray(0) = "Hello"
  
}