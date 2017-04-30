import silverstar.scalafortheimpatient.collections.maps.{Maps, Tuples}

println("Welcome to the Scala worksheet - maps")

Maps.scores1
Maps.scores2
Maps.scoresMut1
Maps.scoresMut2

Maps.scoreFor1(Maps.scores1, "Bob")
Maps.scoreFor2(Maps.scores1, "Bob")
Maps.scoreFor1(Maps.scores1, "NNN")
Maps.scoreFor2(Maps.scores1, "NNN")

Maps.scoreUpdate(Maps.scoresMut1, "Cindy", 90)
Maps.scoreUpdate(Maps.scoresMut1, "Bob", 15)
Maps.scoreUpdate(Maps.scoresMut1, "Tom", 65)
Maps.scoreUpdate(Maps.scoresMut1, Maps.scoresMut3)
Maps.scoreUpdate(Maps.scores3, "Cindy", 34)
Maps.scoreUpdate(Maps.scores3, "Bob", 99)
Maps.scoreUpdate(Maps.scores3, Maps.scores4)
Maps.scoreUpdate(Maps.scores3, Map("Alan" -> 54, "Eve" -> 88))
Maps.scoreUpdate(Maps.scores3, Maps.scores1)

Maps.scorePrint(Maps.scores4)

Maps.scoreSorted1
Maps.scoreSorted2

Tuples.printTupleScores(Tuples.tupleScore1)
Tuples.printTupleScores(Tuples.tupleScore2)
Tuples.printTupleScores(Tuples.tupleScore3)

Tuples.printTupleStudent(Tuples.tupleStudent1)
Tuples.printTupleStudent(Tuples.tupleStudent2)
Tuples.printTupleStudent(Tuples.tupleStudent3)

val (name1, sex1, age1) = Tuples.tupleStudent1
val (name2, _, age2) = Tuples.tupleStudent2
