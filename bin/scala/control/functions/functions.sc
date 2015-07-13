package scala.control.functions

object functions {
  println("Welcome to the Scala worksheet - functions")
                                                  //> Welcome to the Scala worksheet - functions

  import scala.control.functions._

  Factorial.facIter(6)                            //> res0: BigDecimal = 720
  Factorial.facRec(6)                             //> res1: BigDecimal = 720
  Factorial.facIter(234)                          //> res2: BigDecimal = 2.267015005102433920884299679394583E+454
  Factorial.facRec(234)                           //> res3: BigDecimal = 2.267015005102433920884299679394583E+454

  Decorate.decorate("Hello", "???", "???")        //> res4: String = ???Hello???
  Decorate.decorate("Hello")                      //> res5: String = [Hello]
  Decorate.decorate("Hello", ">>>[")              //> res6: String = >>>[Hello]
  Decorate.decorate("Hello", right = "]>>>")      //> res7: String = [Hello]>>>

  Sum.sumIter(1, 4, 5, 8, 14)                     //> res8: Int = 32
  Sum.sumIter(1 to 12: _*)                        //> res9: Int = 78
  Sum.sumRec(24, 77, 43, 11, 8)                   //> res10: Int = 163
  Sum.sumRec(1 to 100 by 10: _*)                  //> res11: Int = 460
  val t = 10 to 100 by 25                         //> t  : scala.collection.immutable.Range = Range(10, 35, 60, 85)

  Procedure.box("Welcome")                        //> ---------
                                                  //| |Welcome|
                                                  //| ---------
  Procedure.boxln("Hello World")                  //> -------------
                                                  //| |Hello World|
                                                  //| -------------
                                                  //| 

  //Init.wordsVal
  //Init.wordsLazyCorrect
  //Init.wordsDef
  //Init.wordsLazyWrong

  Exceptions.sqrt(4)                              //> res12: Double = 2.0
  Exceptions.sqrt(2668)                           //> res13: Double = 51.65268628057983
  //Exceptions.sqrt(-1)
  
  Exercises.signum(-23)                           //> res14: Int = -1
  Exercises.signum(0)                             //> res15: Int = 0
  Exercises.signum(349985)                        //> res16: Int = 1
    
  Exercises.countdown(5)                          //> 5
                                                  //| 4
                                                  //| 3
                                                  //| 2
                                                  //| 1
                                                  //| 0
  
  Exercises.productUnicodeIter("Hello")           //> res17: BigDecimal = 9415087488
  Exercises.productUnicodeRec("Hello")            //> res18: BigDecimal = 9415087488
  
  Exercises.power(3, 0)                           //> res19: Double = 1.0
  Exercises.power(3, 1)                           //> res20: Double = 3.0
  Exercises.power(3, 4)                           //> res21: Double = 81.0
  Exercises.power(3, 13)                          //> res22: Double = 1594323.0
  Exercises.power(3, -7)                          //> res23: Double = 4.572473708276177E-4
}