package silverstar.scalafortheimpatient.collections.maps

import scala.collection.immutable.SortedMap

@SuppressWarnings(Array("org.wartremover.warts.NonUnitStatements"))
object Maps {

  val scores1 = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  val scores2 = Map(("Alice", 100), ("Bob", 30), ("Cindy", 80))
  val scores3 = Map(("John", 40), ("Eve", 60), ("Cindy", 70))
  val scores4 = Map(("Tom", 45), ("Bob", 30), ("Ann", 32))

  val scoresMut1 = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  val scoresMut2 = scala.collection.mutable.Map[String, Int]()
  scoresMut2.put("Tom", 75)
  val scoresMut3 = scala.collection.mutable.Map("John" -> 40, "Eve" -> 60, "Cindy" -> 70)
  val scoresMut4 = scala.collection.mutable.Map("Tom"  -> 45, "Bob" -> 30, "Ann"   -> 32)

  val scoreSorted1 = SortedMap("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  val scoreSorted2 = SortedMap("Tom"   -> 45, "Bob" -> 30, "Ann"  -> 32)

  def scoreFor1(scores: Map[String, Int], key: String): Int = {
    if (scores.contains(key)) {
      scores(key)
    } else -1
  }

  def scoreFor2(scores: Map[String, Int], key: String): Int = {
    scores.getOrElse(key, -1)
  }

  def scoreUpdate(scores: scala.collection.mutable.Map[String, Int], key: String, value: Int) {
    scores(key) = value
    println("update: scores = " + scores.toString())
  }

  def scoreUpdate(scores: scala.collection.mutable.Map[String, Int],
                  update: scala.collection.mutable.Map[String, Int]) {
    scores ++= update
    println("update: scores = " + scores.toString())
  }

  def scoreUpdate(scores: Map[String, Int], key: String, value: Int): Map[String, Int] = {
    val result = scores + (key -> value)
    result
  }

  def scoreUpdate(scores: Map[String, Int], update: Map[String, Int]): Map[String, Int] = {
    val result: Map[String, Int] = scores ++ update
    result
  }

  def scoreUpdate(scores: scala.collection.mutable.Map[String, Int], keys: Array[String], values: Array[Int]) {
    println("scorreUpdate")
    if (keys.length == values.length) {
      for (i <- keys.indices) {
        if (scores.contains(keys(i))) {
          scores(keys(i)) = values(i)
        }
      }
    }
    println("update: scores = " + scores.toString)
  }

  def scorePrint(scores: Map[String, Int]) {
    println("name\t\t>>>\t\tscore")
    for ((k, v) <- scores) {
      println(k + "\t\t>>>\t\t" + v.toString)
    }
  }
}
