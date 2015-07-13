package scala.collections.maps

object maps {
  println("Welcome to the Scala worksheet - maps")//> Welcome to the Scala worksheet - maps

  Maps.scores1                                    //> res0: scala.collection.immutable.Map[String,Int] = Map(Alice -> 10, Bob -> 3
                                                  //| , Cindy -> 8)
  Maps.scores2                                    //> res1: scala.collection.immutable.Map[String,Int] = Map(Alice -> 100, Bob -> 
                                                  //| 30, Cindy -> 80)

  Maps.scoresMut1                                 //> res2: scala.collection.mutable.Map[String,Int] = Map(Bob -> 3, Alice -> 10, 
                                                  //| Cindy -> 8)
  Maps.scoresMut2                                 //> res3: scala.collection.mutable.Map[String,Int] = Map(Tom -> 75)

  Maps.scoreFor1(Maps.scores1, "Bob")             //> res4: Int = 3
  Maps.scoreFor2(Maps.scores1, "Bob")             //> res5: Int = 3
  Maps.scoreFor1(Maps.scores1, "NNN")             //> res6: Int = -1
  Maps.scoreFor2(Maps.scores1, "NNN")             //> res7: Int = -1

  Maps.scoreUpdate(Maps.scoresMut1, "Cindy", 90)  //> update: scores = Map(Bob -> 3, Alice -> 10, Cindy -> 90)
  Maps.scoreUpdate(Maps.scoresMut1, "Bob", 15)    //> update: scores = Map(Bob -> 15, Alice -> 10, Cindy -> 90)
  Maps.scoreUpdate(Maps.scoresMut1, "Tom", 65)    //> update: scores = Map(Bob -> 15, Tom -> 65, Alice -> 10, Cindy -> 90)

  Maps.scoreUpdate(Maps.scoresMut1, Maps.scoresMut3)
                                                  //> update: scores = Map(Bob -> 15, Tom -> 65, Eve -> 60, Alice -> 10, John -> 4
                                                  //| 0, Cindy -> 70)

  Maps.scoreUpdate(Maps.scores3, "Cindy", 34)     //> res8: Map[String,Int] = Map(John -> 40, Eve -> 60, Cindy -> 34)
  Maps.scoreUpdate(Maps.scores3, "Bob", 99)       //> res9: Map[String,Int] = Map(John -> 40, Eve -> 60, Cindy -> 70, Bob -> 99)

  Maps.scoreUpdate(Maps.scores3, Maps.scores4)    //> res10: Map[String,Int] = Map(John -> 40, Eve -> 60, Ann -> 32, Tom -> 45, Bo
                                                  //| b -> 30, Cindy -> 70)
  Maps.scoreUpdate(Maps.scores3, Map("Alan" -> 54, "Eve" -> 88))
                                                  //> res11: Map[String,Int] = Map(John -> 40, Eve -> 88, Cindy -> 70, Alan -> 54)
                                                  //| 
  Maps.scoreUpdate(Maps.scores3, Maps.scores1)    //> res12: Map[String,Int] = Map(John -> 40, Eve -> 60, Bob -> 3, Alice -> 10, C
                                                  //| indy -> 8)

  Maps.scorePrint(Maps.scores4)                   //> name		>>>		score
                                                  //| Tom		>>>		45
                                                  //| Bob		>>>		30
                                                  //| Ann		>>>		32

  Maps.scoreSorted1                               //> res13: scala.collection.immutable.SortedMap[String,Int] = Map(Alice -> 10, B
                                                  //| ob -> 3, Cindy -> 8)
  Maps.scoreSorted2                               //> res14: scala.collection.immutable.SortedMap[String,Int] = Map(Ann -> 32, Bob
                                                  //|  -> 30, Tom -> 45)

  Tuples.printTupleScores(Tuples.tupleScore1)     //> 1: John = 3.14
  Tuples.printTupleScores(Tuples.tupleScore2)     //> 2: Tom = 5.77
  Tuples.printTupleScores(Tuples.tupleScore3)     //> 3: Paul = 4.21

  Tuples.printTupleStudent(Tuples.tupleStudent1)  //> name: Adam;	sex: male;	age=22
  Tuples.printTupleStudent(Tuples.tupleStudent2)  //> name: Eve;	sex: female;	age=19
  Tuples.printTupleStudent(Tuples.tupleStudent3)  //> name: John;	sex: male;	age=25

  val (name1, sex1, age1) = Tuples.tupleStudent1  //> name1  : String = Adam
                                                  //| sex1  : String = male
                                                  //| age1  : Int = 22

  val (name2, _, age2) = Tuples.tupleStudent2     //> name2  : String = Eve
                                                  //| age2  : Int = 19
  
  //Tuples.printZippedSymbols(Array[String]("<", "-", ">"), Array[Int](3, 15, 3))

  //  val keys: Array[String] = Array("Cindy", "Bob", "Tom")
  //  val values: Array[Int] = Array(81, 5, 55)
  //  Maps.scoreUpdate(Maps.scoresMut1, keys, values)

}