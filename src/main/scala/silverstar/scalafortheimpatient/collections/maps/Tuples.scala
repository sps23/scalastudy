package silverstar.scalafortheimpatient.collections.maps

object Tuples {

//  private val tupleScore1 = (1, 3.14, "John")
//  private val tupleScore2 = (2, 5.77, "Tom")
//  private val tupleScore3 = (3, 4.21, "Paul")
//
//  private val tupleStudent1 = ("Adam", "male", 22)
//  private val tupleStudent2 = ("Eve", "female", 19)
//  private val tupleStudent3 = ("John", "male", 25)

  def printTupleScores(t: Tuple3[Int, Double, String]) {
    println(t._1.toString + ": " + t._3 + " = " + t._2.toString)
  }

  def printTupleStudent(t: Tuple3[String, String, Int]) {
    println("name: " + t._1 + ";\tsex: " + t._2 + ";\tage=" + t._3.toString)
  }

  def printZippedSymbols(symbols: Array[String], counts: Array[Int]) {
    val pairs = symbols.zip(counts)
    for ((s, c) <- pairs) println(s * c)
  }
}
