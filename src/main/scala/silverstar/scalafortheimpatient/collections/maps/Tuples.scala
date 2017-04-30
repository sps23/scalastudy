package silverstar.scalafortheimpatient.collections.maps

object Tuples {

  val tupleScore1 = (1, 3.14, "John")
  val tupleScore2 = (2, 5.77, "Tom")
  val tupleScore3 = (3, 4.21, "Paul")

  val tupleStudent1 = ("Adam", "male", 22)
  val tupleStudent2 = ("Eve", "female", 19)
  val tupleStudent3 = ("John", "male", 25)

  def printTupleScores(t: Tuple3[Int, Double, String]) {
    println(t._1 + ": " + t._3 + " = " + t._2)
  }

  def printTupleStudent(t: Tuple3[String, String, Int]) {
    println("name: " + t._1 + ";\tsex: " + t._2 + ";\tage=" + t._3)
  }

  def printZippedSymbols(symbols: Array[String], counts: Array[Int]) {
    val pairs = symbols.zip(counts)
    for ((s, c) <- pairs) println(s * c)
  }
}