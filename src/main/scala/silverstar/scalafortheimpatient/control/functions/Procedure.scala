package silverstar.scalafortheimpatient.control.functions

object Procedure {

  // no explicit return type
  def box(s: String) {
    val border = "-" * s.length + "--\n"
    print(border + "|" + s + "|\n" + border)
  }

  // explicit return type - Unit ~= void
  def boxln(s: String): Unit = {
    val border = "-" * s.length + "--\n"
    println(border + "|" + s + "|\n" + border)
  }
}
