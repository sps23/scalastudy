package silverstar.scalafortheimpatient.control.functions

object Sum extends App {

  @SuppressWarnings(Array("org.wartremover.warts.Var"))
  def sumIter(args: Int*): Int = {
    var result = 0
    for (arg <- args) result += arg
    result
  }

  def sumRec(args: Int*): Int = {
    args.toList match {
      case Nil    => 0
      case h :: t => h + sumRec(t: _*)
    }
  }

  val s1 = sumIter(Seq(1, 2, 3): _*)
  val s2 = sumRec(Seq(1, 2, 3): _*)

  println(s1)
  println(s2)
}
