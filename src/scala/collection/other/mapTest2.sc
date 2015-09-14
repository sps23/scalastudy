package scala.collection.other

object mapTest2 {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet

  val errorMapGirls1 = Map("anna" -> "anna error", "eva" -> "eva error", "lucy" -> List("lucy error"), "jane" -> Map("jj" -> "jane error", "ii" -> 9888), "jilian" -> List(876))
                                                  //> errorMapGirls1  : scala.collection.immutable.Map[String,Object] = Map(eva ->
                                                  //|  eva error, anna -> anna error, jilian -> List(876), jane -> Map(jj -> jane 
                                                  //| error, ii -> 9888), lucy -> List(lucy error))
  val errorMapGirls2 = Map("poly" -> List("poly error"), "sasha" -> Map("s1" -> "s1 error", "s2" -> 9888))
                                                  //> errorMapGirls2  : scala.collection.immutable.Map[String,scala.collection.imm
                                                  //| utable.Iterable[java.io.Serializable] with PartialFunction[String with Int,A
                                                  //| ny]{def seq: scala.collection.immutable.Iterable[java.io.Serializable] with 
                                                  //| PartialFunction[String with Int,Any]{def seq: scala.collection.immutable.Ite
                                                  //| rable[java.io.Serializable] with PartialFunction[String with Int,Any]}}] = M
                                                  //| ap(poly -> List(poly error), sasha -> Map(s1 -> s1 error, s2 -> 9888))
  val errorMapBoys1 = Map("paul" -> 543, "mike" -> true, "steve" -> List("steve error"))
                                                  //> errorMapBoys1  : scala.collection.immutable.Map[String,Any] = Map(paul -> 54
                                                  //| 3, mike -> true, steve -> List(steve error))
  val errorMapBoys2 = Map("george" -> 908, "jim" -> false, "nick" -> List("nick error"))
                                                  //> errorMapBoys2  : scala.collection.immutable.Map[String,Any] = Map(george -> 
                                                  //| 908, jim -> false, nick -> List(nick error))
  val errorMap: Map[String, Any] = Map("tom" -> List("tom error"), "john" -> 1234, "girls1" -> errorMapGirls1, "girls" -> List(errorMapGirls2, errorMapGirls1), "boys" -> List(errorMapBoys1, errorMapBoys2))
                                                  //> errorMap  : Map[String,Any] = Map(girls1 -> Map(eva -> eva error, anna -> an
                                                  //| na error, jilian -> List(876), jane -> Map(jj -> jane error, ii -> 9888), lu
                                                  //| cy -> List(lucy error)), john -> 1234, girls -> List(Map(poly -> List(poly e
                                                  //| rror), sasha -> Map(s1 -> s1 error, s2 -> 9888)), Map(eva -> eva error, anna
                                                  //|  -> anna error, jilian -> List(876), jane -> Map(jj -> jane error, ii -> 988
                                                  //| 8), lucy -> List(lucy error))), tom -> List(tom error), boys -> List(Map(pau
                                                  //| l -> 543, mike -> true, steve -> List(steve error)), Map(george -> 908, jim 
                                                  //| -> false, nick -> List(nick error))))

  def toErrorString(errorMap: Map[String, Any]): String = {

    def iter(any: Any, acc: String, acc2: String = ""): String = any match {
      case map: Map[_, _] => {
        if (map.size >= 1) {
          //println("map: head = " + map.head)
          //iter(map.tail, iter(map.head._2, acc, acc2 + map.head._1 + "."), acc2)
          iter(map.tail, iter(map.head._2, acc + acc2 + map.head._1), acc2)
        } else {
          //println("map_acc = " + acc)
          acc
        }
      }
      case list: List[Any] => {
        list match {
          case x :: xs => {
            //println("list(x::xs): head = " + list.head)
            iter(xs, iter(x, acc, acc2), acc2)
          }
          case List(x) => {
            //println("list(x): head = " + list.head)
            iter(x, acc, acc2)
          }
          case List() => {
            //println("list")
            acc
          }
        }
      }
      case _ => {
        if (acc.endsWith("; ")) acc else acc + "; "
      }
    }
    iter(errorMap, "")
  }                                               //> toErrorString: (errorMap: Map[String,Any])String

  toErrorString(errorMapGirls1)                   //> res0: String = "eva; anna; jilian; janejj; ii; lucy; "
  //toErrorString(errorMapGirls2)

  //toErrorString(errorMapBoys1)
  //toErrorString(errorMapBoys2)

  val err = toErrorString(errorMap)               //> err  : String = "girls1eva; anna; jilian; janejj; ii; lucy; john; girlspoly
                                                  //| ; sashas1; s2; eva; anna; jilian; janejj; ii; lucy; tom; boyspaul; mike; st
                                                  //| eve; george; jim; nick; "
  //err
}