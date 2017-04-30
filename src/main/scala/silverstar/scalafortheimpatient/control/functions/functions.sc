import silverstar.scalafortheimpatient.control.functions._

println("Welcome to the Scala worksheet - functions")

Factorial.facIter(6)
Factorial.facRec(6)
Factorial.facIter(234)
Factorial.facRec(234)

Decorate.decorate("Hello", "???", "???")
Decorate.decorate("Hello")
Decorate.decorate("Hello", ">>>[")
Decorate.decorate("Hello", right = "]>>>")

Sum.sumIter(1, 4, 5, 8, 14)
Sum.sumIter(1 to 12: _*)
Sum.sumRec(24, 77, 43, 11, 8)
Sum.sumRec(1 to 100 by 10: _*)
val t = 10 to 100 by 25

Procedure.box("Welcome")
Procedure.boxln("Hello World")

Exceptions.sqrt(4)
Exceptions.sqrt(2668)
Exercises.signum(-23)
Exercises.signum(0)
Exercises.signum(349985)
Exercises.countdown(5)
Exercises.productUnicodeIter("Hello")
Exercises.productUnicodeRec("Hello")
Exercises.power(3, 0)
Exercises.power(3, 1)
Exercises.power(3, 4)
Exercises.power(3, 13)
Exercises.power(3, -7)