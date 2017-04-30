package silverstar.scalafortheimpatient.control.functions

object Decorate {
  
  def decorate(str: String, left: String = "[", right: String = "]") = left + str + right
}